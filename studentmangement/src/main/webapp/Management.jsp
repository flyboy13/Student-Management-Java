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
        <link rel="stylesheet" type="text/css" href="table.css" />
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="app.js"></script>
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
                        <h2>
                            Course name:
                            <c:out value="${course.name}" escapeXml="false" />
                        </h2>
                    </caption>
                    <br />
                    <div>
                        <h2>Add student course</h2>
                        <form action="addStudentCourse" method="post">
                            <fieldset class="form-group">
                                <input
                                    type="text"
                                    class="form-control trans"
                                    name="classId"
                                    value="<c:out value='${course.classId}' />"
                                    escapeXml="false"
                                />
                                <label>Student ID:</label>
                                <input
                                    type="number"
                                    class="form-control"
                                    name="id"
                                    required="required"
                                />
                            </fieldset>

                            <button class="btn btn-success">Add</button>
                        </form>
                        <c:if test="${check == 3}">
                            <h5>Add successfull</h5>
                        </c:if>
                        <c:if test="${check == -1}">
                            <h5>Not exist student!!</h5>
                        </c:if>
                        <c:if test="${check == 2}">
                            <h5>Student already in this course!!</h5>
                        </c:if>
                        <c:if test="${check == 4}">
                            <h5>
                                Something went wrong!! Please try again later
                            </h5>
                        </c:if>
                    </div>
                    <br />
                    <div>
                        <h2>Student of the course</h2>

                        <div align="center">
                            <table border="1" cellpadding="5">
                                <c:if test="${listStudent != null}">
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th >Grade</th>
                                        <th>Birthday</th>
                                        <th>Address</th>
                                        <th>Notes</th>
                                        <th width="50%">Actions</th>
                                    </tr>
                                    <c:forEach
                                        var="student"
                                        items="${listStudent}"
                                    >
                                        <tr>
                                            <td>
                                                <c:out
                                                    value="${student.id}"
                                                    escapeXml="false"
                                                />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${student.name}"
                                                />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${student.grade}"
                                                />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${student.birthday}"
                                                />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${student.address}"
                                                />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${student.notes}"
                                                />
                                            </td>
                                            <td>
                                                <a
                                                    href="/studentmangement/removestudentfromcourse?id=<c:out value='${student.id}'/>&classId=<c:out value='${course.classId}'/>"
                                                    >Remove</a
                                                >
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <style>
        .trans {
            opacity: 0;
        }
    </style>
</html>
