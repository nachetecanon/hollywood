#!/usr/bin/env bash
set -eu

/opt/jboss/keycloak/bin/jboss-cli.sh --file=/scripts/keycloak.cli

args=(-b 0.0.0.0)

exec /opt/jboss/docker-entrypoint.sh "${args[@]}"
exit "$?"