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
                    <br />
                    <div>
                        <h2>My course in the year</h2>
                        <form action="mycourse" method="post">
                            <fieldset class="form-group">
                                <input
                                    type="number"
                                    class="form-control trans"
                                    name="id"
                                    value="<c:out value='${id}'/>"
                                    escapeXml="false"
                                />
                                <label>Year:</label>
                                <input
                                    type="number"
                                    class="form-control"
                                    name="year"
                                    required="required"
                                />
                            </fieldset>

                            <button class="btn btn-success">Find</button>
                        </form>
                    </div>
                    <br />
                    <div>
                        <c:if test="${listCourse != null}">
                            <h2>My course:</h2>

                            <div align="center">
                                <table border="1" cellpadding="5">
                                    <tr>
                                        <th>ID class</th>
                                        <th>Name</th>
                                        <th>Lecture</th>
                                        <th>Year</th>
                                        <th>Notes</th>
                                    </tr>
                                    <c:forEach
                                        var="course"
                                        items="${listCourse}"
                                    >
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
                                                <c:out
                                                    value="${course.lecture}"
                                                />
                                            </td>
                                            <td>
                                                <c:out value="${course.year}" />
                                            </td>
                                            <td>
                                                <c:out
                                                    value="${course.notes}"
                                                />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </c:if>
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
