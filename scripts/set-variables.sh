#!/bin/bash

function getProcessId {
echo $(<"$1")
}

showErrorMsg() {
    printf "Error on line $1"
    exit 1
}

trap 'showErrorMsg $LINENO' ERR

SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"
# SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
NEXTGEN_HOME_DIR="$(dirname "$SCRIPT_DIR")"
NG_BE_DIR=$NEXTGEN_HOME_DIR/ng_backend
NG_FWK_DIR=$NG_BE_DIR/ng-framework
NG_REF_SVC_DIR=$NG_BE_DIR/ng-fw-services
NG_AR_DIR=$NG_BE_DIR/ng-app-services/app-reg
NG_DC_DIR=$NG_BE_DIR/ng-app-services/data-collection-app
NG_HA_DIR=$NG_BE_DIR/ng-app-services/hearing-appeal
TEMP_LOGS_DIR=~/tmp/logs