import * as hotelApi from "../apiclient/hotelapi.js"
import * as constants from "../constants/constants.js"

const onSubmit = async (event) => {
  let hotelId = $("#editHotelForm").data('hotelid')
  let name = $("#nameInput").val()
  let city = $("#cityInput").val()

  event.preventDefault()

  let hotel = $("#editHotelForm").data('hotel');

  hotel.name = name;
  hotel.city = city;

  delete hotel.id;

  await hotelApi.putHotel(hotelId, hotel)
  window.location = `${constants.BASE_URL}/dashboard`
}

const loadHotel = async (evt) => {
  let hotelId = $("#editHotelForm").data('hotelid')
  let hotel  = await hotelApi.getHotel(hotelId);
  $("#editHotelForm").data('hotel', hotel);
  $("#nameInput").val(hotel.name);
  $("#cityInput").val(hotel.city);
}

$(async () => {
  await loadHotel();
  $("#editHotelForm").on('submit', onSubmit)
})

