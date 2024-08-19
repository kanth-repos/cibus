import * as constants from "../constants/constants.js";
import * as foodApi from "../apiclient/foodapi.js";
import * as userApi from "../apiclient/userapi.js";
import * as ordersApi from "../apiclient/orderapi.js";

const orderHtml = (order) => {
  return `
    <div class="order card">
      <img
        src="${constants.BASE_URL}/content/images/order.png"
        class="card-img-top"
      />
      <div class="card-body">
        <div class="food">
          Food: ${order.foodId}
        </div>
        <div class="qty">
          Quantity: ${order.quantity}
        </div>
        <div class="id">
          OrderId: #${order.id}
        </div>
      </div>
</a>
`;
};

const loadOrders = async () => {
  let container = $("#ordersContainer");
  let user = await userApi.getUser()
  let orders = await ordersApi.getOrders({ userId:user.id });

  for (let order of orders) {
    container.append($(orderHtml(order)));
  }
};

$(loadOrders);
