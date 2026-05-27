import request from './request'

export function getProfitAnalysis(storeId) {
  return request({ url: `/reports/profit/${storeId}`, method: 'get' })
}

export function getPurchasePrediction(storeId) {
  return request({ url: `/purchase/predict/${storeId}`, method: 'get' })
}
