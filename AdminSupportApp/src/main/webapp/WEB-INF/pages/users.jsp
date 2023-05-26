<%@page import="net.etfbl.ip.beans.UsersBean"%>
<%@page import="net.etfbl.ip.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link href="styles/usersStyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%@include file="navbar.jsp" %>
	<div class="users-header">
		<span>Users overview</span>
		<a class="add add-button"  href="?action=newUser" title="add-user" data-toggle="Add user"><i class="bi bi-person-plus add-user"></i></a>
	</div>
	<p class="result-message"><%=session.getAttribute("actionNotification")!=null?session.getAttribute("actionNotification"):""%></p>
	<table class="table table-hover">
		<thead class="table-primary ">
			<tr>
      			<th scope="col">ID</th>
      			<th scope="col">First name</th>
      			<th scope="col">Last name</th>
      			<th scope="col">Username</th>
      			<th scope="col">City</th>
      			<th scope="col">Mail</th>
      			<th scope="col">Is active</th>
      			<th scope="col">Actions</th>
    		</tr>
		</thead>
		<tbody>
			<%
				for(User user : UsersBean.getUsers()) {
					if(!user.isDeleted()) {
						out.println("<tr><td>" + user.getId() + "</td>");
						out.println("<td>" + user.getFirstName() + "</td>");
						out.println("<td>" + user.getLastName() + "</td>");
						out.println("<td>" + user.getUsername() + "</td>");
						out.println("<td>" + user.getCity() + "</td>");
						out.println("<td>" + user.getMail() + "</td>");
						if(user.isActivated()) {
							out.println("<td>Yes</td>");
						} else {
							out.println("<td>No</td>");
						}
						out.println("<td><a class=\"action-button\" title=\"edit\" data-toggle=\"tooltip\" href=\"?action=editUser&id=" + user.getId() + "\"><i class=\"bi bi-pen\">" + "</i>" + "</a>" +
						"<a class=\"delete-button\" title=\"delete\" data-toggle=\"tooltip\" href=\"?action=deleteUser&id=" + user.getId() + "\"><i class=\"bi bi-trash\">" + "</i>" + "</a></td></tr>");
					}
				}
			%>
		</tbody>
	</table>
</body>
</html>