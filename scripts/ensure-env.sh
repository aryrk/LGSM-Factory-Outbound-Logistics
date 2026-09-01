#!/usr/bin/env bash
set -eu

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

ensure_file() {
  local target="$1"
  local source="$2"

  if [ -f "$target" ]; then
    echo "Using existing $target"
    return
  fi

  if [ -f "$source" ]; then
    cp "$source" "$target"
    echo "Created $target from $source"
  else
    echo "No template found for $target" >&2
    exit 1
  fi
}

ensure_file "$ROOT_DIR/logistics/.env" "$ROOT_DIR/logistics/.env.example"
ensure_file "$ROOT_DIR/logistics-FE/.env" "$ROOT_DIR/logistics-FE/.env.example"
