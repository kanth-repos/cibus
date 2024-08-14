<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/components/bootstrap.jsp" />
    <link rel="stylesheet" href="styles/global.css" />
    <link rel="stylesheet" href="styles/signup.css" />
  </head>
  <body>
    <jsp:include page="/components/navbar.jsp" />
    <div
      class="containter d-flex justify-content-center align-items-center fill-height"
    >
      <form action="createHotel" method="post" class="p-3 pane" id="form">
        <div class="form-group py-2">
          <label for="nameInput">Name</label>
          <input
            type="text"
            name="hotel.name"
            class="form-control"
            id="nameInput"
            placeholder="Name"
          />
        </div>
        <div class="form-group py-2">
          <label for="cityInput">City</label>
          <input
            type="text"
            name="hotel.city"
            class="form-control"
            id="cityInput"
            placeholder="City"
          />
        </div>
        <div class="form-group py-2">
          <input
            type="hidden"
            name="hotel.ownerId"
            id="ownerIdInput"
            value="<s:property value='%{#session.userSession.id}'/>"
          />
        </div>
        <div class="form-group py-2 d-flex justify-content-center">
          <button class="btn btn-primary" type="submit">
            Create Hotel
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
