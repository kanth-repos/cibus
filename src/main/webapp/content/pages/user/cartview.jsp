<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <jsp:include page="/content/components/jquery.jsp" />

    <script type="module" src="content/scripts/user/cartview.js" >
    </script>

    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/cart.css" />
    <link rel="stylesheet" href="content/styles/signup.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex flex-row justify-content-center align-items-center gap-4 flex-wrap py-5"
      id="cartContainer"
    >
    </div>
    <div class="float-btn-right" id="placeOrder">
      Place Order
    </div>
  </body>
</html>
