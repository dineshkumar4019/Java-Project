<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<head>  
<title>Employee Management System</title>  
</head>  
<body>  
<h1>Employee Management System</h1>  
   <c:if test="${successMessage != null}">
       "${successMessage}"
   </c:if><br>
   <a href="employeeCreateForm">Create Employee</a><br> 
   <a href="viewEmployee">Display Employee</a><br>
   <a href = "index.jsp"><button> Back </button></a>
</body>  
</html>  