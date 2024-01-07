<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/4/2024
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../../headerAdmin.jsp"/>
<c:if test="${not empty errorMess}">
    <div class="error-message bg-red-600 text-center font-bold">
        <p>${errorMess}</p>
    </div>
</c:if>
<div style="text-align: center">
    <h1 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">CREAT PROJECT</h1>
</div>

<form action="/admin-page?act=insert-project" method="post">
    <div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
        <label class="block text-sm">
            <span class="text-gray-700 dark:text-gray-400">Project</span>
            <input name="projectName"
                   class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                   placeholder="Enter Project Name" required>
        </label>
        <label class="block text-sm">
            <span class="text-gray-700 dark:text-gray-400">Deadline</span>
            <input type="date" name="deadline"
                   class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                   required>
        </label>
        <label class="block text-sm">
            <span class="text-gray-700 dark:text-gray-400">Select Team</span>
            <select name="teamId"
                    class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                    required
            >
                <option value="">Select team</option>
                <c:forEach var="team" items="${listTeam}">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
        </label>
        <div class="mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                  List employee
                </span>
            <div class="flex justify-between mt-2 mb-2">
                <div>
                    <label class="inline-flex items-center text-gray-600 dark:text-gray-400">
                        <input type="radio"
                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                               name="optionShow" value="">
                        <span class="ml-2">ALL</span>
                    </label>
                    <c:forEach var="team" items="${listTeamOption}">
                        <label class="inline-flex items-center text-gray-600 dark:text-gray-400">
                            <input type="radio"
                                   class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                   name="optionShow" value="${team.id}">
                            <span class="ml-2">${team.name}</span>
                        </label>
                    </c:forEach>

                </div>

                <div id="btn-showList" onclick="handleShow()" style="cursor: pointer"
                        class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
                    Show
                </div>

            </div>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
                <table class="w-full whitespace-no-wrap">
                    <thead>
                    <tr
                            class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                    >
                        <th class="px-4 py-3">Member Name</th>
                        <th class="px-4 py-3">Phone Number</th>
                        <th class="px-4 py-3">Email</th>
                        <th class="px-4 py-3">Team</th>
                    </tr>
                    </thead>
                    <tbody
                            class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800" id="listDefault"
                    >

                    <c:forEach var="employee" items="${listEmployee}">

                        <tr class="text-gray-700 dark:text-gray-400">
                            <td class="px-4 py-3 text-sm">
                                <p class="font-semibold">${employee.fullName}</p>
                            </td>
                            <td class="px-4 py-3 text-sm">
                                    ${employee.phoneNum}
                            </td>
                            <td class="px-4 py-3 text-sm">
                                    ${employee.email}
                            </td>
                            <td class="px-4 py-3 text-xs">
                                    ${employee.teamId}
                            </td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>

        </div>

    </div>


    <button class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
        Submit
    </button>
</form>
<c:import url="../../footerAdmin.jsp"/>
<script>
    function handleShow() {
        // document.getElementById("listDefault").classList.add("hidden")
        const selectOption = document.querySelector("input[name='optionShow']:checked")
        if(selectOption){
            const vOption = selectOption.value;
            if (vOption === ""){
                const rows = document.querySelectorAll("#listDefault tr");
                rows.forEach(row => {
                    row.style.display = ""; // Hiển thị tất cả các hàng khi không có tùy chọn nào được chọn
                });
                return;
            }
            const rows = document.querySelectorAll("#listDefault tr");

            rows.forEach(row => {
                const teamId = row.querySelector("td:nth-child(4)").textContent.trim(); // Lấy giá trị teamId từ cell thứ 4 trong mỗi hàng
                if (teamId !== vOption) {
                    row.style.display = "none"; // Ẩn các hàng không phải của team có teamId = 1
                }else {
                    row.style.display = "";
                }
            });
        }
    }
</script>
