import * as constants from '../constants/constants.js'

export const getCarts = async (userId) => {
  let url = `${constants.API_URL}/carts`

  if (userId) {
    url += `?userId=${userId}`
  } else {
    throw new Error("Need User Id");
  }

  let res = await fetch(url, {
    credentials: 'include',
    method: 'GET',
    headers: {
      'Accept': 'application/json'
    }
  })

  if (!res.ok) {
    throw new Error(`res.statusText : ${res.statusText}`)
  }

  return res.json()
}

export const postCart = async (cart) => {
  let url = `${constants.API_URL}/carts`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify(cart),
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  })

  if (!res.ok) {
    throw new Error(`res.statusText : ${res.statusText}`)
  }

  return res.json()
}

export const putCart = async (id, cart) => {
  let url = `${constants.API_URL}/carts/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'PUT',
    body: JSON.stringify(cart),
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  })

  if (!res.ok) {
    throw new Error(`res.statusText : ${res.statusText}`)
  }

  return res.json()
}

export const deleteCart = async (id) => {
  let url = `${constants.API_URL}/carts/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'DELETE',
    headers: {
      'Accept': 'application/json'
    }
  })

  if (!res.ok) {
    throw new Error(`res.statusText : ${res.statusText}`)
  }

  return res.json()
}
