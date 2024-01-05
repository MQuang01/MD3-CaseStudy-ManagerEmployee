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

    <title>Title</title>

<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">Add - account </h2>

<form action="/admin-page?act=add-account" method="post" >

    <div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">

        <label class="block text-sm" style="padding: 10px 0px">
            <span class="text-gray-700 dark:text-gray-400">Username</span>
            <input type="text" id="username" name="username" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="name@example.com">
        </label>



        <label class="block text-sm" style="padding: 10px 0px">
            <span class="text-gray-700 dark:text-gray-400">Password</span>
            <input type="text" id="password" name="password" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="password">
        </label>

        <label class="block mt-4 text-sm">
            <span for="role"  class="text-gray-700 dark:text-gray-400">Select role</span>
            <select id="role" name="role" class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray">
                <c:forEach var="rolelist" items="${roleList}">
                    <option value="<c:out value="${rolelist.name}"/>">  <c:out value="${rolelist.name}"/></option>
                </c:forEach>
            </select>
        </label>

        <div style="padding: 10px 10px 10px 0px" >
            <button type="submit" value="Save" class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
                Create account
            </button>
        </div>
    </div>
</form>


<c:import url="../../footerAdmin.jsp" />
