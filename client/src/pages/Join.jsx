/* eslint-disable */

import "../styles/Login.css"
import logo from "../assets/logo.png"

import { joinApi } from "../services/AuthApi";

import { useState } from "react";
import { useNavigate } from "react-router-dom";


const Join = () => {

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

    //회원가입 핸들러
    const handleJoin = async () => {
        try {
            if ((await joinApi(userId, userPassword)).status == 201) {
                console.log("회원가입 성공");
                navigate("/")
            }
        } catch (err) {
            console.log(err)
        }
    }

    return (
        <div className="login-container">
            <div className="logo-div">
                <img src={logo}></img>
            </div>
            <h1>회원가입</h1>
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
                <button className="login-button" type="button" onClick={handleJoin}>회원가입</button>
                <button className="join-button" type="button" onClick={() => navigate("/")}>취소</button>
            </div>
        </div>
    )
    
}

export default Join;