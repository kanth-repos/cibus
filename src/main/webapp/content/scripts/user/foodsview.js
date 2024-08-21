import * as constants from '../constants/constants.js'
import * as foodsApi from '../apiclient/foodapi.js'
import * as userApi from "../apiclient/userapi.js"
import * as cartApi from '../apiclient/cartapi.js'

const foodHtml = (name, price) => `
<div class="food card pane">
  <img
    src="${constants.BASE_URL}/content/images/food.png"
    class="card-img-top food-image"
  />
  <div class="card-body">
    <div class="name">
      ${name}
    </div>
    <div class="price">
      ${price}
    </div>
  </div>
</a>
`

const onFoodClick = async (evt) => {
  let food = $(evt.target).closest('.food')
  let data = food.data('food')
  window.location = `${constants.BASE_URL}/food?foodId=${data.id}`
}

const loadFoods = async () => {
  let container = $('#foodsContainer')
  let hotelId = container.data('hotelid')
  let foods = await foodsApi.getFoods(hotelId);

  for(let food of foods) {
    let element = $(foodHtml(food.name, food.price))
    element.data('food', food)
    element.on('click', onFoodClick);
    container.append(element)
  }
}

$(loadFoods)
