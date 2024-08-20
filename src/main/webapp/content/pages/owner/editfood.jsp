<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <jsp:include page="/content/components/jquery.jsp" />

    <script type="module" src="content/scripts/owner/editfood.js"></script>

    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/signup.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex justify-content-center align-items-center fill-height"
    >
      <form class="p-3 pane" id="editFoodForm" data-foodid="<s:property value='foodId'/>">
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
          <label for="priceInput">Price</label>
          <input
            type="text"
            name="price"
            class="form-control"
            id="priceInput"
            placeholder="Price"
          />
        </div>
        <div class="form-group py-2 d-flex justify-content-center">
          <button class="btn btn-primary" type="submit">
            Update Food
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
