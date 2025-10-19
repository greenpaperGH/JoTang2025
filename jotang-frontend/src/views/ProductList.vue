<template>
  <div>
    <h2>商品列表</h2>
    <router-link to="/products/new" class="add-button">添加新商品</router-link>
    
    <form @submit.prevent="handleSearch" class="search-form">
      <input type="text" v-model="filters.type" placeholder="类型">
      <input type="number" v-model.number="filters.minPrice" placeholder="最低价">
      <input type="number" v-model.number="filters.maxPrice" placeholder="最高价">
      <button type="submit">搜索</button>
      <button @click="loadProducts(true)" type="button">重置</button>
    </form>

    <p v-if="loading">正在加载...</p>
    <p v-if="error" style="color: red;">{{ error }}</p>

    <table v-if="products.length > 0">
      <thead>
        <tr>
          <th>标题</th>
          <th>类型</th>
          <th>价格</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.title }}</td>
          <td>{{ product.type }}</td>
          <td>{{ product.price }}</td>
          <td>{{ product.status }}</td>
          <td>
            <router-link :to="{ name: 'ProductEdit', params: { id: product.id } }">编辑</router-link>
            <button @click="handleDelete(product.id)" class="delete-button">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p v-else>未找到商品。</p>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import apiClient from '@/services/apiClient';

const products = ref([]);
const loading = ref(false);
const error = ref(null);

const filters = reactive({
  type: '',
  minPrice: null,
  maxPrice: null
});

// 加载所有商品 (GET /products)
const loadProducts = async (resetFilters = false) => {
  loading.value = true;
  error.value = null;
  if (resetFilters) {
      filters.type = '';
      filters.minPrice = null;
      filters.maxPrice = null;
  }
  try {
    const result = await apiClient('/products', 'GET');
    if (result.code === 1) {
      products.value = result.data;
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

// 搜索 (GET /products/by-condition)
const handleSearch = async () => {
  loading.value = true;
  error.value = null;
  try {
    // 构建查询参数
    const params = new URLSearchParams();
    if (filters.type) params.append('type', filters.type);
    if (filters.minPrice) params.append('minPrice', filters.minPrice);
    if (filters.maxPrice) params.append('maxPrice', filters.maxPrice);
    
    const endpoint = `/products/by-condition?${params.toString()}`;
    const result = await apiClient(endpoint, 'GET');

    if (result.code === 1) {
      products.value = result.data;
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

// 删除 (DELETE /products/{id})
const handleDelete = async (id) => {
  if (!confirm('确定要删除这个商品吗？')) {
    return;
  }
  try {
    const result = await apiClient(`/products/${id}`, 'DELETE');
    if (result.code === 1) {
      // 删除成功后重新加载列表
      loadProducts();
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  }
};

// 组件挂载时加载数据
onMounted(loadProducts);
</script>

<style scoped>
.search-form { margin: 20px 0; background: #f9f9f9; padding: 15px; border-radius: 5px; }
.search-form input, .search-form button { margin-right: 10px; }
.add-button { text-decoration: none; background: #42b983; color: white; padding: 8px 12px; border-radius: 4px; }
table { width: 100%; border-collapse: collapse; margin-top: 20px; }
th, td { border: 1px solid #ddd; padding: 8px; }
th { background-color: #f4f4f4; }
.delete-button { background: #e74c3c; color: white; border: none; padding: 5px; cursor: pointer; margin-left: 10px; }
</style>