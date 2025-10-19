// src/utils/auth.js
import { ref, computed } from 'vue';

const TOKEN_KEY = 'jwt_token';

// 响应式的 token 引用，从 localStorage 初始化
const token = ref(localStorage.getItem(TOKEN_KEY));

/**
 * 保存 token 到 localStorage 和响应式引用中
 * @param {string} newToken 
 */
function saveToken(newToken) {
    localStorage.setItem(TOKEN_KEY, newToken);
    token.value = newToken;
}

/**
 * 从 localStorage 和响应式引用中移除 token
 */
function removeToken() {
    localStorage.removeItem(TOKEN_KEY);
    token.value = null;
}

/**
 * 获取当前的 token
 * @returns {string | null}
 */
function getToken() {
    return token.value;
}

// 一个计算属性，用于判断用户是否登录
const isLoggedIn = computed(() => !!token.value);

/**
 * 导出一个 Composable 函数
 */
export function useAuth() {
    return {
        saveToken,
        getToken,
        removeToken,
        isLoggedIn,
        token
    };
}