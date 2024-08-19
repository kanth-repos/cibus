import * as constants from '../constants/constants.js'
import * as userApi from "../apiclient/userapi.js"
import * as cartApi from '../apiclient/cartapi.js'
import * as orderApi from '../apiclient/orderapi.js'

const cartHtml = (item) => `
<div class="cart">
  <img
    src="${constants.BASE_URL}/content/images/order.png"
    class="img-fluid"
  />
  <div class="food">
    Food Id: ${item.foodId}
  </div>
  <div class="qty">
    Quantity: ${item.quantity}
  </div>
  <div class="id">
    CartId: #${item.id}
  </div>
</a>
`

const onOrderClick = async (evt) => {
  let user = await userApi.getUser()
  let cart = await cartApi.getCarts(user.id);
  for(let item of cart) {
    let order = {
      quantity: item.quantity,
      userId: item.userId,
      foodId: item.foodId
    }

    await orderApi.postOrder(order);
    await cartApi.deleteCart(item.id);
  }
  window.location.reload()
}

const loadCart = async () => {
  let container = $('#cartContainer')
  let user = await userApi.getUser()
  let cart = await cartApi.getCarts(user.id);

  for(let item of cart) {
    container.append($(cartHtml(item)))
  }
}

$(async () => {
  $("#placeOrder").on('click', onOrderClick);
  await loadCart()
});
