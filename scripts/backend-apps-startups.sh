#!/bin/bash

showErrorMsg() {
    printf "\nError on line $1"
    exit 1
}

function getProcessId {
echo $(<"$1")
}

trap 'showErrorMsg $LINENO' ERR

BASE_DIR="$(dirname "$(readlink -f "$0")")"
source $BASE_DIR/set-variables.sh

mkdir -p $TEMP_LOGS_DIR
mkdir -p $TEMP_LOGS_DIR/archive

printf "\nMoving older than 7 days files from $TEMP_LOGS_DIR to $TEMP_LOGS_DIR/archive/ "
find $TEMP_LOGS_DIR/ -maxdepth 1 -mtime +7 -type f -name '*.log' -exec mv "{}" $TEMP_LOGS_DIR/archive/ \;

printf "\nAdding AWS service account to SSH KEY"
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
# nohup ssh -p 80 -fNL 1522:ng4db.cymrh4syq1xy.ap-south-1.rds.amazonaws.com:1521 db_user@ec2-65-0-29-109.ap-south-1.compute.amazonaws.com >> $TEMP_LOGS_DIR/adding-aws-service-account.$DATE_TIME_STR.log 2>&1 &
nohup ssh -p 80 -fNL 1522:ng4db.cymrh4syq1xy.ap-south-1.rds.amazonaws.com:1521 db_user@ec2-13-233-109-245.ap-south-1.compute.amazonaws.com >> $TEMP_LOGS_DIR/adding-aws-service-account.$DATE_TIME_STR.log 2>&1 &

printf "\nBuilding ng-framework ($NG_FWK_DIR/gradlew build -x test) .... "
$NG_FWK_DIR/gradlew build -x test -p $NG_FWK_DIR

printf "\nPublishing ng-framework ($NG_FWK_DIR/gradlew publishToMavenLocal) ...."
$NG_FWK_DIR/gradlew publishToMavenLocal -p $NG_FWK_DIR

printf "\nBuilding and Starting ng-ref-tabl svc APP in background mode ($NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:bootRun) ...."
$NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:build -x test -p $NG_REF_SVC_DIR
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
REF_LOG=$TEMP_LOGS_DIR/ref-service.$DATE_TIME_STR.log
nohup $NG_REF_SVC_DIR/gradlew :ng-ref-table-svc:bootRun -p $NG_REF_SVC_DIR > $REF_LOG 2>&1 & echo $! > $TEMP_LOGS_DIR/ref-service.pid.txt &

$BASE_DIR/ar-start.sh

printf "\n Building data-collection app ($NG_DC_DIR/gradlew build -x test) ...."
$NG_DC_DIR/gradlew build -x test -p $NG_DC_DIR
printf "\n Publishing data-collection-dto ($NG_DC_DIR/gradlew publishToMavenLocal) ...."
$NG_DC_DIR/gradlew publishToMavenLocal -p $NG_DC_DIR

printf "\n Starting data-collection app background mode ($NG_DC_DIR/gradlew :data-collection:bootRun) ...."
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
DC_LOG=$TEMP_LOGS_DIR/data-collection.$DATE_TIME_STR.log
nohup $NG_DC_DIR/gradlew :data-collection:bootRun -p $NG_DC_DIR > $DC_LOG 2>&1 & echo $! > $TEMP_LOGS_DIR/data-collection.pid.txt &

printf "\n Building and Starting hearing-appeal app in background mode ($NG_HA_DIR/gradlew bootRun) ...."
$NG_HA_DIR/gradlew build -x test -p $NG_HA_DIR
DATE_TIME_STR=`date +"%Y-%m-%d-%H-%M-%S"`
HA_LOG=$TEMP_LOGS_DIR/hearing-appeal.$DATE_TIME_STR.log
nohup $NG_HA_DIR/gradlew bootRun -p $NG_HA_DIR > $HA_LOG 2>&1 & echo $! > $TEMP_LOGS_DIR/hearing-appeal.pid.txt &

printf "\n****************************************************************************************************************"
printf "\n* ref-service app started with Process id: $(getProcessId $TEMP_LOGS_DIR/ref-service.pid.txt)                  " 
printf "\n* ref-service app STDOUTS/STDERR will be redirected to $REF_LOG           "
printf "\n* data-collection app started with Process id: $(getProcessId $TEMP_LOGS_DIR/data-collection.pid.txt)          " 
printf "\n* data-collection app STDOUTS/STDERR will be redirected to $DC_LOG  "
printf "\n* hearing-appeal  app started with Process id: $(getProcessId $TEMP_LOGS_DIR/hearing-appeal.pid.txt)          " 
printf "\n* hearing-appeal  app STDOUTS/STDERR will be redirected to $HA_LOG   "
printf "\n****************************************************************************************************************" 