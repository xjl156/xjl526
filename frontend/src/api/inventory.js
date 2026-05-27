import request from './request'

export function getInventory(storeId) {
  return request({ url: `/inventory/store/${storeId}`, method: 'get' })
}
