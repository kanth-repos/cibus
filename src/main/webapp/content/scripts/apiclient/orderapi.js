import * as constants from '../constants/constants.js'

export const getOrders = async (param) => {
  let url = `${constants.API_URL}/orders`

  if (param.hotelId) {
    url += `?hotelId=${param.hotelId}`
  }

  if (param.userId) {
    url += `?userId=${param.userId}`
  }

  let res = await fetch(url, {
    credentials: 'include',
    method: 'GET',
    headers: {
      'Accept': 'application/json'
    }
  })

  if (res.status != 200) {
    throw {
      error: new Error(res.statusText),
      res: res
    }
  }

  return res.json()
}

export const postOrder = async (order) => {
  let url = `${constants.API_URL}/orders`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify(order),
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  })

  if (res.status != 200) {
    throw {
      error: new Error(res.statusText),
      res: res
    }
  }

  return res.json()
}

export const deleteOrder = async (id) => {
  let url = `${constants.API_URL}/orders/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'DELETE',
    headers: {
      'Accept': 'application/json'
    }
  })

  if (res.status != 200) {
    throw {
      error: new Error(res.statusText),
      res: res
    }
  }

  return res.json()
}
