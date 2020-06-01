import axios from 'axios';

const api = axios.create({
    baseURL: "https://api-gasosa.herokuapp.com",
});

export default api;