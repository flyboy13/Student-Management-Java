<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> -->
<html>
<head>
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>/" class="navbar-brand"> Student Management</a>
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
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${course != null}">
					<form action="updatecourse" method="post">
				</c:if>
				<c:if test="${course == null}">
					<form action="insertcourse" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${course != null}">
            			Edit course
            		</c:if>
						<c:if test="${course == null}">
            			Add new course
            		</c:if>
					</h2>
				</caption>

			

				<fieldset class="form-group">
					<label>ID</label> <input type="number"
						value="<c:out value='${course.classId}' />" class="form-control"
						name="classId" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${course.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Lecture</label> <input type=".text"
						value="<c:out value='${course.lecture}' />" class="form-control"
						name="lecture">
				</fieldset>

				<fieldset class="form-group">
					<label>Year</label> <input type="number"
						value="<c:out value='${course.year}' />" class="form-control"
						name="year">
				</fieldset>
				<fieldset class="form-group">
					<label>Notes</label> <input type="text"
						value="<c:out value='${course.notes}' />" class="form-control"
						name="notes">
				</fieldset>
				<c:if test="${course == null}">
					<button  class="btn btn-success">Add</button>
            		</c:if>

					<c:if test="${course != null}">
						<button  class="btn btn-success">Save</button>
					
            		</c:if>
				
				</form>
			</div>
		</div>
	</div>
</body>
</html>
