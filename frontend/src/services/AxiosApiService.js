import axios from "axios";

const API_BASE_URL = "http://localhost:3000";

const api = axios.create({
    baseURL: API_BASE_URL,
});

export const get = (url) => api.get(url).then((res) => res.data);
export const post = (url, data) => api.post(url, data).then((res) => res.data);
export const put = (url, data) => api.put(url, data).then((res) => res.data);
export const del = (url) => api.delete(url).then((res) => res.data);
