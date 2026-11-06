# Add the reviewed source bundle to this repository

The initial README and docs were added directly to the remote repository. The complete source archive is provided separately in this conversation as:

`pada-pav-food-delivery-platform-github-ready.zip`

It contains the reviewed source-only project (generated Maven `target/` directories and compiled JARs are excluded).

## Windows PowerShell

1. Download the ZIP from the conversation and extract it.
2. Open PowerShell in the extracted folder.
3. The extracted project has a top-level folder named `Pada Pav Food Delivery Platform`. Open that folder.
4. Clone the remote repository into a separate folder, then copy the extracted project contents into the clone. Keep the remote README/docs; resolve any duplicate docs by retaining the reviewed versions.

Example:

```powershell
git clone https://github.com/vicky123444/pada-pav-food-delivery-platform.git
cd pada-pav-food-delivery-platform
# Copy the extracted application folders/files into this repository folder.
git status
git add .
git status
git commit -m "feat: add food delivery platform starter source"
git push origin main
```

Review `git status` before committing. Never commit passwords, tokens, private keys, local `.env` files, Terraform state, generated Maven `target/` folders, `node_modules/` or production data.

## Verify after push

- Confirm the root contains `services/`, `apps/`, `infra/`, `contracts/`, `scripts/` and `.github/workflows/`.
- Open the Actions tab and review the first Maven and frontend workflow run.
- Fix any failing builds before describing the repository as build-verified.
- Run the local Docker Compose workflow on a machine with JDK 21, Maven and Docker installed.
- Run `terraform fmt -check` and `terraform validate` from the Terraform directory before any plan/apply.

A source upload does not mean the application is production ready. See [implementation status](implementation-status.md).
