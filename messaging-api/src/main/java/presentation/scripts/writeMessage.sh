#!/bin/bash

curl -d '{"chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4","author" : "John" ,"content" : "Hello"}' -H 'Content-Type: application/json' localhost:8080/writeMessage


