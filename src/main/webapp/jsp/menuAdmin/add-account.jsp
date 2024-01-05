<%--
  Created by IntelliJ IDEA.
  User: cnbnh
  Date: 1/4/2024
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>

<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../../headerAdmin.jsp" />
<h3>Add - account Page</h3>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<h3>Add - account </h3>

<form action="/admin-page?act=add-account" method="post" >
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-username">Username</span>
        <input type="text" id="username" name="username" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-password">Password</span>
        <input type="text" id="password" name="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="mb-3 d-flex">
        <label for="role" class="form-label" style="width: 100px;">Select role</label>
        <select id="role" name="role" class="form-select">

            <c:forEach var="rolelist" items="${roleList}">
                <option value="<c:out value="${rolelist.name}"/>">  <c:out value="${rolelist.name}"/></option>
            </c:forEach>

        </select>
    </div>

<%--    onclick="window.location.href='/admin-page?act=add-member'"--%>
    <button type="submit" value="Save" class="btn btn-primary" >
<%--        <a href="/admin-page?act=add-member">--%>
            Create account
<%--        </a>--%>

    </button>
</form>

</body>

</html>
<c:import url="../../footerAdmin.jsp" />
