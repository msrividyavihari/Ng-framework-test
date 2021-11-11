#!/bin/bash

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
mkdir -p $TEMP_LOGS_DIR

$BASE_DIR/ref-build.sh

printf "\n Starting ng-ref-table-svc in background mode ($NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:bootRun) ...."
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
REF_LOG=$TEMP_LOGS_DIR/ref-service.$DATE_TIME_STR.log
REF_PID=$TEMP_LOGS_DIR/ref-service.pid.txt
nohup $NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:bootRun -p $NG_REF_SVC_DIR  > $REF_LOG 2>&1 & echo $! > $REF_PID &

printf "\n****************************************************************************************************************"
printf "\n* ng-ref-table-svc app started with Process id: $(getProcessId $REF_PID)                  				" 
printf "\n* ng-ref-table-svc app STDOUTS/STDERR will be redirected to $REF_LOG          							"
printf "\n****************************************************************************************************************" 