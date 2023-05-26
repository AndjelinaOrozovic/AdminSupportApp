<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="adminBean" class="net.etfbl.ip.beans.AdminBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
<link href="styles/navbar.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">


</head>
<body>
	<!--Main Navigation-->
<header>
  <!-- Sidebar -->
  <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
    <div class="position-sticky">
      <div class="list-group list-group-flush mx-3 mt-4">
        <a href="?action=categories" class="list-group-item list-group-item-action py-2 ripple">
          <i class="bi bi-box-seam"></i><span> Categories</span>
        </a>
        <a href="?action=users" class="list-group-item list-group-item-action py-2 ripple">
          <i class="bi bi-people"></i><span> Users</span>
        </a>
        <a href="?action=logs" class="list-group-item list-group-item-action py-2 ripple">
          <i class="bi bi-bar-chart-line"></i><span> Logs statistics</span></a>
        <a href="?action=logout" class="list-group-item list-group-item-action py-2 ripple">
          <i class="bi bi-box-arrow-left"></i><span> Logout</span></a>
      </div>
    </div>
  </nav>
  <!-- Sidebar -->

  <!-- Navbar -->
  <nav id="main-navbar" class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
    <!-- Container wrapper -->
    <div class="container-fluid">
      
      <!-- Brand -->
      <a class="navbar-brand" href="#">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSz3f4tn5D6-R8JeRljoS8StxuTGaxUfV-x-auc_7Y5EdR58zhBy9qqPVV9QLZpp_VU_-g&usqp=CAU" height="25" alt="MDB Logo"/>
      </a> <span><%=adminBean.getAdmin().getFirstName()%> <%=adminBean.getAdmin().getLastName()%></span>
      
    </div>
    <!-- Container wrapper -->
  </nav>
  <!-- Navbar -->
</header>
<!--Main Navigation-->

</body>
</html>