<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cibus</title>
    <jsp:include page="/content/components/bootstrap.jsp" />
    <link rel="stylesheet" href="content/styles/global.css" />
    <link rel="stylesheet" href="content/styles/signup.css" />
  </head>
  <body>
    <jsp:include page="/content/components/navbar.jsp" />
    <div
      class="containter d-flex justify-content-center align-items-center fill-height"
    >
      <form action="signup" method="post" class="p-3 pane" id="form">
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
          <button class="btn btn-primary" type="submit">
            Sign Up
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
