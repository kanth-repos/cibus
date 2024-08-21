import * as constants from '../constants/constants.js'
import * as hotelApi from '../apiclient/hotelapi.js'
import * as userApi from '../apiclient/userapi.js'

const hotelHtml = (name, city) => `
<div class="hotel pane">
  <img
    src="${constants.BASE_URL}/images/restaurant.png"
    class="img-fluid"
  />
  <div class="name">
    ${name}
  </div>
  <div class="city">
    ${city}
  </div>
  <div class="actions">
    <img src="${constants.BASE_URL}/images/checkout.png"
      class="img-fluid"
      title="Orders"
    />
    <img src="${constants.BASE_URL}/images/dish.png"
      class="img-fluid"
      title="Foods"
    />
    <img src="${constants.BASE_URL}/images/delete.png"
      class="img-fluid"
      title="delete"
    />
    <img src="${constants.BASE_URL}/images/edit.png"
      class="img-fluid"
      title="Edit"
    />
  </div>
</a>
`

const onOrdersClick = (evt) => {
  let hotel = $(evt.target).closest('.hotel')
  let data = hotel.data('hotel')
  window.location = `${constants.BASE_URL}/hotelOrders?hotelId=${data.id}`
}

const onFoodsClick = (evt) => {
  let hotel = $(evt.target).closest('.hotel')
  let data = hotel.data('hotel')
  window.location = `${constants.BASE_URL}/hotelFoods?hotelId=${data.id}`
}

const onDeleteClick = (evt) => {
  let hotel = $(evt.target).closest('.hotel')
  let data = hotel.data('hotel')
  hotelApi.deleteHotel(data.id)
  window.location.reload();
}

const onEditClick = (evt) => {
  let hotel = $(evt.target).closest('.hotel')
  let data = hotel.data('hotel')
  window.location = `${constants.BASE_URL}/editHotelForm?hotelId=${data.id}`;
}

const loadHotels = async () => {
  let container = $('#hotelsContainer')
  let user = await userApi.getUser()
  let hotels = await hotelApi.getHotels(user.id)

  for(let hotel of hotels) {
    let element = $(hotelHtml(hotel.name, hotel.city))
    let actions = element.find(".actions > img").toArray()
    element.data('hotel', hotel)
    $(actions[0]).on('click', onOrdersClick)
    $(actions[1]).on('click', onFoodsClick)
    $(actions[2]).on('click', onDeleteClick)
    $(actions[3]).on('click', onEditClick)
    container.append(element)
  }
}

$(loadHotels)
