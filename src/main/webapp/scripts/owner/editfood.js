import * as foodsApi from '../apiclient/foodapi.js'
import * as constants from "../constants/constants.js"

const onSubmit = async (event) => {
  let foodId = $("#editFoodForm").data('foodid')
  let name = $("#nameInput").val()
  let price = $("#priceInput").val()

  event.preventDefault()

  let food =  $("#editFoodForm").data('food');
  food.name = name;
  food.price = price;

  delete food.id;

  await foodsApi.putFood(foodId, food)
  window.location = `${constants.BASE_URL}/hotelFoods?hotelId=${food.hotelId}`
}

const loadFood = async (evt) => {
  let foodId = $("#editFoodForm").data('foodid')
  let food  = await foodsApi.getFood(foodId);
  $("#editFoodForm").data('food', food);
  $("#nameInput").val(food.name);
  $("#priceInput").val(food.price);
}

$(async () => {
  await loadFood();
  
  $("#editFoodForm").on('submit', onSubmit)
})
