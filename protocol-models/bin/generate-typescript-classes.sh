#!/usr/bin/env bash

set -e

git config --global --add safe.directory /airbyte-protocol

ROOT_DIR=${ROOT_DIR:-$(git rev-parse --show-toplevel)}

[ -z "$ROOT_DIR" ] && exit 1

cd protocol-models/typescript
npm install
npm run build
