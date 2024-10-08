<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex justify-content-center align-items-center fill-height px-1"
>
  <form class="p-3 card" id="editUserForm" style="width: 450px">
    <div class="form-group py-2">
      <label for="emailInput">Email address</label>
      <input
        type="email"
        name="email"
        class="form-control"
        id="emailInput"
        placeholder="Email"
      />
    </div>
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
      <label for="mobileInput">Mobile</label>
      <input
        type="text"
        name="mobile"
        class="form-control"
        id="mobileInput"
        placeholder="Mobile"
      />
    </div>
    <div class="form-group py-2">
      <label for="typeInput">Type</label>
      <select class="form-control" name="type" id="typeInput" disabled>
        <option>user</option>
        <option>owner</option>
      </select>
    </div>
    <div class="form-group py-2">
      <label for="passInput">Password</label>
      <input
        type="password"
        name="password"
        class="form-control"
        id="passInput"
        placeholder="Password"
      />
    </div>
    <div class="form-group py-2 d-flex justify-content-center">
      <button class="btn btn-primary" type="submit">Update</button>
    </div>
  </form>
</div>
