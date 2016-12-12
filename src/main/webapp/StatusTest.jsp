<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateStatusServlet" method="POST">
Status number: <input  type="text" name="statusNumber"><br>
Employee ID: <input type="text" name="employeeID"><br>
<input type="submit" value="change status">
</form>

</body>
</html>