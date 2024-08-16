import * as foodsApi from '../apiclient/foodapi.js'
import * as constants from "../constants/constants.js"

const onSubmit = async (event) => {
  let name = $("#nameInput").val()
  let city = $("#priceInput").val()

  event.preventDefault()

  let food = {
    name: name,
    city: city,
    hotelId: hotelId
  }

  await foodsApi.postFood(food)
  window.location.reload()
}

$(() => {
  $("#createHotelForm").on('submit', onSubmit)
})
