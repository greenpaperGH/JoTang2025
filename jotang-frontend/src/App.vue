<template>
  <div id="app-layout">
    <nav>
      <router-link to="/">首页</router-link> |
      <router-link to="/products">商品列表</router-link> |
      <router-link to="/orders/new">新建订单</router-link> |

      <span v-if="!isLoggedIn" class="auth-links">
        <router-link to="/login">登录</router-link> |
        <router-link to="/signup">注册</router-link>
      </span>
      <span v-else class="auth-links">
        <button @click="handleLogout">登出</button>
      </span>
    </nav>
    
    <main>
      <router-view />
    </main>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAuth } from '@/utils/auth';

const { isLoggedIn, removeToken } = useAuth();
const router = useRouter();

const handleLogout = () => {
  removeToken();
  router.push({ name: 'Login' });
};
</script>

<style>
/* 添加一些基本样式 */
body { font-family: sans-serif; margin: 0; }
nav { 
  padding: 20px; 
  background: #f4f4f4; 
  border-bottom: 1px solid #ddd; 
  display: flex;
  align-items: center;
}
nav a, nav button { 
  font-weight: bold; 
  color: #2c3e50; 
  margin: 0 10px;
  text-decoration: none;
}
nav a.router-link-exact-active { color: #42b983; }
nav button { 
  background: none; 
  border: none; 
  cursor: pointer; 
  padding: 0; 
  font-size: 1em;
}
.auth-links { margin-left: auto; }
main { padding: 20px; }
form div { margin-bottom: 15px; }
label { display: block; margin-bottom: 5px; font-weight: bold; }
input[type="text"],
input[type="password"],
input[type="number"],
input[type="tel"],
textarea {
  width: 300px;
  padding: 8px;
  box-sizing: border-box;
}
</style>