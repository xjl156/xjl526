<template>
  <div class="ordering-page">
    <div class="page-title">点单</div>
    <el-row :gutter="16">
      <el-col :span="16">
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:12px">
          <el-tabs v-model="activeCategory" @tab-change="loadDishes" style="flex:1">
            <el-tab-pane v-for="cat in categories" :key="cat.id" :label="cat.name" :name="String(cat.id)" />
          </el-tabs>
          <el-button type="primary" size="small" @click="showAddCustomDish = true">+ 自定义菜品</el-button>
        </div>
        <el-row :gutter="12">
          <el-col v-for="dish in dishList" :key="dish.id" :span="8" style="margin-bottom: 12px">
            <el-card shadow="hover" :body-style="{ padding: '16px' }" class="dish-card" @click="addToCart(dish)">
              <div class="dish-name">{{ dish.dishName }}</div>
              <div class="dish-price">¥{{ dish.costPrice }}</div>
              <el-tag v-if="dish.status === 1" size="small" type="success">可售</el-tag>
              <el-tag v-else size="small" type="danger">已下架</el-tag>
            </el-card>
          </el-col>
        </el-row>
        <el-empty v-if="dishList.length === 0" description="该分类暂无菜品" :image-size="60" />
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="cart-card">
          <template #header>
            <div style="display: flex; justify-content: space-between; align-items: center">
              <span style="font-weight: 600">🛒 购物车</span>
              <div>
                <el-button type="primary" size="small" text @click="showAddCustomDish = true">+ 加菜</el-button>
                <el-button type="danger" size="small" text @click="clearCart">清空</el-button>
              </div>
            </div>
          </template>
          <div v-if="cart.length === 0" style="text-align: center; color: #999; padding: 40px 0">
            <p>点击菜品添加到购物车</p>
          </div>
          <div v-for="(item, idx) in cart" :key="item._key" class="cart-item">
            <div class="cart-item-info">
              <div class="cart-item-name">{{ item.dishName }}</div>
              <div class="cart-item-price">
                ¥<el-input v-model="item.unitPrice" size="small" style="width:80px; display:inline-block" @blur="recalcItem(idx)" />
              </div>
            </div>
            <div class="cart-item-actions">
              <el-button size="small" :icon="Minus" circle @click="decreaseQty(idx)" />
              <span class="cart-qty">{{ item.quantity }}</span>
              <el-button size="small" :icon="Plus" circle @click="increaseQty(idx)" />
              <el-button size="small" type="danger" :icon="Delete" circle @click="removeItem(idx)" style="margin-left: 4px" />
            </div>
          </div>
          <el-divider />
          <el-form size="small" label-width="70px">
            <el-form-item label="桌号">
              <el-input v-model="tableNo" placeholder="如 A01" />
            </el-form-item>
            <el-form-item label="人数">
              <el-input-number v-model="guestCount" :min="1" :max="50" style="width: 100%" />
            </el-form-item>
          </el-form>
          <el-divider />
          <div class="cart-total">
            <span>合计：</span>
            <span class="total-price">¥{{ cartTotal.toFixed(2) }}</span>
          </div>
          <el-button type="primary" size="large" style="width: 100%; margin-top: 12px" :disabled="cart.length === 0" :loading="submitting" @click="submitOrder">提交订单</el-button>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog v-model="showAddCustomDish" title="添加自定义菜品" width="400px">
      <el-form :model="customDishForm" label-width="80px" size="small">
        <el-form-item label="菜品名称">
          <el-input v-model="customDishForm.dishName" placeholder="如：蒜蓉粉丝蒸虾" />
        </el-form-item>
        <el-form-item label="单价 (¥)">
          <el-input-number v-model="customDishForm.unitPrice" :min="0" :precision="2" :step="1" style="width:100%" />
        </el-form-item>
        <el-form-item label="数量">
          <el-input-number v-model="customDishForm.quantity" :min="1" :max="999" style="width:100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button size="small" @click="showAddCustomDish = false">取消</el-button>
        <el-button size="small" type="primary" @click="addCustomDish">添加</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getDishes } from '@/api/dish'
