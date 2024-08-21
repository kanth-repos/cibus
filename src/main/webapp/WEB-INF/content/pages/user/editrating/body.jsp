<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex flex-column justify-content-center fill-height mx-auto px-1"
  data-ratingid="<s:property value='ratingId'/>"
  id="ratingIdHolder"
  style="max-width: 900px"
>
  <form class="card" id="editRatingForm" class="m-5">
    <div class="text-center">Add an Review</div>
    <div class="form-group py-2">
      <label for="starInput">Star</label>
      <select class="form-control" name="star" id="starInput">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
      </select>
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
