<%@page import="net.etfbl.ip.dto.User"%>
<%@page import="net.etfbl.ip.beans.UsersBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="usersBean" class="net.etfbl.ip.beans.UsersBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit user</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link href="styles/newUserStyle.css" rel="stylesheet">

</head>
<body onload="init()">

<%
	
	Integer id = Integer.parseInt(request.getParameter("id"));
	User selectedUser = usersBean.getById(id);
	boolean isActive = selectedUser.isActivated();

%>

<div class="container px-4 py-4 px-md-3 text-center text-lg-start my-2">
    <div class="row gx-lg-5 align-items-center mb-5">

      <div class="col-lg-12 mb-5 mb-lg-0 position-relative">
        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

        <div class="card bg-glass">
          <div class="card-body px-4 py-4 px-md-5">
            <form method="post" action="?action=updateUser&id=<%=id%>">
              
              <!-- First name -->
              <div class="form-outline mb-4">
              	<input type="text" name="firstName" id="firstName" class="form-control" placeholder="First name" value="<%=selectedUser.getFirstName()%>" required/>
              </div>
              
              <!-- Last name -->
              <div class="form-outline mb-4">
              	<input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last name" value="<%=selectedUser.getLastName()%>" required/>
              </div>

              <!-- Username -->
              <div class="form-outline mb-4">
              	<input type="text" name="username" id="username" class="form-control" placeholder="Username" value="<%=selectedUser.getUsername()%>" readonly/>
              </div>
              
              <!-- City -->
              <div class="form-outline mb-4">
              	<input type="text" name="city" id="city" class="form-control" placeholder="City" value="<%=selectedUser.getCity()%>" required/>
              </div>
              
              <!-- Email -->
              <div class="form-outline mb-4">
              	<input type="email" name="email" id="email" class="form-control" placeholder="Email" value="<%=selectedUser.getMail()%>" required/>
              </div>
 
              
              <!-- Avatar -->
              <div class="form-outline mb-4">
              	<input type="text" name="avatar" id="avatar" class="form-control" value="<%=(selectedUser.getAvatar()!=null)?selectedUser.getAvatar():""%>" placeholder="Avatar"/>
              </div>
              
              <!--  Is Active -->
              <div class="form-check form-switch">
              <% if(isActive) { %>
 				 <input class="form-check-input" type="checkbox" id="isActive" name="isActive" value="Yes" checked>
 				 <label class="form-check-label" for="isActive">Is user active</label>
 			  <% } else { %>
 				 <input class="form-check-input" type="checkbox" id="isActive" name="isActive" value="No">
 				 <label class="form-check-label" for="isActive">Is user active</label>
 			  <% } %>
			  </div>
             				
              <p class="mail-notification"><%=session.getAttribute("notification")%></p>
              
              <div class="response-buttons">
              	<!-- Back button -->
              	<a class="btn btn-outline-secondary btn-block mb-4" href="?action=users">Back</a>
              	<!-- Submit button -->
              	<button type="submit" name="submit" class="btn btn-primary btn-block mb-4">Save</button>
              </div>
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>