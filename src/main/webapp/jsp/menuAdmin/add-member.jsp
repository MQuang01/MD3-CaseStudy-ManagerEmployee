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


<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">Add - Member </h2>

<form action="/admin-page?act=add-member" method="post">


    <div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">

        <label class="block text-sm" style="padding: 10px 0px">
            <span class="text-gray-700 dark:text-gray-400">Name</span>
            <input type="text" id="name" name="name" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="Employee name">
        </label>

        <label class="block text-sm" >
            <span class="text-gray-700 dark:text-gray-400">Phone Number</span>
            <input type="text" id="phone" name="phone" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="e.g:0987654321">
        </label>

        <label class="block text-sm" style="padding: 10px 0px">
            <span class="text-gray-700 dark:text-gray-400">Day Of Birth</span>
            <input type="text" id="dob" name="dob" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="yyyy-MM-dd">
        </label>

        <label class="block text-sm" >
            <span class="text-gray-700 dark:text-gray-400">Email address</span>
            <input type="email" name="email" id="email" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="name@example.com">
        </label>

        <label class="block mt-4 text-sm" style="padding: 10px 0px">
            <span for="teamId"  class="text-gray-700 dark:text-gray-400">Select team</span>
            <select id="teamId" name="teamId" class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray">
                <c:forEach var="teamList" items="${teamList}">
                    <option value="<c:out value="${teamList.id}"/>">  <c:out value="${teamList.name}"/></option>
                </c:forEach>
            </select>
        </label>

        <label class="block text-sm" style="padding: 10px 0px">
            <span class="text-gray-700 dark:text-gray-400">Account Id</span>
            <input type="text" id="accountId" name="accountId" value="${newAccountId}" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" >
        </label>


            <button type="submit" value="Save" class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" style="padding: 10px 10px 10px 0px">
                Create account
            </button>
    </div>



</form>

<c:import url="../../footerAdmin.jsp" />
