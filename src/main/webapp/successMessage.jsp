<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<head>  
<head>
    <title>
        Employee Management System
    </title>
    <link rel="stylesheet" href="EmployeeStylePage.css">
</head>
</head>  
<body>  
<h1>Employee Management System</h1>
<h2>
<% out.print(request.getParameter("message")); %><br></h2>
<a href = "viewEmployee"><button> Go To view Page </button></a>
</body>  
</html> 