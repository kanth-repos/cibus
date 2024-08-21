import * as foodsApi from '../apiclient/foodapi.js'
import * as constants from "../constants/constants.js"

const onSubmit = async (event) => {
  let hotelId = $("#createFoodForm").data('hotelid')
  let name = $("#nameInput").val()
  let price = $("#priceInput").val()

  event.preventDefault()

  let food = {
    name: name,
    price: price,
    hotelId: hotelId
  }

  await foodsApi.postFood(food)
  window.location = `${constants.BASE_URL}/hotelFoods?hotelId=${hotelId}`
}

$(() => {
  $("#createFoodForm").on('submit', onSubmit)
})
