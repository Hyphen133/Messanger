#!/bin/bash

IP=$1
USER=$2
CHAT_ID=$3
MSG_NUMBER=$4
MSG_DELAY_MS=$5

LOG_FILE=$USER.log

curl -d '{"username" : "'$USER'", "chatId" : "'$CHAT_ID'"}' -H 'Content-Type: application/json' $IP:8080/connectUserToChat
node single_test.js $IP $USER $CHAT_ID $MSG_NUMBER $MSG_DELAY_MS > $LOG_FILE 2>&1 &