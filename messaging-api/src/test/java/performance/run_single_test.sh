#!/bin/bash

#At command for specific time
#node single_test.js Dan 5229ff98-2b23-4fa9-892c-55448f0c63c4 10 1000 | at now + 1 minute > log_file

#Connect user to chat (not needed cause it only loads previous messages)??
#curl -d '{"username" : "Dan", "chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4"}' -H 'Content-Type: application/json' localhost:8080/connectUserToChat


node single_test.js Dann 5229ff98-2b23-4fa9-892c-55448f0c63c4 10 1000 $PWD/log_file