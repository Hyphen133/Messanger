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

function getMessage(type, content){
    return "[" + new Date().toDateString() + "]" + type +": " + content + " chat: " + chatId + "\n"
}

async function test(number_of_messages, break_between_messages, filestream) {
    const websocket = new WebSocket('ws://localhost:8080/webSocket/' + user)

    websocket.on('message', function incoming(data) {
        console.log(getMessage("Recieved", ""))
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

            let message = getMessage("Sent", content)
            console.log(message)
            // try {
            //     writeStream.write(message)
            // }catch (e){
            //     console.log(e);
            // }
            await sleep(sleep_time_ms);
        }

    } else {
        writeStream.write("ERROR on Opening Connection")
        return
    }
    websocket.close()
}

test(num_messages, sleep_time_ms)
writeStream.close()