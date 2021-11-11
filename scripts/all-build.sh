#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"
$BASE_DIR/fwk-build.sh
$BASE_DIR/ref-build.sh
$BASE_DIR/ar-build.sh
$BASE_DIR/dc-build.sh
$BASE_DIR/ha-build.sh