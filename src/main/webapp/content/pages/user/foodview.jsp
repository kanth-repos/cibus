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

    <script type="module" src="content/scripts/user/foodview.js"></script>

    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/foods.css" />
    <link rel="stylesheet" href="content/styles/ratings.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex flex-column fill-height mx-auto px-1"
      data-foodid="<s:property value='foodId'/>"
      id="foodIdHolder"
      style="max-width: 900px;"
    >
      <div id="foodContainer">
      </div>
      <div id="ratings">
      </div>
      <div id="formContainer"></div>
    </div>
  </body>
</html>
