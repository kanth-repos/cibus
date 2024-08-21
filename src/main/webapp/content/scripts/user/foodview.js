import * as constants from '../constants/constants.js'
import * as foodsApi from '../apiclient/foodapi.js'
import * as userApi from "../apiclient/userapi.js"
import * as cartApi from '../apiclient/cartapi.js'
import * as ratingApi from '../apiclient/ratingapi.js'
import star from '../star.js'

const foodHtml = (name, price) => `
<div class="food pane mx-5">
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
    <div class="actions">
      <img src="${constants.BASE_URL}/content/images/checkout.png"
        class="img-fluid"
        title="Add to Cart"
      />
    </div>
  </div>
</a>
`

const ratingHtml = (rating, user) => `
<div class="rating d-flex flex-column gap-2 mx-5 pane py-3">
  <div class="star">
    ${star(rating.rating)}
  </div>
  <div>
    ${rating.message}
  </div>
  <div style="color: grey;">
    by ${user.name}
  </div>
  <div class="actions" ${rating.userId!=user.id ? "hidden":""}>
    <img src="${constants.BASE_URL}/content/images/delete.png"
      class="img-fluid"
      title="delete"
    />
    <img src="${constants.BASE_URL}/content/images/edit.png"
      class="img-fluid"
      title="Edit"
    />
  </div>
</div>
`

const formHtml = () => `
<form id="ratingForm" class="m-5">
  <div class="text-center">Add an Review</div>
  <div class="form-group py-2">
  <label for="starInput">Star</label>
    <select class="form-control" name="star" id="starInput">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </div>
  <div class="form-group py-2">
    <label for="msgInput">Message</label>
    <textarea class="form-control" name="msg" id="msgInput" rows="3"></textarea>
  </div>
  <div class="form-group py-2 d-flex justify-content-center">
    <button class="btn btn-primary" type="submit">
      Submit
    </button>
  </div>
</form>
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

const onSubmit = async (evt) => {
  let foodIdHolder = $("#foodIdHolder");
  let foodId = foodIdHolder.data('foodid')
  let rating = $("#starInput").val()
  let msg = $("#msgInput").val()

  evt.preventDefault();

  let user = await userApi.getUser();

  let data = {
    foodId: foodId,
    userId: user.id,
    rating: +rating,
    message: msg
  }

  await ratingApi.postRating(data)
  window.location.reload();
}

const onEditClick = async (evt) => {
  let rating = $(evt.target).closest('.rating').data('rating');
  window.location = `${constants.BASE_URL}/editrating?ratingId=${rating.id}`;
}

const onDeleteClick = async (evt) => {
  let rating = $(evt.target).closest('.rating').data('rating');
  await ratingApi.deleteRating(rating.id);
  window.location.reload()
}

const loadFood = async () => {
  let foodIdHolder = $("#foodIdHolder");
  let foodId = foodIdHolder.data('foodid')
  let container = $('#foodContainer')
  let food = await foodsApi.getFood(foodId);
  let element = $(foodHtml(food.name, food.price))
  let actions = element.find(".actions > img").toArray()
  element.data('food', food)
  $(actions[0]).on('click', onCartClick)
  container.append(element)
}

const loadRatings = async () => {
  let foodIdHolder = $("#foodIdHolder")
  let foodId = foodIdHolder.data('foodid')
  let ratings = await ratingApi.getRatings(foodId);
  let container = $('#ratings')
  let accountUser = await userApi.getUser()

  ratings.sort((a, b) => {
    if(a.userId == accountUser.id) {
      return -1
    }

    if(b.userId == accountUser.id) {
      return 1
    }

    return NaN;
  })

  for(let rating of ratings) {
    let user = await userApi.getUser(rating.userId)
    let element = $(ratingHtml(rating, user))
    let actions = element.find(".actions > img").toArray()
    element.data('rating', rating)
    $(actions[0]).on('click', onDeleteClick)
    $(actions[1]).on('click', onEditClick)
    container.append(element)
  }
}

const loadForm = async () => {
  let foodIdHolder = $("#foodIdHolder")
  let foodId = foodIdHolder.data('foodid')
  let userId = (await userApi.getUser()).id
  
  try {
    await ratingApi.getRatingByFoodAndUser(foodId, userId);
  } catch (error) {
    $("#formContainer").append(formHtml());
    $("#ratingForm").on('submit', onSubmit);
  }
}

$(async () => {
  await loadFood();
  await loadRatings();
  await loadForm();
})
