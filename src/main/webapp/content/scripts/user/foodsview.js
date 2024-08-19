import * as constants from '../constants/constants.js'
import * as foodsApi from '../apiclient/foodapi.js'
import * as userApi from "../apiclient/userapi.js"
import * as cartApi from '../apiclient/cartapi.js'

const foodHtml = (name, price) => `
<div class="food">
  <img
    src="${constants.BASE_URL}/content/images/food.png"
    class="img-fluid"
  />
  <div class="name">
    ${name}
  </div>
  <div class="price">
    ${price}
  </div>
  <div class="actions">
    <img src="${constants.BASE_URL}/content/images/checkout.png"
      class="img-fluid"
      title="Add to Cart"
    />
  </div>
</a>
`

const onCartClick = async (evt) => {
  let food = $(evt.target).closest('.food')
  let data = food.data('food')
  let user = await userApi.getUser()
  let quantity = window.prompt("Enter Quantity", 1)

  let cart = {
    userId: user.id,
    foodId : data.id,
    quantity: quantity
  }

  await cartApi.postCart(cart)
  window.alert("Added to Cart")
}

const loadFoods = async () => {
  let container = $('#foodsContainer')
  let hotelId = container.data('hotelid')
  let foods = await foodsApi.getFoods(hotelId);

  for(let food of foods) {
    let element = $(foodHtml(food.name, food.price))
    let actions = element.find(".actions > img").toArray()
    element.data('food', food)
    $(actions[0]).on('click', onCartClick)
    container.append(element)
  }
}

$(loadFoods)
