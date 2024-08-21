import * as constants from '../constants/constants.js'
import * as foodsApi from '../apiclient/foodapi.js'
import * as userApi from "../apiclient/userapi.js"
import * as ratingApi from '../apiclient/ratingapi.js'
import star from '../star.js'

const foodHtml = (name, price) => `
<div class="food pane mx-5">
  <img
    src="${constants.BASE_URL}/images/food.png"
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

const ratingHtml = (rating, user) => `
  <div class="d-flex flex-column gap-2 mx-5 pane py-3">
    <div class="star">
      ${star(rating.rating)}
    </div>
    <div>
      ${rating.message}
    </div>
    <div style="color: grey;">
      by ${user.name}
    </div>
  </div>
`

const loadFood = async () => {
  let foodIdHolder = $("#foodIdHolder");
  let foodId = foodIdHolder.data('foodid')
  let container = $('#foodContainer')
  let food = await foodsApi.getFood(foodId);
  let element = $(foodHtml(food.name, food.price))
  container.append(element)
}

const loadRatings = async () => {
  let foodIdHolder = $("#foodIdHolder")
  let foodId = foodIdHolder.data('foodid')
  let ratings = await ratingApi.getRatings(foodId);
  let container = $('#ratings')

  for(let rating of ratings) {
    let user = await userApi.getUser(rating.userId)
    let element = $(ratingHtml(rating, user))
    container.append(element)
  }
}

$(async () => {
  await loadFood();
  await loadRatings();
})
