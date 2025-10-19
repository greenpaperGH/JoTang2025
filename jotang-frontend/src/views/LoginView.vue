<template>
  <div>
    <h2>登录</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="username">用户名:</label>
        <input type="text" v-model="username" id="username" required>
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" v-model="password" id="password" required>
      </div>
      <button type="submit">登录</button>
      <p v-if="error" style="color: red;">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuth } from '@/utils/auth';
import apiClient from '@/services/apiClient';

const username = ref('');
const password = ref('');
const error = ref(null);

const router = useRouter();
const { saveToken } = useAuth();

const handleSubmit = async () => {
  error.value = null;
  try {
    // 对应 LoginController 的 x-www-form-urlencoded
    const formData = {
      username: username.value,
      password: password.value
    };
    
    const result = await apiClient('/login', 'POST', formData);

    if (result.code === 1 && result.data) {
      saveToken(result.data); // result.data 是 JWT
      router.push({ name: 'ProductList' }); // 登录成功，跳转到商品页
    } else {
      error.value = result.msg || '登录失败。';
    }
  } catch (err) {
    error.value = err.message || '发生错误。';
  }
};
</script>