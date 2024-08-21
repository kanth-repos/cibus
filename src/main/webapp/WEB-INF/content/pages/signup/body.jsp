<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div
  class="containter d-flex justify-content-center align-items-center fill-height px-1"
>
  <form
    action="signup"
    method="post"
    class="p-3 card"
    id="form"
    style="width: 450px"
  >
    <div class="form-group py-2">
      <label for="emailInput">Email address</label>
      <input
        type="email"
        name="user.email"
        class="form-control"
        id="emailInput"
        placeholder="Email"
      />
    </div>
    <div class="form-group py-2">
      <label for="nameInput">Name</label>
      <input
        type="text"
        name="user.name"
        class="form-control"
        id="nameInput"
        placeholder="Name"
      />
    </div>
    <div class="form-group py-2">
      <label for="mobileInput">Mobile</label>
      <input
        type="text"
        name="user.mobile"
        class="form-control"
        id="nameInput"
        placeholder="Mobile"
      />
    </div>
    <div class="form-group py-2">
      <label for="typeInput">Type</label>
      <select class="form-control" name="user.type" id="typeInput">
        <option>user</option>
        <option>owner</option>
      </select>
    </div>
    <div class="form-group py-2">
      <label for="passInput">Password</label>
      <input
        type="password"
        name="user.password"
        class="form-control"
        id="passInput"
        placeholder="Password"
      />
    </div>
    <div class="form-group py-2 d-flex justify-content-center">
      <button class="btn btn-primary" type="submit">Sign Up</button>
    </div>
  </form>
</div>
