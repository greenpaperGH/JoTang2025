<template>
  <div>
    <h1>欢迎来到 GreenPaperUJ 前端</h1>
    <p v-if="isLoggedIn">您已登录。</p>
    <p v-else>请 <router-link to="/login">登录</router-link> 或 <router-link to="/signup">注册</router-link>。</p>
  </div>
</template>

<script setup>
import { useAuth } from '@/utils/auth';
const { isLoggedIn } = useAuth();
</script>