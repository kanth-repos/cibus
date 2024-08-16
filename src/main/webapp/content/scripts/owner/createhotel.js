import * as hotelApi from "../apiclient/hotelapi.js"
import * as userApi from "../apiclient/userapi.js"
import * as constants from "../constants/constants.js"

const onSubmit = async (event) => {
  let name = $("#nameInput").val()
  let city = $("#cityInput").val()

  event.preventDefault()

  let user = await userApi.getUser()

  let hotel = {
    name: name,
    city: city,
    ownerId: user.id
  }

  await hotelApi.postHotel(hotel)
  window.location = `${constants.BASE_URL}/dashboard`
}

$(() => {
  $("#createHotelForm").on('submit', onSubmit)
})

