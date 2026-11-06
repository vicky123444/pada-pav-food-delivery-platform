# AWS development infrastructure starter

This Terraform configuration is an educational/dev starting point, not a production baseline. It has not been validated with `terraform init/validate/plan` in the review environment and must be reviewed before apply.

## Required secret input

Do not commit a real password in a tfvars file. Set the database password in your shell before running Terraform:

PowerShell:
```powershell
$env:TF_VAR_db_password = Read-Host -AsSecureString | ConvertFrom-SecureString -AsPlainText -Force
terraform init
terraform validate
terraform plan
```

For stronger secret handling, use your organization's approved secret injection workflow. Do not put the password in shell history or CI logs.

## Security/cost cautions

- RDS is private in this version and its inbound rule is scoped to the VPC CIDR. Narrow this further to the real application security group before production.
- EKS worker nodes use private subnets with one shared NAT gateway for development egress. This is cost-conscious but not AZ-resilient; use one NAT gateway per AZ or reviewed VPC endpoints for production.
- OpenSearch sizing and EKS/RDS resources can incur charges while running. Check current pricing for `ap-south-1`, create a budget/alerts and destroy unused dev resources.
- No real payment credentials, production secrets or customer data belong in this repository.
