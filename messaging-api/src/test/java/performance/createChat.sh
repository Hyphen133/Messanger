#!/bin/bash

IP=$1
CHAT_ID=$2

curl -d '{"chatId":"'$CHAT_ID'"}' -H 'Content-Type: application/json' $IP:8080/createChat
