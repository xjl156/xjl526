<template>
  <div class="page-title">数据大屏</div>
  <el-row :gutter="16">
    <el-col :span="6" v-for="card in cards" :key="card.label">
      <el-card shadow="hover">
        <div class="card-value">{{ card.value }}</div>
        <div class="card-label">{{ card.label }}</div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getDashboard } from '@/api/dashboard'

const cards = ref([
  { label: '今日用餐人数', value: '-' },
  { label: '今日收入', value: '-' },
  { label: '待结账订单', value: '-' },
  { label: '库存预警', value: '-' }
])

onMounted(async () => {
  try {
    const data = await getDashboard(1)
    cards.value = [
      { label: '今日用餐人数', value: data.todayCustomers + ' 人' },
      { label: '今日收入', value: '¥' + (data.todayRevenue || 0).toFixed(2) },
      { label: '待结账订单', value: data.pendingOrders + ' 单' },
      { label: '库存预警', value: data.lowStockItems + ' 项' }
    ]
  } catch (e) {}
})
</script>

<style scoped>
.card-value { font-size: 32px; font-weight: bold; color: #409EFF; }
.card-label { font-size: 14px; color: #666; margin-top: 8px; }
</style>
