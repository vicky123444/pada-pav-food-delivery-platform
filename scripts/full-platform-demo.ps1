param(
    [string]$BaseUrl = "http://localhost:8080"
)

$ErrorActionPreference = "Stop"

function Invoke-Json {
    param(
        [Parameter(Mandatory = $true)][string]$Method,
        [Parameter(Mandatory = $true)][string]$Url,
        [object]$Body = $null
    )

    $headers = @{ "Content-Type" = "application/json" }

    if ($null -eq $Body) {
        return Invoke-RestMethod -Method $Method -Uri $Url -Headers $headers
    }

    $jsonBody = $Body | ConvertTo-Json -Depth 10
    return Invoke-RestMethod -Method $Method -Uri $Url -Headers $headers -Body $jsonBody
}

function Wait-ForGateway {
    param(
        [string]$Url,
        [int]$TimeoutSeconds = 120
    )

    $deadline = (Get-Date).AddSeconds($TimeoutSeconds)
    while ((Get-Date) -lt $deadline) {
        try {
            Invoke-RestMethod -Method Get -Uri "$Url/actuator/health" | Out-Null
            return
        }
        catch {
            Start-Sleep -Seconds 5
        }
    }

    throw "API Gateway did not become healthy within $TimeoutSeconds seconds."
}

$root = Split-Path -Parent $PSScriptRoot
Set-Location $root

Write-Host "Checking Docker availability..."
try {
    docker info | Out-Null
}
catch {
    throw "Docker Desktop or Docker Engine is not running. Start Docker and retry."
}

Write-Host "Starting local platform with docker compose..."
docker compose up --build -d

Write-Host "Waiting for API Gateway to be healthy..."
Wait-ForGateway -Url $BaseUrl

Write-Host "Creating a test order..."
$orderPayload = @{
    customerId = 101
    items = @(
        @{ menuItemName = "Pada Pav"; quantity = 2; unitPrice = 70 },
        @{ menuItemName = "Pav Bhaji"; quantity = 1; unitPrice = 140 }
    )
}
$order = Invoke-Json -Method POST -Url "$BaseUrl/api/v1/orders" -Body $orderPayload
$orderId = $order.id
Write-Host "Order created: $orderId"

Write-Host "Confirming order..."
$confirmedOrde = Invoke-Json -Method PATCH -Url "$BaseUrl/api/v1/orders/$orderId/status?status=CONFIRMED"
Write-Host "Order status: $($confirmedOrder.status)"

Write-Host "Looking up payment record..."
$payments = Invoke-Json -Method GET -Url "$BaseUrl/api/v1/payments"
if ($payments -isnot [array]) { $payments = @($payments) }
$paymentMatch = $payments | Where-Object { $_.orderId -eq $orderId }
if (-not $paymentMatch) { throw "No payment record was created for order $orderId" }
$paymentId = $paymentMatch[0].id
Write-Host "Payment created: $paymentId"
$successPayment = Invoke-Json -Method PATCH -Url "$BaseUrl/api/v1/payments/$paymentId/success"
Write-Host "Payment status: $(successPayment.status)"

Write-Host "Checking kitchen workflow..."
$kitchenOrders = Invoke-Json -Method GET -Url "$BaseUrl/api/v1/kitchen/orders"
if ($kitchenOrders -isnot [array]) { $kitchenOrders = @($kitchenOrders) }
$k\Ø]Y