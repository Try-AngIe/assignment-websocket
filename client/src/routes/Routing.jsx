/* eslint-disable */

import { Route, Routes } from "react-router-dom"
import ChatRoom from "../pages/ChatRoom"
import Login from './../pages/Login';
import Join from './../pages/Join';

const Routing = () => {

    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/chatroom" element={<ChatRoom />} />
            <Route path="/join" element={<Join/>} />
        </Routes>
    )

}

export default Routing;