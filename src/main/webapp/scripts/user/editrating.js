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

const onStarClick = async (evt) => {
  let order = +$(evt.target).data('order');

  for(let i = 1; i <= order; ++i) {
    $(`.star-container > i:nth-child(${i})`).css('color', 'gold');
  }

  for(let i = order + 1; i <= 5; ++i) {
    $(`.star-container > i:nth-child(${i})`).css('color', 'black');
  }

  $("#starInput").val(order);
}

const loadForm = async () => {
  let ratingIdHolder = $("#ratingIdHolder");
  let ratingId = ratingIdHolder.data("ratingid");
  let rating = await ratingApi.getRating(ratingId);

  for(let i = 1; i <= rating.rating; ++i) {
    $(`.star-container > i:nth-child(${i})`).css('color', 'gold');
  }

  $("#starInput").val(rating.rating);
  $("#msgInput").val(rating.message);

  $("#editRatingForm").data('rating', rating);
  $("#editRatingForm").on('submit', onSubmit);
  $(".star-container > i").on('click', onStarClick);
};

$(async() => {
  await loadForm();
})
