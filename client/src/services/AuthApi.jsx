import axios from "axios";

axios.defaults.withCredentials = true;

export const joinApi = (userId, userPassword) => {
    
    let data = JSON.stringify({
        "username": userId,
        "password": userPassword
    });

    let config = {
        method: 'post',
        url: 'http://localhost:9100/api/join',
        headers: { 
            'Content-Type': 'application/json', 
        },
        data : data
    };

    return axios.request(config);

} 

export const loginApi = (userId, userPassword) => {

    let data = JSON.stringify({
        "username": userId,
        "password": userPassword
    });

    let config = {
        method: 'post',
        url: 'http://localhost:9100/api/login',
        headers: { 
            'Content-Type': 'application/json'
        },
        data : data
    };

    return axios.request(config)

}