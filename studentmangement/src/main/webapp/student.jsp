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
				<c:if test="${student != null}">
					<form action="updatestudent" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="insertstudent" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${student != null}">
            			Edit student
            		</c:if>
						<c:if test="${student == null}">
            			Add new student
            		</c:if>
					</h2>
				</caption>

			

				<fieldset class="form-group">
					<label>ID</label> <input type="number"
						value="<c:out value='${student.id}' />" class="form-control"
						name="id" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Grade</label> <input type="text"
						value="<c:out value='${student.grade}' />" class="form-control"
						name="grade">
				</fieldset>

				<fieldset class="form-group">
					<label>Birthday</label> <input type="date"
						value="<c:out value='${student.birthday}' />" class="form-control"
						name="birthday">
				</fieldset>
				<fieldset class="form-group">
					<label>Address</label> <input type="text"
						value="<c:out value='${student.address}' />" class="form-control"
						name="address">
				</fieldset>
				<fieldset class="form-group">
					<label>Notes</label> <input type="text"
						value="<c:out value='${student.notes}' />" class="form-control"
						name="notes">
				</fieldset>
				<c:if test="${student == null}">
					<button  class="btn btn-success">Add</button>
            		</c:if>

					<c:if test="${student != null}">
						<button  class="btn btn-success">Save</button>
					
            		</c:if>
				
				</form>
			</div>
		</div>
	</div>
</body>
</html>
