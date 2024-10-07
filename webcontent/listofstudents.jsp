<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ include file="sessionCheck.jsp"%>
<title>List of Students</title>

<div class="container">
	<%
    String successMessage = (String) request.getAttribute("successMessage");
    if (successMessage != null) {
        
       
%>
	<!-- Optionally you can style the message below -->
	<div class="success-message"><%= successMessage %></div>
	<%
    } 
%>


	<h1>List of Students</h1>

	<table>
		<thead>
			<tr>
				<th>Student Code</th>
				<th>Roll Number</th>
				<th>Marks</th>
				<th>Branch</th>
				<th>College</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Father Name</th>
				<th>Mobile Number</th>
				<th>Date of Birth</th>
				<th>Address</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${studentlist}">
				<tr>
					<td>${student.studentcode}</td>
					<td>${student.rollnumber}</td>
					<td>${student.marks}</td>
					<td>${student.branch}</td>
					<td>${student.college}</td>
					<td>${student.firstname}</td>
					<td>${student.lastname}</td>
					<td>${student.fathername}</td>
					<td>${student.mobileno}</td>
					<td>${student.dateofbirth}</td>
					<td>${student.address}</td>
					<td>${student.status}</td>
					<td><a href="EditServlet?rollnumber=${student.rollnumber}">Edit</a>
						| <a href="DeleteStudentServlet?rollnumber=${student.rollnumber}"
						onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="footer.jsp" />
