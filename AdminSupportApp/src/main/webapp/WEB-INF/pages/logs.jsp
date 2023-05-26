<%@page import="net.etfbl.ip.dto.Log"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="LogsBean" class="net.etfbl.ip.beans.LogsBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logs statistics</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/mainStyle.css" type="text/css" rel="stylesheet">

</head>
<body>

	<%@include file="navbar.jsp" %>
	<p class="logs-header">Logs overview</p>
	<table class="table table-hover">
		<thead class="table-primary ">
			<tr>
      			<th scope="col">ID</th>
      			<th scope="col">Content</th>
      			<th scope="col">Date And Time</th>
    		</tr>
		</thead>
		<tbody>
			<%
				for(Log log : LogsBean.getLogs()) {
					out.println("<tr><td>" + log.getId() + "</td>");
					out.println("<td>" + log.getContent() + "</td>");
					out.println("<td>" + log.getDateAndTime() + "</td></tr>");			
				}
			%>
		</tbody>
	</table>
</body>
</html>