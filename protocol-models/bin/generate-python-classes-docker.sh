#!/usr/bin/env bash

set -e

ROOT_DIR=${ROOT_DIR:-$(git rev-parse --show-toplevel)}
ROOT_DIR_NAME=${ROOT_DIR##*/}

[ -z "$ROOT_DIR" ] && exit 1

docker run --rm \
  --volume "${ROOT_DIR}:/${ROOT_DIR_NAME}" \
  --workdir "/${ROOT_DIR_NAME}" \
  python:3.8 "/${ROOT_DIR_NAME}/protocol-models/bin/generate-python-classes.sh"
