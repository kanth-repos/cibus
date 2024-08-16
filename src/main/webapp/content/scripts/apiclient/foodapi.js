import * as constants from '../constants/constants.js'

export const getFoods = async (hotelId) => {
  let url = `${constants.API_URL}/foods`

  if (hotelId) {
    url += `?hotelId=${hotelId}`
  } else {
    throw new Error("Need hotel Id");
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

export const postFood = async (food) => {
  let url = `${constants.API_URL}/foods`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify(food),
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

export const putFood = async (id, food) => {
  let url = `${constants.API_URL}/foods/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'PUT',
    body: JSON.stringify(food),
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

export const deleteFood = async (id) => {
  let url = `${constants.API_URL}/foods/${id}`

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
