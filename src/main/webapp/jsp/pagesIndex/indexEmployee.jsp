<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/2/2024
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>


<c:import url="../../headerEmployee.jsp"/>
    <h3>${member.fullName}</h3>
    <input type="hidden" id="fullName" value="${member.fullName}">
<c:import url="../../footerAdmin.jsp"/>

