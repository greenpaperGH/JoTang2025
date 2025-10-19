// src/services/apiClient.js
import { useAuth } from '@/utils/auth';

// 假设您的后端运行在此地址
const BASE_URL = 'http://localhost:8080';

/**
 * 封装的 fetch 客户端
 * @param {string} endpoint API 路径 (例如 /products)
 * @param {string} method HTTP 方法 (GET, POST, PUT, DELETE)
 * @param {object | null} data 要发送的数据 (用于 POST/PUT)
 * @returns {Promise<object>} 返回后端 Result 对象的 data 字段
 */
async function apiClient(endpoint, method = 'GET', data = null) {
    const { getToken } = useAuth();
    const token = getToken();

    const config = {
        method: method,
        headers: {
            // 默认设置为 JSON
            'Content-Type': 'application/json',
        }
    };

    // **按照您的要求，将 JWT 添加到 'token' 头**
    if (token) {
        config.headers['token'] = token;
    }

    if (data) {
        // 特例：LoginController 需要 x-www-form-urlencoded
        if (method === 'POST' && endpoint === '/login') {
            config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
            // 将 {username: 'a', password: 'b'} 转换为 "username=a&password=b"
            config.body = new URLSearchParams(data).toString();
        } 
        // 其他 POST/PUT 请求使用 JSON
        else if (method === 'POST' || method === 'PUT') {
            config.body = JSON.stringify(data);
        }
    }

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);
        
        // 解析后端的 Result 对象
        const responseData = await response.json();

        // 检查后端的业务逻辑是否成功
        if (!response.ok || responseData.code === 0) {
            throw new Error(responseData.msg || 'API 请求失败');
        }

        // 返回完整的 Result 对象
        return responseData;

    } catch (error) {
        console.error('API Client Error:', error);
        // 将错误抛出，以便组件可以捕获它
        throw error;
    }
}

export default apiClient;