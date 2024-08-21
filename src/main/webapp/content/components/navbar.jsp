<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="s"
uri="/struts-tags" %>

<nav class="navbar navbar-expand-md navbar-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index">Cibus</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto">
        <s:if test="%{#session.userSession == null}">
          <li class="nav-item">
            <a class="nav-link" href="signupForm">SignUp</a>
          </li>
          <li>
            <a class="nav-link" href="signinForm">SignIn</a>
          </li>
        </s:if>
        <s:else>
          <li class="nav-item">
            <a class="nav-link" href="dashboard">Dashboard</a>
          </li>
          <s:if test="%{#session.userSession.type == 'user'}">
            <li class="nav-item">
              <a class="nav-link" href="usercart">Cart</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="userorders">Orders</a>
            </li>
          </s:if>
          <li class="nav-item">
            <a class="nav-link" href="account">Account</a>
          </li>
          <li>
            <a class="nav-link" href="signout">SignOut</a>
          </li>
        </s:else>
      </ul>
    </div>
  </div>
</nav>
