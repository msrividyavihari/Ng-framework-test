#!/bin/bash

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"

source $BASE_DIR/set-variables.sh

printf "\n Building ng-ref-table-svc  ($NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:build -x test -p $NG_REF_SVC_DIR) ...."
$NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:build -x test -p $NG_REF_SVC_DIR