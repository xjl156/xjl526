<template>
  <el-container class="layout-container">
    <el-header class="layout-header">
      <div class="logo">小徐同学 - 餐饮智能管理</div>
      <el-menu mode="horizontal" :router="true" :default-active="route.path">
        <el-menu-item v-for="menu in menus" :key="menu.path" :index="menu.path">
          {{ menu.title }}
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { routes } from '@/router'

const route = useRoute()
const menus = routes[0].children
</script>

<style scoped>
.layout-container { min-height: 100vh; }
.layout-header { display: flex; align-items: center; padding: 0 20px; background: #fff; border-bottom: 1px solid #e0e0e0; }
.logo { font-size: 18px; font-weight: bold; color: #409EFF; margin-right: 40px; white-space: nowrap; }
</style>
