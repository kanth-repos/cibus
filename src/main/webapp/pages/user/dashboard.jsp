<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="s"
uri="/struts-tags" %>

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
      class="containter d-flex flex-row justify-content-center align-items-center gap-4 flex-wrap mx-5"
    >
      <s:iterator value="%{hotels}" status="it">
        <a href="" class="hotel">
          <img src="${pageContext.request.contextPath}/images/restaurant.png" class="img-fluid image" />
          <div class="name"><s:property value="name" /></div>
          <div class="city"><s:property value="city" /></div>
        </a>
      </s:iterator>
    </div>
  </body>
</html>
