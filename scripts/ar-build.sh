#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
printf "\n Building app-reg ($NG_AR_DIR/gradlew build -x test -p $NG_AR_DIR) ...."
$NG_AR_DIR/gradlew build -x test -p $NG_AR_DIR
printf "\n Publishing app-reg ($NG_AR_DIR/gradlew publishToMavenLocal) ...."
$NG_DC_DIR/gradlew publishToMavenLocal -p $NG_AR_DIR