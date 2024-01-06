<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/2/2024
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<c:import url="../../headerAdmin.jsp"/>
<main class="h-full overflow-y-auto">
    <div class="grid gap-6 mb-8 md:grid-cols-2">
        <div class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
            <h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
                Percent Checkin
            </h4>
            <canvas id="pie" width="705" height="352" style="display: block; height: 282px; width: 564px;" class="chartjs-render-monitor"></canvas>
            <div class="flex justify-center mt-4 space-x-3 text-sm text-gray-600 dark:text-gray-400">
                <!-- Chart legend -->
                <div class="flex items-center">
                    <span class="inline-block w-3 h-3 mr-1 bg-blue-500 rounded-full"></span>
                    <span>Uncheck</span>
                </div>
                <div class="flex items-center">
                    <span class="inline-block w-3 h-3 mr-1 bg-teal-600 rounded-full"></span>
                    <span>On Time</span>
                </div>
                <div class="flex items-center">
                    <span class="inline-block w-3 h-3 mr-1 bg-purple-600 rounded-full"></span>
                    <span>Late</span>
                </div>
            </div>
        </div>
        <div class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
            <h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
                Chart
            </h4>
            <canvas id="line" width="705" height="352" style="display: block; height: 282px; width: 564px;" class="chartjs-render-monitor"></canvas>
            <div class="flex justify-center mt-4 space-x-3 text-sm text-gray-600 dark:text-gray-400">
                <!-- Chart legend -->
                <div class="flex items-center">
                    <span class="inline-block w-3 h-3 mr-1 bg-teal-600 rounded-full"></span>
                    <span>On Time</span>
                </div>
                <div class="flex items-center">
                    <span class="inline-block w-3 h-3 mr-1 bg-purple-600 rounded-full"></span>
                    <span>Late</span>
                </div>
            </div>
        </div>
    </div>
    <!-- New Table -->
    <div class="w-full overflow-hidden rounded-lg shadow-xs">
        <div class="w-full overflow-x-auto">
            <table class="w-full whitespace-no-wrap">
                <thead>
                <tr
                        class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                >
                    <th class="px-4 py-3">Name</th>
                    <th class="px-4 py-3">Phone number</th>
                    <th class="px-4 py-3">Date of Birth</th>
                    <th class="px-4 py-3">Email</th>
                    <th class="px-4 py-3">Team Name</th>
                    <th class="px-4 py-3">Project Name</th>
                </tr>
                </thead>
                <tbody
                        class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                >

                <c:forEach var="listMember" items="${listMember}">

                <tr class="text-gray-700 dark:text-gray-400">
                    <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                            <!-- Avatar with inset shadow -->
                            <div
                                    class="relative hidden w-8 h-8 mr-3 rounded-full md:block"
                            >
                                <img
                                        class="object-cover w-full h-full rounded-full"
                                        src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjE3Nzg0fQ"
                                        alt=""
                                        loading="lazy"
                                />
                                <div
                                        class="absolute inset-0 rounded-full shadow-inner"
                                        aria-hidden="true"
                                ></div>
                            </div>
                            <div>
                                <p class="font-semibold"><c:out value="${listMember.fullName}"/></p>
                                <p class="text-xs text-gray-600 dark:text-gray-400">
                                    <c:forEach var="listAccount" items="${listAccount}">
                                        <c:if test="${listMember.id == listAccount.id}">
                                            ${listAccount.role}
                                        </c:if>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                    </td>
                    <td class="px-4 py-3 text-sm">
                        <c:out value="${listMember.phoneNum}"/>
                    </td>
                    <td class="px-4 py-3 text-xs">
                        <c:out value="${listMember.doB}"/>
                    </td>
                    <td class="px-4 py-3 text-sm">
                        <c:out value="${listMember.email}"/>
                    </td>

                    <td class="px-4 py-3 text-sm">
                    <c:forEach var="listTeam" items="${listTeam}">
                        <c:if test="${listMember.teamId == listTeam.id}">
                            ${listTeam.name}
                        </c:if>
                    </c:forEach>
                    </td>

<%--                    ở đây--%>
                    <td class="px-4 py-3 text-sm">

                    </td>

                </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</main>
<c:import url="../../footerAdmin.jsp"/>
