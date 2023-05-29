<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<html>
    <head>
        <title>Students Management Application</title>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
        />
    </head>
    <body>
        <header>
            <nav
                class="navbar navbar-expand-md navbar-dark"
                style="background-color: tomato"
            >
                <div>
                    <a
                        href="<%=request.getContextPath()%>/"
                        class="navbar-brand"
                    >
                        Student Management</a
                    >
                </div>

                <ul class="navbar-nav">
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/liststudent"
                            class="nav-link"
                            >Student list</a
                        >
                    </li>
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/newstudent"
                            class="nav-link"
                            >Add student</a
                        >
                    </li>
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/findstudent"
                            class="nav-link"
                            >Find student</a
                        >
                    </li>
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/listcourse"
                            class="nav-link"
                            >Course list</a
                        >
                    </li>
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/addcourse"
                            class="nav-link"
                            >Add course</a
                        >
                    </li>
                    <li>
                        <a
                            href="<%=request.getContextPath()%>/findcourse"
                            class="nav-link"
                            >Find course</a
                        >
                    </li>
                </ul>
            </nav>
        </header>
        <br />
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <caption>
                        <h2>List of Students</h2>
                    </caption>
                    <div>
                        <table
                            border="1"
                            cellpadding="5"
                            style="width: 100%"
                            id="myTable"
                        >
                            <tr>
                                <th>ID</th>
                                <th onclick="sortTable(1)">Name</th>
                                <th onclick="sortTable(2)">Grade</th>
                                <th>Birthday</th>
                                <th>Address</th>
                                <th>Notes</th>
                                <th class="width w-25">Actions</th>
                            </tr>

                            <c:forEach var="student" items="${listStudent}">
                                <tr>
                                    <td>
                                        <c:out
                                            value="${student.id}"
                                            escapeXml="false"
                                        />
                                    </td>
                                    <td><c:out value="${student.name}" /></td>
                                    <td><c:out value="${student.grade}" /></td>
                                    <td>
                                        <c:out value="${student.birthday}" />
                                    </td>
                                    <td>
                                        <c:out value="${student.address}" />
                                    </td>
                                    <td><c:out value="${student.notes}" /></td>
                                    <td class="w-auto">
                                        <a
                                            href="/studentmangement/editstudent?id=<c:out value='${student.id}' />"
                                            >Edit</a
                                        >
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a
                                            href="/studentmangement/deletestudent?id=<c:out value='${student.id}' />"
                                            >Delete</a
                                        >
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a
                                            href="/studentmangement/mycourse?id=<c:out value='${student.id}' />"
                                            >course</a
                                        >
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        var number = 0;
        function sortTable(columnIndex) {
            number = number + 1;
            // console.log(number);
            var table, rows, switching, i, x, y, shouldSwitch;
            table = document.getElementById("myTable");
            switching = true;
            while (switching) {
                switching = false;
                rows = table.getElementsByTagName("tr");
                for (i = 1; i < rows.length - 1; i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("td")[columnIndex];
                    y = rows[i + 1].getElementsByTagName("td")[columnIndex];
                    if (number % 2 === 0) {
                        if (
                            x.innerHTML.toLowerCase() <
                            y.innerHTML.toLowerCase()
                        ) {
                            shouldSwitch = true;
                            break;
                        }
                    } else {
                        if (
                            x.innerHTML.toLowerCase() >
                            y.innerHTML.toLowerCase()
                        ) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                }
            }
        }
    </script>

    <style>
        .width {
            width: 150px;
        }
    </style>
</html>
