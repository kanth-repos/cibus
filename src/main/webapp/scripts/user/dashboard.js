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
    <img src="${constants.BASE_URL}/images/dish.png"
      class="img-fluid"
      title="Foods"
    />
  </div>
</a>
`

const onFoodsClick = (evt) => {
  let hotel = $(evt.target).closest('.hotel')
  let data = hotel.data('hotel')
  window.location = `${constants.BASE_URL}/hotelFoods?hotelId=${data.id}`
}

const loadHotels = async () => {
  let container = $('#hotelsContainer')
  let hotels = await hotelApi.getHotels()

  for(let hotel of hotels) {
    let element = $(hotelHtml(hotel.name, hotel.city))
    let actions = element.find(".actions > img").toArray()
    element.data('hotel', hotel)
    $(actions[0]).on('click', onFoodsClick)
    container.append(element)
  }
}

$(loadHotels)
