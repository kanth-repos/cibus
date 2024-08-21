<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
class="containter d-flex justify-content-center align-items-center fill-height px-1"
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
<a class="float-btn-right" href="editUserForm">
Edit
</a>