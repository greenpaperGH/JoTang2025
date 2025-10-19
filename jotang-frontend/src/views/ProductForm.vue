<template>
  <div>
    <h2 v-if="isEditMode">编辑商品</h2>
    <h2 v-else>添加新商品</h2>
    
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="title">标题:</label>
        <input type="text" v-model="product.title" id="title" required>
      </div>
      <div>
        <label for="description">描述:</label>
        <textarea v-model="product.description" id="description"></textarea>
      </div>
      <div>
        <label for="type">类型:</label>
        <input type="text" v-model="product.type" id="type" required>
      </div>
      <div>
        <label for="price">价格:</label>
        <input type="number" step="0.01" v-model.number="product.price" id="price" required>
      </div>
      <div>
        <label for="status">状态:</label>
        <input type="text" v-model="product.status" id="status" required>
      </div>
      <div v-if="!isEditMode">
        <label for="publisherId">发布者 ID:</label>
        <input type="number" v-model.number="product.publisherId" id="publisherId" required>
      </div>
      
      <button type="submit">{{ isEditMode ? '更新' : '创建' }}</button>
      <p v-if="error" style="color: red;">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed, defineProps } from 'vue';
import { useRouter } from 'vue-router';
import apiClient from '@/services/apiClient';

// 从路由获取 props (在 router/index.js 中定义了 props: true)
const props = defineProps({
  id: {
    type: String,
    default: null
  }
});

const router = useRouter();
const product = reactive({
  id: null,
  title: '',
  description: '',
  type: '',
  price: 0.0,
  status: 'available',
  publisherId: null 
});
const error = ref(null);

// 计算属性，判断当前是编辑模式还是新建模式
const isEditMode = computed(() => !!props.id);

// (GET /products/{id})
const loadProductData = async () => {
  if (!isEditMode.value) return; // 如果是新建模式，则跳过加载
  try {
    const result = await apiClient(`/products/${props.id}`, 'GET');
    if (result.code === 1) {
      // 将获取到的数据填充到表单
      Object.assign(product, result.data);
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  }
};

// 提交表单 (POST /products 或 PUT /products)
const handleSubmit = async () => {
  error.value = null;
  try {
    let result;
    if (isEditMode.value) {
      // 更新 (PUT)
      result = await apiClient('/products', 'PUT', product);
    } else {
      // 创建 (POST)
      result = await apiClient('/products', 'POST', product);
    }

    if (result.code === 1) {
      router.push({ name: 'ProductList' }); // 成功后返回列表页
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  }
};

// 组件挂载时，如果是编辑模式，则加载商品数据
onMounted(loadProductData);
</script>