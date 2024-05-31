/* eslint-disable */

import "../styles/Input.css";

import { useState } from "react";

const Input = ({ handleOnSubmit }) => {

    //메시지 상태저장
    const [message, setMessage] = useState("");

    //메시지 상태변경 핸들러
    const handleOnChange = (e) => {
        setMessage(e.target.value);
    };

    //메시지 전송 핸들러
    const handleSubmit = async (e) => {
        e.preventDefault();
        handleOnSubmit(message);
        setMessage(""); //보냈으면 초기화
    };

    return (
        <div className="input-div">
            <form onSubmit={handleSubmit}>
                <input
                    // placeholder="내용을 입력하세요."
                    value={message}
                    onChange={handleOnChange}
                    onKeyDown={(e) => {
                        if (e.key === "Enter") { //엔터로도 가능
                            handleSubmit(e);
                        }
                    }}
                />
                <button type="submit">전송</button>
            </form>
        </div>
    );
};

export default Input;