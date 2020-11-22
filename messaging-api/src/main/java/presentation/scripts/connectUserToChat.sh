#!/bin/bash


curl -d '{"username" : "Dan", "chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4"}' -H 'Content-Type: application/json' localhost:8080/connectUserToChat
