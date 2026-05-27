import request from './request'

export function getDishes(storeId) {
  return request({ url: `/dishes/store/${storeId}`, method: 'get' })
}

export function createDish(data) {
  return request({ url: '/dishes', method: 'post', data })
}

export function updateDish(id, data) {
  return request({ url: `/dishes/${id}`, method: 'put', data })
}

export function deleteDish(id) {
  return request({ url: `/dishes/${id}`, method: 'delete' })
}

export function getRecommendations(storeId) {
  return request({ url: `/dishes/recommendations/${storeId}`, method: 'get' })
}

export function toggleDishStatus(id) {
  return request({ url: `/dishes/${id}/toggle`, method: 'put' })
}
