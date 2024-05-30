import axios from "axios";

const kafkaApi = axios.create({
    baseURL: "http://localhost:9100/kafka",
});

const ChatApi = {

    //메시지 수신
    getMessages: (groupId) => {
        return kafkaApi.get(`/messages/${groupId}`);
    },

    //메시지 송신
    sendMessage: (username, text) => {
        let msg = {
            author: username,
            content: text,
        };
        return kafkaApi.post(`/publish`, msg, {
            headers: { "Content-Type": "application/json" },
        });
    },
};

export default ChatApi;