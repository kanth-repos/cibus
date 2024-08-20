import * as constants from "./constants/constants.js"
import * as userApi from "../scripts/apiclient/userapi.js";

const onSubmit = async (evt) => {
  let user = $.extend({}, $("#editUserForm").data('user'));
  let id = user.id;

  user.mobile = $("#mobileInput").val();
  user.name = $("#nameInput").val();
  user.email = $("#emailInput").val();

  evt.preventDefault();

  if($("#passInput").val()) {
    user.password = $("#passInput").val();
  } else {
    user.password = null;
  }

  delete user.id;

  await userApi.putUser(id, user);
  window.location = `${constants.BASE_URL}/account`
}

const loadUser = async () => {
  let user = await userApi.getUser();
  $("#editUserForm").data('user', user)
  $("#emailInput").val(user.email);
  $("#nameInput").val(user.name);
  $("#mobileInput").val(user.mobile);
  $("#typeInput").val(user.type).change();
};

$(async () => {
  await loadUser();
  $("#editUserForm").on('submit', onSubmit);
})
