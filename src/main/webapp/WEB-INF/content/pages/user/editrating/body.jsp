<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> <%@ page
contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="s"
uri="/struts-tags" %>

<div
  class="containter d-flex flex-column justify-content-center fill-height mx-auto px-1"
  data-ratingid="<s:property value='ratingId'/>"
  id="ratingIdHolder"
  style="max-width: 900px"
>
  <form class="card p-3" id="editRatingForm" class="m-5">
    <div class="text-center">Update Review</div>
    <div class="form-group py-2">
      <input
        class="form-control"
        name="star"
        id="starInput"
        value="1"
        type="hidden"
      />
      <div class="star-container d-flex flex-row gap-1 justify-content-center">
        <i class="fa fa-star" data-order="1"></i>
        <i class="fa fa-star" data-order="2"></i>
        <i class="fa fa-star" data-order="3"></i>
        <i class="fa fa-star" data-order="4"></i>
        <i class="fa fa-star" data-order="5"></i>
      </div>
    </div>
    <div class="form-group py-2">
      <label for="msgInput">Message</label>
      <textarea
        class="form-control"
        name="msg"
        id="msgInput"
        rows="3"
      ></textarea>
    </div>
    <div class="form-group py-2 d-flex justify-content-center">
      <button class="btn btn-primary" type="submit">Update</button>
    </div>
  </form>
</div>
