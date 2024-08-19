<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <link rel="stylesheet" href="content/styles/account.css" />
    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/signup.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex justify-content-center align-items-center fill-height"
    >
      <table class="account">
        <tr>
          <td class="label">
            Email
          </td>
          <td class="value">
            <s:property value="#session.userSession.email" />
          </td>
        </tr>
        <tr>
          <td class="label">
            Name
          </td>
          <td class="value">
            <s:property value="#session.userSession.name" />
          </td>
        </tr>
        <tr>
          <td class="label">
            Mobile
          </td>
          <td class="value">
            <s:property value="#session.userSession.mobile" />
          </td>
        </tr>
        <tr>
          <td class="label">
            Type
          </td>
          <td class="value">
            <s:property value="#session.userSession.type" />
          </td>
        </tr>
      </table>
    </div>
  </body>
</html>
