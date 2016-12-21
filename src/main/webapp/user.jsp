<%@page import="com.revature.beans.ErsUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">

<style type="text/css">

    #x{
        padding-top: 30px;
    }

    /* The side navigation menu */
    .sidenav {
        height: 100%; /* 100% Full-height */
        width: 0; /* 0 width - change this with JavaScript */
        position: fixed; /* Stay in place */
        z-index: 1; /* Stay on top */
        top: 0;
        left: 0;
        background-color: rgb(30,40,50); /* Black*/
        overflow-x: hidden; /* Disable horizontal scroll */
        padding-top: 60px; /* Place content 60px from the top */
        transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
    }
    
    .bottonNav {
        height: 0; /* 100% Full-height */
        width: 250px; /* 0 width - change this with JavaScript */
        position: fixed; /* Stay in place */
        background-color: rgb(40,50,60); 
        overflow-x: hidden; /* Disable horizontal scroll */
        padding-top: 0px; /* Place content 60px from the top */
        transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
    }

    /* The navigation menu links */
    .sidenav a {
        padding: 8px 8px 8px 32px;
        text-decoration: none;
        font-size: 25px;
        color: #818181;
        display: block;
        transition: 0.3s
    }

    /* When you mouse over the navigation links, change their color */
    .sidenav a:hover, .offcanvas a:focus{
        color: #f1f1f1;
    }

    /* Position and style the close button (top right corner) */
    .sidenav .closebtn {
        position: absolute;
        top: 30px;
        right: 25px;
        font-size: 36px;
        margin-left: 50px;
    }

    /* Style page content - use this if you want to push the page content to the right when you open the side navigation */
    #main {
        transition: margin-left .5s;
        padding: 20px;
    }


    /* NAV BAR PROPERTIES*/
    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
        color: #000;  /*Sets the text hover color on navbar*/
    }

    .navbar-fixed-top .navbar-nav > .active > a, .navbar-default .navbar-nav > .active >
    a:hover, .navbar-fixed-top .navbar-nav > .active > a:focus {
        color: white; /*BACKGROUND color for active*/
        background-color: #030033;
    }

    .navbar-default {
        background-color: rgb(30,40,50);
        border-color: #030033;
    }



    .navbar-default .navbar-nav > li > a {
        color: white; /*Change active text color here*/
       
    }
        #hoverColor tbody tr:HOVER{
            background-color: #602069;
        }
        tbody{
            background-color: rgb(30,40,50);
        }
     .sideNavText{
     	color: white;
     }
</style>


</head>





<body>

<div id="mySidenav" class="sidenav">
    <br>

    <a id="x" href="javascript:void(0)" class="closebtn" onclick="closeNav(); closeButtonNav();">&times;</a> 
    
    <br><br>
    <form action="logoutServlet" method="POST">
		<input type="submit" class="btn btn-primary btn-sm btn-block" value="Logout"/> 
	</form>
        <a href="#" onclick="openButtonNav()" >Request</a>
    <div id="myButtonNav" class="bottonNav" >
    

    
    <form method="POST" action="insertReim">
	  <div class="form-group">
	    <label class="sideNavText" for="exampleSelect1">Type</label>
	    <select name="type" class="form-control" id="exampleSelect1">
	      <option>LODGING</option>
	      <option>TRAVEL</option>
	      <option>FOOD</option>
	      <option>OTHER</option>
	    </select>
	  </div>

	 <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	    <div class="input-group">
	      <div class="input-group-addon">$</div>
	      <input name="amount" type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
	      <div class="input-group-addon"></div>
	    </div>
	    
  <div class="form-group">
    <label class="sideNavText" for="exampleTextarea">Description</label>
    <textarea name="desc" class="form-control" id="exampleTextarea" rows="3"></textarea>
  </div>
  
  <button type="submit" class="btn btn-primary btn-sm btn-block">Submit</button>
</form>
    </div>


</div>


<!-- MY NAV BAR -->
<nav class="navbar navbar-fixed-top navbar-light bg-faded" style="background-color: rgb(30,40,50);">
       <button onclick="openNav()" class="navbar-toggler" style="background-color: rgb(170,175,179);" type=" button" data-toggle="collapse" data-target="#exCollapsingNavbar" aria-controls="exCollapsingNavbar" aria-expanded="false" aria-label="Toggle navigation"></button>
<span class="navbar-text float-xs-right text-muted">
<% ErsUser user = (ErsUser) session.getAttribute("userSession");
String welcomeMessage = "Welcome " + user.getFn() + " "+ user.getLn();%>
	<b><%= welcomeMessage %></b>


   	
  </span>


</nav>
<!-- MY NAV BAR END-->




<!-- Use any element to open the sidenav -->

<!-- Add all page content inside this div if you want the side nav to push page content to the right (not used if you only want the sidenav to sit on top of the page -->
<div id="main">


    <br><br>
    <table class="table table-bordered table-inverse table-hover" id="hoverColor">
        <tr>
            <th>Type</th>
            <th>Description</th>
            <th>Cost</th>
            <th>Date Submitted</th>
            <th>Status</th>
            <th>Date Resolved</th>
            <th>Resolver Name</th>
        </tr>
        <c:forEach var="reimb" items="${reimb}">
            <tr>
                <td><c:out value="${reimb.typeID.type}"> </c:out></td>
                <td><c:out value="${reimb.description}"> </c:out></td>
                <td><c:out value="${reimb.amount}"> </c:out></td>
                <td><c:out value="${reimb.submitted}"> </c:out></td>
                <td><c:out value="${reimb.statusID.status}"> </c:out></td>
                <td><c:out value="${reimb.resolved}"> </c:out></td>
                <td><c:out value="${reimb.resolver.fn}"> </c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>

     <%
			String wrongNumber=(String)request.getAttribute("numberError");  
			if(wrongNumber!=null){
				out.println(wrongNumber);
				}
	%>



<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft= "0";
    }
    
    function openButtonNav() {
	    document.getElementById("myButtonNav").style.height = "300px";
    }
    function closeButtonNav() {
	    document.getElementById("myButtonNav").style.width = "0";
        document.getElementById("main").style.marginLeft= "0";
    }
</script>

<!-- jQuery first, then Tether, then Bootstrap JS. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js" integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU" crossorigin="anonymous"></script>
</body>
</html>