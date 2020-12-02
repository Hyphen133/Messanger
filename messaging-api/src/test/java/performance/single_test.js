const WebSocket = require('ws');

var user = process.argv[2]
var chatId = process.argv[3]
var num_messages = parseInt(process.argv[4])
var sleep_time_ms = parseInt(process.argv[5])



var log = console.log;

console.log = function(){
    log.apply(console, [Date.now()].concat(arguments));
};

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

async function test(number_of_messages, break_between_messages) {
    const websocket = new WebSocket('ws://localhost:8080/webSocket/Dan1112')

    websocket.on('message', function incoming(data) {
        log.call(console,new Date(),user + " recieved " + data + " chat: " + chatId  )
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
            log.call(console,new Date(),"Sent: " + content + " chat: " + chatId)
            await sleep(sleep_time_ms);
        }

    } else {
        log.call(console,new Date(),"the socket is closed OR couldn't have the socket in time, program crashed")
        return
    }
    websocket.close()
}

test(num_messages, sleep_time_ms)