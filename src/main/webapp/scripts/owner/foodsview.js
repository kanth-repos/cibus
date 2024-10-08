import * as constants from '../constants/constants.js'
import * as foodsApi from '../apiclient/foodapi.js'

const foodHtml = (name, price) => `
<div class="food card pane">
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
    <div class="actions">
      <img src="${constants.BASE_URL}/images/delete.png"
        class="img-fluid"
        title="delete"
      />
      <img src="${constants.BASE_URL}/images/edit.png"
        class="img-fluid"
        title="Edit"
      />
    </div>
  </div>
</a>
`

const onDeleteClick = async (evt) => {
  let food = $(evt.target).closest('.food')
  let data = food.data('food')
  await foodsApi.deleteFood(data.id);
  window.location.reload();
}

const onEditClick = async (evt) => {
  let food = $(evt.target).closest('.food')
  let data = food.data('food')
  window.location = `${constants.BASE_URL}/editFoodForm?foodId=${data.id}`;
}

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
    let actions = element.find(".actions > img").toArray()
    element.data('food', food)
    element.children('img').on('click', onFoodClick);
    $(actions[0]).on('click', onDeleteClick)
    $(actions[1]).on('click', onEditClick)
    container.append(element)
  }
}

$(loadFoods)
