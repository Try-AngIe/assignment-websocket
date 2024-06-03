/* eslint-disable */

import "../styles/Login.css"
import logo from "../assets/logo.png"

import { loginApi } from "../services/AuthApi";

import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {

    //유즈네비게이트
    const navigate = useNavigate();

    //유저 상태저장
    const [userId, setUserId] = useState("");
    const [userPassword, setUserPassword] = useState("");

    //유저 상태변경 핸들러
    const handleIdChange = (e) => {
        setUserId(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setUserPassword(e.target.value);
    };

    //로그인 핸들러
    const handleLogin = async () => {
        try {
            const res = await loginApi(userId, userPassword);
            console.log(res.data);
            if (res.status == 200) {
                console.log("로그인 성공");
                 navigate('/chatroom', {
                state: { user: { name: userId } }
                });
            }
        } catch (err) {
            console.log(err)
        }
    };

    return (
        <div className="login-container">
            <div className="logo-div">
                <img src={logo}></img>
            </div>
            <h1>로그인</h1>
            <div className="login-div">
                <input
                    className="login-input"
                    placeholder="아이디를 입력하세요."
                    value={userId}
                    onChange={handleIdChange}
                />
                <input
                    type="password"
                    className="login-input"
                    placeholder="비밀번호를 입력하세요."
                    value={userPassword}
                    onChange={handlePasswordChange}
                />
                <button className="login-button" type="button" onClick={handleLogin}>로그인</button>
                <button className="join-button" type="button" onClick={() => navigate("/join")}>회원가입</button>
            </div>
        </div>
    );

};

export default Login;
