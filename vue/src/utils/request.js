import { ElMessage } from 'element-plus'
import router from '../router'
import axios from "axios";

const request = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 30000  // timeout for the background API
})

// request interceptor
// pre-process on request before sending promise
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
});

// response interceptor
// uniformly processed after the interface responds
request.interceptors.response.use(
    response => {
        let res = response.data;
        // if return files
        if (response.config.responseType === 'blob') {
            return res
        }
        // return String data
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // show message when permission verification fails
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push("/login")
        }
        return res;
    },
        error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)


export default request
