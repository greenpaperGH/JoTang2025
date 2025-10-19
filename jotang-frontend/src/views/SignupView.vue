<template>
  <div>
    <h2>注册新用户</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="username">用户名:</label>
        <input type="text" v-model="user.username" id="username" required>
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" v-model="user.password" id="password" required>
      </div>
      <div>
        <label for="role">角色 (例如: buyer, publisher):</label>
        <input type="text" v-model="user.role" id="role" required>
      </div>
      <div>
        <label for="phone">电话:</label>
        <input type="tel" v-model="user.phone" id="phone">
      </div>
      <button type="submit">注册</button>
      <p v-if="error" style="color: red;">{{ error }}</p>
      <p v-if="success" style="color: green;">{{ success }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import apiClient from '@/services/apiClient';

const router = useRouter();
// 使用 reactive 来处理表单对象
const user = reactive({
  username: '',
  password: '',
  role: 'buyer', // 默认角色
  phone: ''
});
const error = ref(null);
const success = ref(null);

const handleSubmit = async () => {
  error.value = null;
  success.value = null;
  try {
    // 对应 SignupController 的 @RequestBody，apiClient 会发送 JSON
    const result = await apiClient('/signup', 'POST', user);

    if (result.code === 1) {
      success.value = '注册成功！即将跳转到登录页...';
      setTimeout(() => {
        router.push({ name: 'Login' });
      }, 2000);
    } else {
      error.value = result.msg || '注册失败。';
    }
  } catch (err) {
    error.value = err.message || '发生错误。';
  }
};
</script>