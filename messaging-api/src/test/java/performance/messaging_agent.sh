#!/bin/bash

IP=$1
USER=$2
CHAT_IDS=$3
MSG_NUMBER=$4
MSG_DELAY_MS=$5

LOG_FILE=$USER.log

IFS=', ' read -ra chats <<< "$CHAT_IDS"

for id in ${chats[@]}
do
    echo "Connecting $USER to chat $id"
    curl -d '{"username" : "'$USER'", "chatId" : "'$id'"}' -H 'Content-Type: application/json' $IP:8080/connectUserToChat
done

node single_test.js $IP $USER $CHAT_IDS $MSG_NUMBER $MSG_DELAY_MS > $LOG_FILE 2>&1 &