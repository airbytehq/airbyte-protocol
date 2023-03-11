#!/usr/bin/env bash

set -e

git config --global --add safe.directory /airbyte-protocol

ROOT_DIR=${ROOT_DIR:-$(git rev-parse --show-toplevel)}
[ -z "$ROOT_DIR" ] && exit 1
cd $ROOT_DIR

source ".env"
PATCHED_VERSION="${VERSION/.dev/"-dev"}" # npm is strict semver - you only get 2 .s
echo "loaded VERSION=$PATCHED_VERSION"

cd protocol-models/typescript

npm install -g pnpm

pnpm version $PATCHED_VERSION --allow-same-version
pnpm install
pnpm run build
