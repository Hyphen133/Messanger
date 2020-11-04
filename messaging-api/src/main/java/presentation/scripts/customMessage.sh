#!/bin/bash

echo $1
JSON=$(echo '{"chatId" : "5229ff98-2b23-4fa9-892c-55448f0c63c4","author" : "John" ,"content" : "MESSAGE"}' | sed -e 's/MESSAGE/${MESSAGE}/')

echo $JSON

curl -d "$JSON"  -H 'Content-Type: application/json' localhost:8080/writeMessage


