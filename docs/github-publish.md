# Repository setup and local development

The source scaffold is now committed to the main branch of this repository. Clone it directly; there is no separate ZIP source bundle required.

## Clone

```bash
git clone https://github.com/vicky123444/pada-pav-food-delivery-platform.git
cd pada-pav-food-delivery-platform
```

## Build and verify

Use JDK 21 and Maven 3.9+:

```bash
mvn -B verify
```

Build the two React prototypes independently:

```bash
cd apps/customer-web
npm ci
npm run build

cd ../restaurant-admin
npm ci
npm run build
```

## Run the local containers

The Dockerfiles expect service JARs to be created before building images. From the repository root:

```bash
mvn -B -DskipTests package
docker compose up --build -d
docker compose ps
```

The gateway is mapped to port 8080. Individual service ports are listed in the root README.

## Contribute changes

```bash
git checkout -b feature/short-description
git status
git add .
git diff --cached
git commit -m "feat: describe the change"
git push -u origin feature/short-description
```

Open a pull request and review the GitHub Actions result. Do not merge a change with failing builds.

## Never commit

- Real credentials, access tokens, certificates or private keys
- Local .env files or production data
- Terraform state or real tfvars
- Generated Maven target folders, node_modules or frontend dist output

The source scaffold is not production-ready. Read [implementation-status.md](implementation-status.md) before deploying.
