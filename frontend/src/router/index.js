import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/MainLayout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/Index.vue'),
        meta: { title: '数据大屏', icon: 'DataBoard' }
      },
      {
        path: 'ordering',
        name: 'Ordering',
        component: () => import('@/views/ordering/Index.vue'),
        meta: { title: '点单', icon: 'Edit' }
      },
      {
        path: 'cashier',
        name: 'Cashier',
        component: () => import('@/views/cashier/Index.vue'),
        meta: { title: '收银', icon: 'Money' }
      },
      {
        path: 'inventory',
        name: 'Inventory',
        component: () => import('@/views/inventory/Index.vue'),
        meta: { title: '库存管理', icon: 'Box' }
      },
      {
        path: 'purchase',
        name: 'Purchase',
        component: () => import('@/views/purchase/Index.vue'),
        meta: { title: '采购建议', icon: 'ShoppingCart' }
      },
      {
        path: 'dishes',
        name: 'Dishes',
        component: () => import('@/views/dish/Index.vue'),
        meta: { title: '菜品管理', icon: 'CoffeeCup' }
      },
      {
        path: 'reports',
        name: 'Reports',
        component: () => import('@/views/reports/Index.vue'),
        meta: { title: '报表中心', icon: 'DataAnalysis' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
export { routes }
