#!/usr/bin/env bash

set -e

git config --global --add safe.directory /airbyte-protocol

ROOT_DIR=${ROOT_DIR:-$(git rev-parse --show-toplevel)}
cd $ROOT_DIR

source ".env"
echo "loaded VERSION=$VERSION"

[ -z "$ROOT_DIR" ] && exit 1

cd protocol-models/typescript

# package.json's vesion MUST be in the file as a literal... so lets use jq to replace it.
jq ".version=\"$VERSION\"" package.json > package.json.tmp
mv package.json.tmp package.json

npm install -g pnpm
pnpm install
pnpm run build
