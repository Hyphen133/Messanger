#!/bin/bash

IP=$1
USER=$2
CHAT_ID=$3
MSG_NUMBER=$4
MSG_DELAY_MS=$5
TIME=$6


./messaging_agent.sh $IP $USER $CHAT_ID $MSG_NUMBER $MSG_DELAY_MS | at -t $TIME