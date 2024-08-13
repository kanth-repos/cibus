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
      class="containter d-flex justify-content-center align-items-center fill-height"
    >
      <form action="signin" method="post" class="p-3" id="form">
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
          <button class="btn btn-primary" type="submit">
            Sign In
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
