import * as constants from '../constants/constants.js'

export const getHotels = async (ownerId) => {
  let url = `${constants.API_URL}/hotels`

  if (ownerId) {
    url += `?ownerId=${ownerId}`
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

export const getHotel = async (hotelId) => {
  let url = `${constants.API_URL}/hotels/${hotelId}`

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


export const postHotel = async (hotel) => {
  let url = `${constants.API_URL}/hotels`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify(hotel),
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

export const putHotel = async (id, hotel) => {
  let url = `${constants.API_URL}/hotels/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'PUT',
    body: JSON.stringify(hotel),
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

export const deleteHotel = async (id) => {
  let url = `${constants.API_URL}/hotels/${id}`

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
