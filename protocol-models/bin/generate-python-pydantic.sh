#!/usr/bin/env bash

set -e

git config --global --add safe.directory /airbyte-protocol

ROOT_DIR=${ROOT_DIR:-$(git rev-parse --show-toplevel)}

[ -z "$ROOT_DIR" ] && exit 1



YAML_DIR=protocol-models/src/main/resources/airbyte_protocol
OUTPUT_DIR=protocol-models/python/airbyte_protocol/airbyte_protocol/models

python -m pip install --upgrade pip
pip install datamodel_code_generator==0.11.19

rm -rf "$ROOT_DIR/$OUTPUT_DIR"/*.py
mkdir -p "$ROOT_DIR/$OUTPUT_DIR"

echo "# generated by generate-python-pydantic.sh" > "$ROOT_DIR/$OUTPUT_DIR"/__init__.py
echo "name = 'models'" >> "$ROOT_DIR/$OUTPUT_DIR"/__init__.py

for f in "$ROOT_DIR/$YAML_DIR"/*.yaml; do
  filename_wo_ext=$(basename "$f" | cut -d . -f 1)
  echo "from .$filename_wo_ext import *" >> "$ROOT_DIR/$OUTPUT_DIR"/__init__.py

  datamodel-codegen \
    --input "$ROOT_DIR/$YAML_DIR/$filename_wo_ext.yaml" \
    --output "$ROOT_DIR/$OUTPUT_DIR/$filename_wo_ext.py" \
    --use-title-as-name \
    --disable-timestamp
done
