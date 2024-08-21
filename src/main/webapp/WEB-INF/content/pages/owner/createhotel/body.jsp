<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex justify-content-center align-items-center fill-height px-1"
>
  <form class="p-3 pane" id="createHotelForm">
    <div class="form-group py-2">
      <label for="nameInput">Name</label>
      <input
        type="text"
        name="name"
        class="form-control"
        id="nameInput"
        placeholder="Name"
      />
    </div>
    <div class="form-group py-2">
      <label for="cityInput">City</label>
      <input
        type="text"
        name="city"
        class="form-control"
        id="cityInput"
        placeholder="City"
      />
    </div>
    <div class="form-group py-2 d-flex justify-content-center">
      <button class="btn btn-primary" type="submit">Create Hotel</button>
    </div>
  </form>
</div>
