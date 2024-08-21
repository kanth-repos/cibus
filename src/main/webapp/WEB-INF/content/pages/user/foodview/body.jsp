<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex flex-column fill-height mx-auto px-1"
  data-foodid="<s:property value='foodId'/>"
  id="foodIdHolder"
  style="max-width: 900px"
>
  <div id="foodContainer"></div>
  <div id="ratings"></div>
  <div id="formContainer"></div>
</div>
