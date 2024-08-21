import * as constants from '../constants/constants.js'


export const getRatingByFoodAndUser = async (foodId, userId) => {
  let url = `${constants.API_URL}/ratings?foodId=${foodId}&userId=${userId}`

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

export const getRating = async (id) => {
  let url = `${constants.API_URL}/ratings/${id}`;

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

export const getRatings = async (foodId) => {
  let url = `${constants.API_URL}/ratings?foodId=${foodId}`

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

export const postRating = async (rating) => {
  let url = `${constants.API_URL}/ratings`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify(rating),
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

export const putRating = async (id, rating) => {
  let url = `${constants.API_URL}/ratings/${id}`

  let res = await fetch(url, {
    credentials: 'include',
    method: 'PUT',
    body: JSON.stringify(rating),
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

export const deleteRating = async (id) => {
  let url = `${constants.API_URL}/ratings/${id}`

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
