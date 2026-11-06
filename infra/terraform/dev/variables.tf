variable "aws_region" {
  type    = string
  default = "ap-south-1"
}

variable "environment" {
  type    = string
  default = "dev"
}

variable "db_username" {
  type    = string
  default = "padapav"
}

variable "db_password" {
  description = "Strong database password. Supply via TF_VAR_db_password or a local, untracked tfvars file."
  type        = string
  sensitive   = true
  nullable    = false
}
