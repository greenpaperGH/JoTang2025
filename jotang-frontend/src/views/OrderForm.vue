<template>
  <div>
    <h2>创建新订单</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="productId">商品 ID:</label>
        <input type="number" v-model.number="order.productId" id="productId" required>
      </div>
      <div>
        <label for="buyerId">购买者 ID:</label>
        <input type="number" v-model.number="order.buyerId" id="buyerId" required>
      </div>
      <div>
        <label for="status">状态:</label>
        <input type="text" v-model="order.status" id="status" required>
      </div>

      <button type="submit">创建订单</button>
      <p v-if="error" style="color: red;">{{ error }}</p>
      <p v-if="success" style="color: green;">{{ success }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import apiClient from '@/services/apiClient';

const order = reactive({
  productId: null,
  buyerId: null,
  status: 'pending' // 默认状态
});
const error = ref(null);
const success = ref(null);

const handleSubmit = async () => {
  error.value = null;
  success.value = null;
  try {
    const result = await apiClient('/product-orders', 'POST', order);
    if (result.code === 1) {
      success.value = '订单创建成功！';
      // 重置表单
      order.productId = null;
      order.buyerId = null;
      order.status = 'pending';
    } else {
      error.value = result.msg;
    }
  } catch (err) {
    error.value = err.message;
  }
};
</script>