<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <s:include value="%{#application.WEB_CONTENTS}/components/bootstrap.jsp" />
    <s:include value="%{#application.WEB_CONTENTS}/components/fontawsome.jsp" />
    <s:include value="%{#application.WEB_CONTENTS}/components/jquery.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/global.css" />
    <tiles:insertAttribute name="headerComponents"/>
  </head>
  <body>
    <s:include value="%{#application.WEB_CONTENTS}/components/navbar.jsp" />
    <tiles:insertAttribute name="bodyComponents"/>
  </body>
</html>
