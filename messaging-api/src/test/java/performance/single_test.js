const WebSocket = require('ws');


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

async function test() {
    const websocket = new WebSocket('ws://localhost:8080/webSocket/Dan1112')

    websocket.on('message', function incoming(data) {
        console.log(data);
    });

    const opened = await connection(websocket)

    if (opened) {
        websocket.send(JSON.stringify({
            "chatId": "5229ff98-2b23-4fa9-892c-55448f0c63c4",
            "author": "Dan1112",
            "content": "Hello"
        }))
        console.log("Sent")
    } else {
        console.log("the socket is closed OR couldn't have the socket in time, program crashed");
        return
    }
    websocket.close()
}

test()