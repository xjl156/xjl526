import request from './request'

export function createOrder(data) {
  return request({ url: '/orders', method: 'post', data })
}

export function getOrder(id) {
  return request({ url: `/orders/${id}`, method: 'get' })
}

export function getStoreOrders(storeId) {
  return request({ url: `/orders/store/${storeId}`, method: 'get' })
}

export function payOrder(id, data) {
  return request({ url: `/orders/${id}/pay`, method: 'put', data })
}

export function cancelOrder(id) {
  return request({ url: `/orders/${id}/cancel`, method: 'put' })
}
