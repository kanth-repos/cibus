import * as constants from "../constants/constants.js";
import * as ratingApi from "../apiclient/ratingapi.js";

const onSubmit = async (evt) => {
  let rating = $.extend({}, $("#editRatingForm").data('rating'));
  let id = rating.id;
  let msg = $("#msgInput").val();
  let star = $("#starInput").val();

  evt.preventDefault();

  delete rating.id;

  if(msg) {
    rating.message = msg;
  }

  if(star) {
    rating.rating = +star;
  }

  await ratingApi.putRating(id, rating);
  window.location = `${constants.BASE_URL}/food?foodId=${rating.foodId}`;
}

const loadForm = async () => {
  let ratingIdHolder = $("#ratingIdHolder");
  let ratingId = ratingIdHolder.data("ratingid");
  let rating = await ratingApi.getRating(ratingId);

  $("#starInput").val(rating.rating).change();
  $("#msgInput").val(rating.message);

  $("#editRatingForm").data('rating', rating);
  $("#editRatingForm").on('submit', onSubmit)
};

$(async() => {
  await loadForm();
})
