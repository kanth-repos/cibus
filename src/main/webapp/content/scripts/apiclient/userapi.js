import * as constants from '../constants/constants.js';

export const getUser = async (id) => {
  let url = `${constants.API_URL}/users`;

  if(id) {
    url += `/${id}`;
  }

  let res = await fetch(url, {
    credentials: 'include',
    method: 'GET',
    headers: {
      'Accept': 'application/json'
    }
  });

  if (!res.ok) {
    throw new Error(`res.statusText : ${res.statusText}`);
  }

  return res.json();
}
