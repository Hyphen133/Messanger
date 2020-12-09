const WebSocket = require('ws');

var ip = process.argv[2]
var user = process.argv[3]
var chatId = process.argv[4]
var num_messages = parseInt(process.argv[5])
var sleep_time_ms = parseInt(process.argv[6])


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
    return "[" + new Date().toLocaleString() + "]" + type +": " + content + " chat: " + chatId
}

async function test(number_of_messages, break_between_messages, filestream) {
    const websocket = new WebSocket('ws://' + ip +':8080/webSocket/' + user)

    websocket.on('message', function incoming(data) {
        console.log(getMessage("Received", ""))
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

            await sleep(sleep_time_ms);
        }

    } else {
        console.log("ERROR on Opening Connection")
        return
    }
    websocket.close()
}

test(num_messages, sleep_time_ms)