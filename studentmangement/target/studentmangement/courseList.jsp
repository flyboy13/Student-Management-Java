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
                        <h2>List of Courses</h2>
                    </caption>
                    <div align="center">
                        <table border="1" cellpadding="5" id="myTable1">
                            <tr>
                                <th>ID class</th>
                                <th onclick="sortTable(1)">Name</th>
                                <th>Lecture</th>
                                <th>Year</th>
                                <th>Notes</th>
                                <th>Actions</th>
                                <th>Management</th>
                            </tr>

                            <c:forEach var="course" items="${listCourse}">
                                <tr>
                                    <td>
                                        <c:out
                                            value="${course.classId}"
                                            escapeXml="false"
                                        />
                                    </td>
                                    <td><c:out value="${course.name}" /></td>
                                    <td><c:out value="${course.lecture}" /></td>
                                    <td>
                                        <c:out value="${course.year}" />
                                    </td>
                                    <td><c:out value="${course.notes}" /></td>
                                    <td class="w-auto">
                                        <a
                                            href="/studentmangement/editcourse?classId=<c:out value='${course.classId}' />"
                                            >Edit</a
                                        >
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a
                                            href="/studentmangement/deletecourse?classId=<c:out value='${course.classId}' />"
                                            >Delete</a
                                        >
                                    </td>
                                    <td>
                                        <a
                                            href="/studentmangement/managecourse?classId=<c:out value='${course.classId}' />"
                                            >Management</a
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
            console.log(number);
            var table, rows, switching, i, x, y, shouldSwitch;
            table = document.getElementById("myTable1");
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
</html>
