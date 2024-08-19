import * as constants from "../constants/constants.js";
import * as foodApi from "../apiclient/foodapi.js";
import * as userApi from "../apiclient/userapi.js";
import * as ordersApi from "../apiclient/orderapi.js";

const orderHtml = (order) => {
  return `
    <div class="order">
      <img
        src="${constants.BASE_URL}/content/images/order.png"
        class="img-fluid"
      />
      <div class="user">
        From User: ${order.userId}
      </div>
      <div class="food">
        Food: ${order.foodId}
      </div>
      <div class="qty">
        Quantity: ${order.quantity}
      </div>
      <div class="id">
        OrderId: #${order.id}
      </div>
</a>
`;
};

const loadOrders = async () => {
  let container = $("#ordersContainer");
  let hotelId = container.data("hotelid");
  let orders = await ordersApi.getOrders({ hotelId });

  for (let order of orders) {
    container.append($(orderHtml(order)));
  }
};

$(loadOrders);
