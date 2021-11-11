#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
printf "\n Building data-collection app ($NG_DC_DIR/gradlew build -x test) ...."
$NG_DC_DIR/gradlew build -x test -p $NG_DC_DIR
printf "\n Publishing data-collection-dto ($NG_DC_DIR/gradlew publishToMavenLocal) ...."
$NG_DC_DIR/gradlew publishToMavenLocal -p $NG_DC_DIR
