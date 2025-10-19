// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import { useAuth } from '@/utils/auth';

// 导入视图组件
import HomeView from '@/views/HomeView.vue';
import LoginView from '@/views/LoginView.vue';
import SignupView from '@/views/SignupView.vue';
import ProductList from '@/views/ProductList.vue';
import ProductForm from '@/views/ProductForm.vue';
import OrderForm from '@/views/OrderForm.vue';

const routes = [
    { path: '/', name: 'Home', component: HomeView },
    { path: '/login', name: 'Login', component: LoginView },
    { path: '/signup', name: 'Signup', component: SignupView },
    {
        path: '/products',
        name: 'ProductList',
        component: ProductList,
        meta: { requiresAuth: true } // 此路由需要登录
    },
    {
        path: '/products/new',
        name: 'ProductNew',
        component: ProductForm,
        meta: { requiresAuth: true }
    },
    {
        path: '/products/edit/:id', // 动态路由匹配 ID
        name: 'ProductEdit',
        component: ProductForm,
        props: true, // 将路由参数 :id 作为 props 传递给组件
        meta: { requiresAuth: true }
    },
    {
        path: '/orders/new',
        name: 'OrderNew',
        component: OrderForm,
        meta: { requiresAuth: true }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

// 全局导航守卫
router.beforeEach((to, from, next) => {
    const { isLoggedIn } = useAuth();
    
    // 检查目标路由是否需要认证
    if (to.meta.requiresAuth && !isLoggedIn.value) {
        // 如果未登录，重定向到登录页
        next({ name: 'Login' });
    } else {
        // 否则，正常放行
        next();
    }
});

export default router;