#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
printf "\n Building hearing-appeal app ($NG_HA_DIR/gradlew build -x test -p $NG_HA_DIR) ...."
$NG_HA_DIR/gradlew build -x test -p $NG_HA_DIR