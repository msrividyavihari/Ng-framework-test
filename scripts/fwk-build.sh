#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"

source $BASE_DIR/set-variables.sh

printf  "\n Building ng-framework ($NG_FWK_DIR/gradlew build -x test) .... "
$NG_FWK_DIR/gradlew build -x test -p $NG_FWK_DIR

printf  "\n Publishing ng-framework ($NG_FWK_DIR/gradlew publishToMavenLocal) ...."
$NG_FWK_DIR/gradlew publishToMavenLocal -p $NG_FWK_DIR