#!/bin/bash

curl -d '{"username" : "John2", "chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4"}' -H 'Content-Type: application/json' localhost:8080/connectUserToChat
node single_test.js John2 5229ff98-2b23-4fa9-892c-55448f0c63c4 100 1000 > john2.log 2>&1 &