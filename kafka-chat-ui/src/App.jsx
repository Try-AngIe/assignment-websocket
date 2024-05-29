/* eslint-disable */

import { useState } from "react";
import Login from "./components/Login";
import Chat from "./components/Chat";
import Input from "./components/Input";
import ChatApi from "./services/ChatApi";
import { StompSessionProvider, useSubscription } from "react-stomp-hooks";

const App = () => {
    const [messages, setMessages] = useState([]);
    const [user, setUser] = useState(null);

    const onMessageReceived = (msg) => {
        console.log("New Message Received!!", msg);
        setMessages((prevMessages) => [...prevMessages, msg]);
    };

    const handleLoginSubmit = (name) => {
        setUser({ name: name, color: randomColor() });
    };

    const handleMessageSubmit = (msg) => {
        ChatApi.sendMessage(user.name, msg)
            .then((res) => {
                console.log("sent", res);
            })
            .catch((e) => {
                console.log(e);
            });
    };

    const randomColor = () => {
        return "#" + Math.floor(Math.random() * 0xffffff).toString(16);
    };

    return (
        <>
            {user !== null ? (
                <StompSessionProvider
                    url="http://localhost:9090/my-chat"
                    onConnect={() => console.log("connected!")}
                    onDisconnect={() => console.log("disconnected!")}
                >
                    <SubscriptionWrapper
                        onMessageReceived={onMessageReceived}
                    />
                    <Chat messages={messages} currentUser={user} />
                    <Input handleOnSubmit={handleMessageSubmit} />
                </StompSessionProvider>
            ) : (
                <Login handleOnSubmit={handleLoginSubmit} />
            )}
        </>
    );
}

const SubscriptionWrapper = ({ onMessageReceived }) => {
    useSubscription("/topic/group", (message) => {
        onMessageReceived(JSON.parse(message.body));
    });
    return null;
}

export default App;
