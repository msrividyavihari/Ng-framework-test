#!/bin/bash

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
mkdir -p $TEMP_LOGS_DIR

$BASE_DIR/ar-build.sh

printf "\n Starting app-reg in background mode ($NG_AR_DIR/gradlew :app-reg-web:bootRun) ...."
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
AR_LOG=$TEMP_LOGS_DIR/app-reg.$DATE_TIME_STR.log
AR_PID=$TEMP_LOGS_DIR/app-reg.pid.txt
nohup $NG_AR_DIR/gradlew :app-reg-web:bootRun -p $NG_AR_DIR > $AR_LOG 2>&1 & echo $! > $AR_PID &

printf "\n****************************************************************************************************************"
printf "\n* applicationregistration app started with Process id: $(getProcessId $AR_PID)                  				" 
printf "\n* applicationregistration app STDOUTS/STDERR will be redirected to $AR_LOG          							"
printf "\n****************************************************************************************************************" 