import { createOrder } from '@/api/order'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Minus, Delete } from '@element-plus/icons-vue'

const storeId = 1
const categories = ref([{id:1,name:'热菜'},{id:2,name:'凉菜'},{id:3,name:'汤类'},{id:4,name:'烧烤'},{id:5,name:'寿司'},{id:6,name:'甜品'},{id:7,name:'水果'}])
const activeCategory = ref('1')
const dishList = ref([])
const cart = ref([])
const tableNo = ref('')
const guestCount = ref(2)
const submitting = ref(false)
const showAddCustomDish = ref(false)
const customDishForm = ref({ dishName: '', unitPrice: 0, quantity: 1 })
let keyCounter = Date.now()

const cartTotal = computed(() => cart.value.reduce((s, i) => s + Number(i.unitPrice || 0) * i.quantity, 0))

const loadDishes = async () => {
  try {
    const all = await getDishes(storeId)
    dishList.value = all.filter(d => d.categoryId === parseInt(activeCategory.value) && d.status === 1)
  } catch (e) {
    dishList.value = []
  }
}

const addToCart = (dish) => {
  const existing = cart.value.find(i => i.dishId === dish.id && !i._custom)
  if (existing) { existing.quantity++ }
  else { cart.value.push({ _key: ++keyCounter, dishId: dish.id, dishName: dish.dishName, unitPrice: dish.costPrice, quantity: 1, _custom: false }) }
}

const addCustomDish = () => {
  const form = customDishForm.value
  if (!form.dishName.trim() || form.unitPrice <= 0) return
  cart.value.push({ _key: ++keyCounter, dishId: null, dishName: form.dishName.trim(), unitPrice: Number(form.unitPrice), quantity: form.quantity, _custom: true })
  showAddCustomDish.value = false
  customDishForm.value = { dishName: '', unitPrice: 0, quantity: 1 }
}

const recalcItem = (idx) => { if (cart.value[idx].unitPrice < 0) cart.value[idx].unitPrice = 0 }
const increaseQty = (idx) => { cart.value[idx].quantity++ }
const decreaseQty = (idx) => { if (cart.value[idx].quantity <= 1) cart.value.splice(idx, 1); else cart.value[idx].quantity-- }
const removeItem = (idx) => cart.value.splice(idx, 1)
const clearCart = () => {
  ElMessageBox.confirm('确定清空购物车？', '提示', { type: 'warning' }).then(() => { cart.value = [] }).catch(() => {})
}

const submitOrder = async () => {
  if (!tableNo.value.trim()) { ElMessage.warning('请输入桌号'); return }
  submitting.value = true
  try {
    await createOrder({ storeId, tableNo: tableNo.value, guestCount: guestCount.value, items: cart.value.map(i => ({ dishId: i.dishId, dishName: i.dishName, quantity: i.quantity, unitPrice: Number(i.unitPrice) })) })
    ElMessage.success('订单已提交')
    cart.value = []; tableNo.value = ''
  } catch (e) { ElMessage.error('下单失败') }
  finally { submitting.value = false }
}

onMounted(() => loadDishes())
</script>

<style scoped>
.dish-card { cursor: pointer; transition: transform 0.15s; user-select: none; }
.dish-card:hover { transform: translateY(-2px); }
.dish-name { font-size: 15px; font-weight: 600; margin-bottom: 6px; }
.dish-price { color: #F56C6C; font-size: 18px; font-weight: bold; }
.cart-item { display: flex; justify-content: space-between; align-items: center; padding: 8px 0; border-bottom: 1px solid #f0f0f0; }
.cart-item-name { font-size: 14px; font-weight: 500; }
.cart-item-price { color: #F56C6C; font-size: 14px; }
.cart-item-actions { display: flex; align-items: center; gap: 4px; }
.cart-qty { font-size: 16px; font-weight: 600; min-width: 24px; text-align: center; }
.cart-total { display: flex; justify-content: space-between; align-items: center; }
.total-price { color: #F56C6C; font-size: 24px; font-weight: bold; }
</style>
