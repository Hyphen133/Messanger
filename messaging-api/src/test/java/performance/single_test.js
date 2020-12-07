const WebSocket = require('ws');
const fs = require('fs');

var user = process.argv[2]
var chatId = process.argv[3]
var num_messages = parseInt(process.argv[4])
var sleep_time_ms = parseInt(process.argv[5])
var log_path = process.argv[6]



let writeStream = fs.createWriteStream(log_path);
writeStream.write("TEST for " + user + " and chat " + chatId +"\n")

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function connection(socket, timeout = 10000) {
    const isOpened = () => (socket.readyState === WebSocket.OPEN)

    if (socket.readyState !== WebSocket.CONNECTING) {
        return isOpened()
    } else {
        const intrasleep = 100
        const ttl = timeout / intrasleep // time to loop
        let loop = 0
        while (socket.readyState === WebSocket.CONNECTING && loop < ttl) {
            await new Promise(resolve => setTimeout(resolve, intrasleep))
            loop++
        }
        return isOpened()
    }
}

async function test(number_of_messages, break_between_messages, filestream) {
    const websocket = new WebSocket('ws://localhost:8080/webSocket/' + user)

    websocket.on('message', function incoming(data) {
        filestream.write("[" + new Date().toDateString(),"] Recieved: " + content + " chat: " + chatId + "\n")
    });

    const opened = await connection(websocket)

    if (opened) {
        for (i=0; i<number_of_messages; i++){
            let content = i.toString() + " from " + user;
            websocket.send(JSON.stringify({
                "chatId": chatId,
                "author": user,
                "content": content
            }))

            filestream.write("[" + new Date().toDateString(),"] Sent: " + content + " chat: " + chatId, "base64")
            await sleep(sleep_time_ms);
        }

    } else {
        writeStream.write("ERROR: NOT OPENED")
        return
    }
    websocket.close()
}

test(num_messages, sleep_time_ms, writeStream)
writeStream.close()