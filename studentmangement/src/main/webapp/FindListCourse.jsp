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

        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <caption>
                        <h2>Find Course</h2>
                    </caption>
                    <form action="findcourse" method="post">
                        <fieldset class="form-group">
                            <label>Name</label>
                            <input
                                type="text"
                                class="form-control"
                                name="courseInput"
                                required="required"
                            />
                        </fieldset>
                    </form>
                    <div align="center">
                        <table border="1" cellpadding="5">
                            <c:if test="${course != null}">
                                <tr>
                                    <th>Class Id</th>
                                    <th>Name</th>
                                    <th>Lecture</th>
                                    <th>Year</th>
                                    <th>Notes</th>
                                    <th>Actions</th>
                                    <th>Management</th>
                                </tr>

                                <tr>
                                    <td>
                                        <c:out
                                            value="${course.classId}"
                                            escapeXml="false"
                                        />
                                    </td>
                                    <td>
                                        <c:out value="${course.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${course.lecture}" />
                                    </td>
                                    <td>
                                        <c:out value="${course.year}" />
                                    </td>
                                    <td>
                                        <c:out value="${course.notes}" />
                                    </td>
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
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
