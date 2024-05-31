/* eslint-disable */

import { useState } from "react";
import Chat from "../components/Chat";
import Input from "../components/Input";
import ChatApi from "../services/ChatApi";
import { StompSessionProvider, useSubscription } from "react-stomp-hooks";
import { useLocation } from "react-router-dom";

const ChatRoom = () => {

    //유즈로케이션
    const location = useLocation();

     // 로그인한 유저 정보 받아오기
    const currentUser = location.state?.user;

    console.log("[currentUser]", currentUser);

    //메시지들 상태저장
    const [messages, setMessages] = useState([]);

    // 메시지 받기
    const onMessageReceived = (message) => {
        setMessages((prevMessages) => [...prevMessages, message]);
    };

    // 메시지 전송
    const handleMessageSubmit = (message) => {
        ChatApi.sendMessage(currentUser.name, message)
            .then((res) => {
                console.log("[보낸 메시지]", res);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    // 카프카 메시지 큐잉
    const Subscription = ({ onMessageReceived }) => {
        useSubscription("/topic/group", (message) => {
            onMessageReceived(JSON.parse(message.body));
        });
        return null;
    };

    return (
        <>
            <div className="header">
                <h4>효성에프엠에스 1기 톡방</h4>
            </div>
            <div className="content">
                <StompSessionProvider
                    url="http://localhost:9100/my-chat"
                    onConnect={() => console.log("on")}
                    onDisconnect={() => console.log("off")}
                >
                    <Subscription onMessageReceived={onMessageReceived} />
                    <Chat messages={messages} currentUser={currentUser} />
                    <Input handleOnSubmit={handleMessageSubmit} />
                </StompSessionProvider>
            </div>
        </>
    );
};

export default ChatRoom;