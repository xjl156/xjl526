import request from './request'

export function getDashboard(storeId) {
  return request({ url: `/dashboard/store/${storeId}`, method: 'get' })
}
