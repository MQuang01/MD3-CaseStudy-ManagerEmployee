<%--
  Created by IntelliJ IDEA.
  User: cnbnh
  Date: 1/6/2024
  Time: 3:39 PM
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
<%--
  Created by IntelliJ IDEA.
  User: cnbnh
  Date: 1/6/2024
  Time: 10:17 AM
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
<c:import url="../../headerEmployee.jsp" />

<title>Title</title>
<img  src="https://images.unsplash.com/photo-1502378735452-bc7d86632805?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=200&amp;fit=max&amp;s=aa3a807e1bbdfd4364d1f449eaa96d82" alt="" style="width: 300px; border: 2px solid #ddd; border-radius: 5px;">

<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">Profile </h2>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Name</h4>

<%--<c:forEach var="member" items="${member}">--%>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400"><c:out value="${member.fullName}"/></p>
</div>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Phone Number</h4>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400"><c:out value="${member.phoneNum}"/></p>
</div>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Date of birth</h4>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400"><c:out value="${member.doB}"/></p>
</div>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Email</h4>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400"><c:out value="${member.email}"/></p>
</div>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Team name</h4>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400">
        <c:forEach var="listTeam" items="${listTeam}">

            <c:if test="${member.teamId == listTeam.id}">
                ${listTeam.name}
            </c:if>
        </c:forEach>

    </p>
</div>

<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"> Project name</h4>
<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
    <p class="text-sm text-gray-600 dark:text-gray-400">
<%--        <c:forEach var="listTeam" items="${listTeam}">--%>
<%--            <c:forEach var="projectIdName" items="${projectIdName}">--%>
<%--                <c:if test="${member.teamId == listTeam.id and listTeam.projectId == projectIdName.id}">--%>
<%--                    ${projectIdName.name}--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
<%--        </c:forEach>--%>
    </p>
</div>



<c:import url="../../footerEmployee.jsp" />
