<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <jsp:include page="/content/components/jquery.jsp" />
    <jsp:include page="/content/components/fontawsome.jsp" />

    <script type="module" src="content/scripts/user/editrating.js"></script>

    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/foods.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex flex-column justify-content-center fill-height mx-auto px-1"
      data-ratingid="<s:property value='ratingId'/>"
      id="ratingIdHolder"
      style="max-width: 900px;"
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
          <textarea class="form-control" name="msg" id="msgInput" rows="3"></textarea>
        </div>
        <div class="form-group py-2 d-flex justify-content-center">
          <button class="btn btn-primary" type="submit">
            Update
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
