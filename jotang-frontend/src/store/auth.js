// src/store/auth.js
import { ref, computed } from 'vue'

const token = ref(localStorage.getItem('token'))
const isAuthenticated = computed(() => !!token.value)

function saveToken(newToken) {
    token.value = newToken
    localStorage.setItem('token', newToken)
}

function clearToken() {
    token.value = null
    localStorage.removeItem('token')
}

export function useAuth() {
    return { token, isAuthenticated, saveToken, clearToken }
}