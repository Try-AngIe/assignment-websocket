/* eslint-disable */

import Chat from "../components/Chat";
import Input from "../components/Input";

import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const ChatRoom = () => {

    //유즈로케이션
    const location = useLocation();

    //로그인한 유저 정보 받아오기
    const currentUser = location.state?.user;

    //메시지 상태저장
    const [messages, setMessages] = useState([]);

    //메시지 받기
    const [stompClient, setStompClient] = useState(null);

    //WebSocket 연결
    useEffect(() => {
        //Kafka는 ws프로토콜을 지원하지 않기 때문에 stompjs의 Client 객체가 아닌 Stomp 객체를 사용함
        //http로 최초1회 핸드셰이크하면 그 후론 알아서 ws 통신
        const socket = new SockJS("http://localhost:9100/api/chat");
        const client = Stomp.over(socket);

        client.connect({}, () => {
            //STOMP Client Kafka를 구독(메시지 브로커 매핑)
            //Kafka에선 converAndSend를 통해 STOMP의 메시지 브로킹을 함
            client.subscribe("/topic/group", (message) => {
                onMessageReceived(JSON.parse(message.body));
            });
        });

        setStompClient(client); //STOMP Client 설정

        return () => {
            client.disconnect();
        };
    }, []);

    //메시지 수신
    const onMessageReceived = (message) => {
        setMessages((prevMessages) => [...prevMessages, message]);
    };

    //메시지 송신 핸들러
    const handleMessageSubmit = (message) => {
        if (stompClient) {
            const msg = {
                author: currentUser.name,
                content: message,
            };
            //STOMP Client가 메시지 전달 -> Kafka template를 통해 Kafka 서버에 저장
            //이후 메시지를 구독한 Kafka topic에서 수신함
            stompClient.send("/kafka/message", {}, JSON.stringify(msg));
        } else {
            console.log("[STOMP 연결 안 됨]");
        }
    };

    return (
        <>
            <div className="header">
                <h4>효성에프엠에스 1기 톡방</h4>
            </div>
            <div className="content">
                <Chat messages={messages} currentUser={currentUser} />
                <Input handleOnSubmit={handleMessageSubmit} />
            </div>
        </>
    );
};

export default ChatRoom;
