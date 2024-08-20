import * as constants from "../constants/constants.js";
import * as foodApi from "../apiclient/foodapi.js";
import * as userApi from "../apiclient/userapi.js";
import * as ordersApi from "../apiclient/orderapi.js";

const orderHtml = (order, food, user) => `
<div class="order card">
  <img
    src="${constants.BASE_URL}/content/images/order.png"
    class="card-img-top"
  />
  <div class="card-body">
    <div class="user">
      From User: ${user.name}
    </div>
    <div class="food">
      Food: ${food.name}
    </div>
    <div class="qty">
      Quantity: ${order.quantity}
    </div>
    <div class="id">
      OrderId: #${order.id}
    </div>
    </div>
  </div>
`;


const loadOrders = async () => {
  let container = $("#ordersContainer");
  let hotelId = container.data("hotelid");
  let orders = await ordersApi.getOrders({ hotelId });

  for (let order of orders) {
    let food = await foodApi.getFood(order.foodId);
    let user = await userApi.getUser(order.userId);
    container.append($(orderHtml(order, food, user)));
  }
};

$(loadOrders);
