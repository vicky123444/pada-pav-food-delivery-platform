# Contributing

1. Create a feature branch from `main`.
2. Keep service boundaries clear; do not share JPA entities across services.
3. Add/update unit and integration tests for behavior changes.
4. Update OpenAPI/event contracts and docs when APIs or events change.
5. Never commit credentials, `.env` files, real customer data or Terraform state.
6. Run `mvn -B verify` and `npm run build` in each changed frontend app before opening a pull request.
7. Do not claim a cloud integration is implemented until it has an automated or documented integration test.

See `docs/implementation-status.md` for known gaps and `docs/production-hardening.md` for planned hardening.
