<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/7/2024
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../../headerEmployee.jsp"/>
<div class="text-center mt-4 mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
    My Project
</div>
<div class="w-full overflow-hidden rounded-lg shadow-xs">
    <div class="w-full overflow-hidden rounded-lg shadow-xs">
        <div class="w-full overflow-x-auto">
            <table class="w-full mb-4 whitespace-no-wrap">
                <thead>
                <tr
                        class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                >
                    <th class="px-4 py-3">#</th>
                    <th class="px-4 py-3">Project</th>
                    <th class="px-4 py-3">Deadline</th>
                    <th class="px-4 py-3">Status</th>
                </tr>
                </thead>
                <tbody
                        class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                >

                <c:forEach var="line" items="${listProject}">

                    <tr class="text-gray-700 dark:text-gray-400">
                        <td class="px-4 py-3">
                            <div class="flex items-center text-sm">
                                <p class="font-semibold">${line.id}</p>
                            </div>
                        </td>

                        <td class="px-4 py-3 text-sm">
                                ${line.name}
                        </td>
                        <td class="px-4 py-3 text-sm">
                                ${line.deadLine}
                        </td>
                        <td class="px-4 py-3 text-xs">
                            <span
                                    class="px-2 py-1 font-semibold leading-tight  <c:if test="${line.status}">text-green-700 bg-green-100</c:if> rounded-full <c:if test="${!line.status}">text-orange-700 bg-orange-100</c:if> "
                            >
                                    ${line.status ? "Done" : "Peding"}
                            </span>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
            <h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
                Teammates
            </h4>
            <table class="w-full whitespace-no-wrap">
                <thead>
                <tr
                        class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                >
                    <th class="px-4 py-3">#</th>
                    <th class="px-4 py-3">Name</th>
                    <th class="px-4 py-3">Email</th>
                    <th class="px-4 py-3">Team</th>
                </tr>
                </thead>
                <tbody
                        class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                >

                <c:forEach var="member" items="${listTeammate}">

                    <tr class="text-gray-700 dark:text-gray-400">
                        <td class="px-4 py-3">
                            <div class="flex items-center text-sm">
                                <p class="font-semibold">${member.id}</p>
                            </div>
                        </td>

                        <td class="px-4 py-3 text-sm">
                                ${member.fullName}
                        </td>
                        <td class="px-4 py-3 text-sm">
                                ${member.email}
                        </td>
                        <td class="px-4 py-3 text-xs">
                                ${member.team.name}
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
    </div>
</div>

<c:import url="../../footerEmployee.jsp"/>
