<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/components/bootstrap.jsp" />
    <link rel="stylesheet" href="styles/global.css" />
    <link rel="stylesheet" href="styles/index.css" />
  </head>
  <body>
    <jsp:include page="/components/navbar.jsp" />
    <div class="containter d-flex flex-column justify-content-center align-items-center fill-height">
        <img src="${pageContext.request.contextPath}/images/tick.png" class="img-fluid">
        <p>Account Creation was Success, Sign In</p>
    </div>
  </body>
</html>
