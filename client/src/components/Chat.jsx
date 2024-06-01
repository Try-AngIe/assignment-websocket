/* eslint-disable */

import "../styles/Chat.css";

import { useRef, useEffect } from "react";

const Chat = ({ messages, currentUser }) => {

    //스크롤용 유즈레프
    const chatEndRef = useRef(null);

    //스크롤 자동 최하단
    useEffect(() => {
        chatEndRef.current.scrollIntoView({ behavior: "smooth" });
    }, [messages]);

    //시간 형식 설정
    const formattingTimestamp = (timestamp) => {
    const date = new Date(timestamp);
    let meridiem = "오전";
    let hour = date.getHours();
    if (hour >= 12) {
        meridiem = "오후";
        if (hour > 12) {
            hour -= 12;
        }
    } else if (hour === 0) {
        hour = 12;
    }
    hour = hour < 10 ? `0${hour}` : hour;
    let min = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes();
    return `${meridiem} ${hour}:${min}`;
};


    return (
        <div className="chat-div">
            {messages.map((msg) => (
                <li
                    className={`chat-message ${
                        msg.author === currentUser.name ? "sent" : "received"
                    }`}
                >
                    <div className="chat-info">
                        <div className="chat-username">
                            <p>{msg.author}</p>
                        </div>
                        <div className="chat-message-content">
                            <p>{msg.content}</p>
                        </div>
                    </div>
                    <div className="message-time">
                        <span>{formattingTimestamp(msg.timestamp)}</span>
                    </div>
                </li>
            ))}
            <div ref={chatEndRef}></div>
        </div>
    );
};

export default Chat;