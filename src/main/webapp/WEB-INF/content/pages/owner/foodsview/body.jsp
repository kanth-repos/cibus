<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex flex-row justify-content-center align-items-center gap-4 flex-wrap py-5 px-1"
  id="foodsContainer"
  data-hotelid="<s:property value='hotelId'/>"
></div>
<a
  class="float-btn-right"
  href="createFoodForm?hotelId=<s:property value='hotelId'/>"
>
  Add Food
</a>
