<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<head>  
<title>Employee Management System</title>  
</head>  
<body>  
<h1>Employee Management System</h1>  
<h2>
<% out.print(request.getParameter("message")); %><br></h2>
<a href = "project.jsp"><button> Go To Home Page </button></a>
</body>  
</html>