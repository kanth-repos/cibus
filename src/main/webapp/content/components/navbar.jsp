<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<nav class="navbar navbar-expand navbar-light px-5">
  <a class="navbar-brand" href="index">Cibus</a>
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
      <li>
        <a class="nav-link" href="signout">SignOut</a>
      </li>
    </s:else>
  </ul>
</nav>
