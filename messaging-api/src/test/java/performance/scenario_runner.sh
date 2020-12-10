#!/bin/bash

SCENARIO_FILE=$1
CHAT_LIST_FILE=$2
TIME=$3 
IP=$4
#TIME=202012092324
#IP=localhost


CHAT_LIST=$(cat $CHAT_LIST_FILE)
IFS=';' read -ra chatsToCreate <<< $CHAT_LIST

for id in ${chatsToCreate[@]}
do
  ./createChat.sh $IP $id
done


while read line; do
  IFS=';' read -ra scenario_entry <<< $line

  USER=${scenario_entry[0]}
  CHAT_IDS=${scenario_entry[1]}
  MSG_NUMBER=${scenario_entry[2]}
  MSG_DELAY_MS=${scenario_entry[3]}

  ./schedule_messaging_agent.sh $IP $USER $CHAT_IDS $MSG_NUMBER $MSG_DELAY_MS $TIME

done < $SCENARIO_FILE
