<%@page import="net.etfbl.ip.dto.Attribute"%>
<%@page import="net.etfbl.ip.dto.Category"%>
<%@page import="net.etfbl.ip.dto.User"%>
<%@page import="net.etfbl.ip.beans.UsersBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="categoriesBean" class="net.etfbl.ip.beans.CategoriesBean" scope="session"></jsp:useBean>
<jsp:useBean id="attributesBean" class="net.etfbl.ip.beans.AttributesBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit category</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link href="styles/editCategoryStyle.css" rel="stylesheet">

</head>
<body onload="init()">

<%
	
	Integer id = Integer.parseInt(request.getParameter("id"));
	Category selectedCategory = categoriesBean.getCategoryById(id);
	Integer idParentCategory = selectedCategory.getId_parent();

%>

<div class="container px-4 py-4 px-md-3 text-center text-lg-start my-2">
    <div class="row gx-lg-5 align-items-center mb-5">

      <div class="col-lg-12 mb-5 mb-lg-0 position-relative">
        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

        <div class="card bg-glass">
          <div class="card-body px-4 py-4 px-md-5">
              
              <% if(selectedCategory.getId_parent() != 0) { %>
              
              <!-- Category full name -->
              <div class="form-outline mb-4">
              	<label class="form-label" for="categoryFullName">Category full name</label>
              	<input type="text" name="categoryFullName" id="categoryFullName" class="form-control" value="<%=categoriesBean.getCategoryFullName(selectedCategory)%>" readonly/>
              </div>
             
              <% } %>
              
              <!-- Category name -->	
              <form action="?action=updateCategory&id=<%=id%>" method="post" class="form-outline mb-4">
             	 <label class="form-label" for="categoryName">Category name</label>
             	 <div class="new-category-name">
        			<input type="text" name="categoryName" id="categoryName" class="form-control" placeholder="Category name" value="<%=selectedCategory.getName()%>" required/>
					<button class="btn btn-primary px-3 new-save-btn" type="submit" name="submit"><i class="bi bi-save"></i></button>
			  	</div>
			  </form>
			  
			  <!-- Add subcategory -->	
              <form action="?action=addSubcategory&id=<%=id%>" method="post" class="form-outline mb-4">
             	 <label class="form-label" for="attributeName">Add subcategory</label>
             	 <div class="new-category-name">
        			<input type="text" name="subcategoryName" id="subcategoryName" class="form-control" placeholder="New subcategory" required/>
					<button class="btn btn-primary px-3 new-save-btn" type="submit" name="submit"><i class="bi bi-save"></i></button>
			  	</div>
			  </form>
			  
			  <!-- Attributes -->
			  <label class="form-label" for="atributes">Attributes</label>
			  <table class="table table-hover">
				<thead class="table-primary ">
					<tr>
      					<th scope="col">ID</th>
      					<th scope="col">Name</th>
      					<th scope="col">Delete</th>
    				</tr>
				</thead>
				<tbody>
					<%
						for(Attribute attribute : selectedCategory.getAttributes()) {
							if(!attribute.isDeleted()) {
								out.println("<tr><td>" + attribute.getId() + "</td>");
								out.println("<td>" + attribute.getName() + "</td>");
								out.println("<td><a class=\"action-button\" title=\"delete\" data-toggle=\"tooltip\" href=\"?action=deleteAttribute&id=" + attribute.getId() + "&idCategory=" + id + "\"><i class=\"bi bi-trash\">" + "</i>" + "</a></td></tr>");
							}
					}
				%>
				</tbody>
			</table>
			
			<!-- Add attribute -->	
              <form action="?action=addAttribute&id=<%=id%>&idParentCategory=<%=idParentCategory%>" method="post" class="form-outline mb-4">
             	 <label class="form-label" for="attributeName">Add new attribute</label>
             	 <div class="new-category-name">
        			<input type="text" name="attributeName" id="attributeName" class="form-control" placeholder="New attribute" required/>
					<button class="btn btn-primary px-3 new-save-btn" type="submit" name="submit"><i class="bi bi-save"></i></button>
			  	</div>
			  </form>
              	
              <p class="mail-notification"><%=session.getAttribute("notification")%></p>
              
            <div>
              <!-- Back button -->
              <a class="btn btn-outline-secondary btn-block mb-4" href="?action=categories">Back</a>
            </div>
              
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>