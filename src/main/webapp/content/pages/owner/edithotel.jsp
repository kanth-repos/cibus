<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <jsp:include page="/content/components/jquery.jsp" />

    <script type="module" src="content/scripts/owner/edithotel.js"></script>
    <link rel="stylesheet" href="content/styles/global.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex justify-content-center align-items-center fill-height px-1"
    >
      <form class="p-3 card" id="editHotelForm" data-hotelid="<s:property value='hotelId'/>">
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
          <button class="btn btn-primary" type="submit">
            Update Hotel
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
