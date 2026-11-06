$ErrorActionPreference = 'Stop'

$root = Split-Path -Parent $PSScriptRoot
Set-Location $root

if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    throw "Java was not found. Install JDK 21 and ensure java is on PATH."
}
if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
    throw "Maven was not found. Install Maven 3.9+ and ensure mvn is on PATH."
}
if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
    throw "Docker was not found. Install Docker Desktop or Docker Engine."
}

$javaMajor = (& java -version 2>&1 | Select-String -Pattern 'version "(\d+)' | ForEach-Object { [int]$_.Matches[0].Groups[1].Value } | Select-Object -First 1)
if ($javaMajor -ne 21) {
    Write-Warning "This project targets Java 21; detected Java major version: $javaMajor"
}

Write-Host "Building all Java services..."
mvn -B -DskipTests package

Write-Host "Starting the complete local stack..."
docker compose up --build -d

Write-Host "Waiting briefly for containers to initialize..."
Start-Sleep -Seconds 10

docker compose ps

Write-Host ""
Write-Host "Local endpoints:"
Write-Host "- API Gateway: http://localhost:8080"
Write-Host "- Customer app: run npm install / npm run dev in apps/customer-web"
Write-Host "- Restaurant admin: run npm install / npm run dev in apps/restaurant-admin"
Write-Host "- PostgreSQL: localhost:5432 (local demo credentials only)"
Write-Host "- Redis: localhost:6379 (container present; application integration is pending)"
Write-Host "- OpenSearch: http://localhost:9200 (security plugin disabled for local-only development)"
Write-Host ""
Write-Host "Check gateway health: Invoke-RestMethod http://localhost:8080/actuator/health"
Write-Host "Run order-flow demo: ./scripts/full-platform-demo.ps1"
