#!/bin/bash

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh
mkdir -p $TEMP_LOGS_DIR

$BASE_DIR/ha-build.sh

printf "\n Starting hearing-appeal in background mode ($NG_HA_DIR/gradlew :bootRun) ...."
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
HA_LOG=$TEMP_LOGS_DIR/hearing-appeal.$DATE_TIME_STR.log
HA_PID=$TEMP_LOGS_DIR/hearing-appeal.pid.txt
nohup $NG_HA_DIR/gradlew :bootRun -p $NG_HA_DIR  > $HA_LOG 2>&1 & echo $! > $HA_PID &

printf "\n****************************************************************************************************************"
printf "\n* hearing-appeal app started with Process id: $(getProcessId $HA_PID)                  				" 
printf "\n* hearing-appeal app STDOUTS/STDERR will be redirected to $HA_LOG          							"
printf "\n****************************************************************************************************************" 