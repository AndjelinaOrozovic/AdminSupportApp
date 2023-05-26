<%@page import="net.etfbl.ip.beans.CategoriesBean"%>
<%@page import="net.etfbl.ip.dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="categoriesBean" class="net.etfbl.ip.beans.CategoriesBean" scope="session"></jsp:useBean>
<jsp:useBean id="attributesBean" class="net.etfbl.ip.beans.AttributesBean" scope="session"></jsp:useBean>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link href="styles/categoriesStyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%@include file="navbar.jsp" %>
	<div class="categories-header">
		<span>Categories overview</span>
		<form action="?action=addNewCategory" method="post" class="new-category">
        	<input type="text" name="newCategory" id="newCategory" class="form-control" placeholder="Add new category" required/>
			<button class="btn btn-primary px-3 new-category-btn" type="submit" name="submit"><i class="bi bi-plus-square add-category-icon"></i></button>
		</form>
	</div>
	<p class="result-message"><%=session.getAttribute("actionNotification")!=null?session.getAttribute("actionNotification"):""%></p>
	<table class="table table-hover">
		<thead class="table-primary ">
			<tr>
      			<th scope="col">ID</th>
      			<th scope="col">Parent ID</th>
      			<th scope="col">Name</th>
      			<th scope="col">Action</th>
    		</tr>
		</thead>
		<tbody>
			<%
				for(Category category : CategoriesBean.getAllCategories()) {
					if(!category.isDeleted()) {
						out.println("<tr><td>" + category.getId() + "</td>");
						out.println("<td>" + category.getId_parent() + "</td>");
						out.println("<td>" + category.getName() + "</td>");	
						out.println("<td><a class=\"action-button\" title=\"edit\" data-toggle=\"tooltip\" href=\"?action=editCategory&id=" + category.getId() + "\"><i class=\"bi bi-pen\">" + "</i>" + "</a>" +
								"<a class=\"action-button\" title=\"delete\" data-toggle=\"tooltip\" href=\"?action=deleteCategory&id=" + category.getId() + "\"><i class=\"bi bi-trash\">" + "</i>" + "</a></td></tr>");
				
					}
				}
			%>
		</tbody>
	</table>
	<p class="notification"><%=session.getAttribute("notification")%></p>
</body>
</html>