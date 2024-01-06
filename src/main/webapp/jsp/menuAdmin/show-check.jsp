<%--
  Created by IntelliJ IDEA.
  User: cnbnh
  Date: 1/6/2024
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/2/2024
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="../../assetsEmployee/css/style.css">
<c:import url="../../headerAdmin.jsp"/>

<div class="filter-time-keeping">
  <div>
        <span>
            Sort by
        </span>
  </div>
  <div class="filter-btn">
    <button id="day" class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
      Day
    </button>
  </div>
  <div class="filter-btn">
    <button id="month" class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
      Month
    </button>
  </div>
  <div class="filter-input" id="date-input">
    <input type="date" class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray form-input">
  </div>
</div>
<div class="w-full overflow-hidden rounded-lg shadow-xs">
  <div class="w-full overflow-hidden rounded-lg shadow-xs">
    <div class="w-full overflow-x-auto">
      <table class="w-full whitespace-no-wrap">
        <thead>
        <tr
                class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
        >
          <th class="px-4 py-3">Date</th>
          <th class="px-4 py-3">Time Checkin</th>
          <th class="px-4 py-3">Time Checkout</th>
          <th class="px-4 py-3">Status</th>
        </tr>
        </thead>
        <tbody
                class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
        >

        <c:forEach var="line" items="${listCheckin}">

          <tr class="text-gray-700 dark:text-gray-400">
            <td class="px-4 py-3">
              <div class="flex items-center text-sm">
                <p class="font-semibold">${line.day}</p>
              </div>
            </td>

            <td class="px-4 py-3 text-sm">
                ${line.timeCheckin == null ? 'Null' : line.timeCheckin}
            </td>
            <td class="px-4 py-3 text-sm">
                ${line.timeCheckout == null ? 'Null' : line.timeCheckout}
            </td>
            <td class="px-4 py-3 text-xs">
                            <span
                                    class="px-2 py-1 font-semibold leading-tight  <c:if test="${line.status}">text-green-700 bg-green-100</c:if> rounded-full <c:if test="${!line.status}">text-red-700 bg-red-100</c:if> "
                            >
                                ${line.status ? "Checked" : "Uncheck"}
                            </span>
            </td>
          </tr>

        </c:forEach>


        </tbody>
      </table>
    </div>
  </div>
</div>

<c:import url="../../footerAdmin.jsp"/>
