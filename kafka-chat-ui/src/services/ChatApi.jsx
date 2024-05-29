import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:9090/kafka",
});

const ChatApi = {
    getMessages: (groupId) => {
        console.log("[API 호출해서 메시지 가져옴]");
        return api.get(`/messages/${groupId}`);
    },

    sendMessage: (username, text) => {
        let msg = {
            author: username,
            content: text,
        };
        return api.post(`/publish`, msg, {
            headers: { "Content-Type": "application/json" },
        });
    },
};

export default ChatApi;
