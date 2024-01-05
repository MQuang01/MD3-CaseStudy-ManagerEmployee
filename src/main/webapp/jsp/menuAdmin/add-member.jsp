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
<h3>Add - Member Page</h3>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<h3>Add - Member Page12312312313123123</h3>

<form action="/admin-page?act=add-member" method="post">
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-name">Name</span>
        <input type="text" id="name" name="name" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-phone-number">Phone Number</span>
        <input type="text" id="phone" name="phone" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-dob">Day Of Birth</span>
        <input type="text" id="dob" name="dob" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp">
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
    </div>


    <div class="mb-3">
        <label for="teamId" class="form-label">Select team</label>
        <select id="teamId" name="teamId" class="form-select">

            <c:forEach var="teamList" items="${teamList}">
                <option value="<c:out value="${teamList.id}"/>">  <c:out value="${teamList.name}"/></option>
            </c:forEach>

        </select>
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-accountId" style="display: none">Account Id</span>
        <input type="text" id="accountId" name="accountId" value="${newAccountId}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" hidden>
    </div>

    <button type="submit" value="Save" class="btn btn-primary">Submit</button>
</form>

</body>
</html>
<c:import url="../../footerAdmin.jsp" />
