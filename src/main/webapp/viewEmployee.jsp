<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>  
<html>  
<head>
    <title>Employees</title>
</head>
<body>
    <div align="center">
        <h2>Employees</h2>
        <table border="1">
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Date Of Birth</th>
                <th>Address</th>
                <th>Select</th>
            </tr>
            
            <c:forEach var="employee" items="${employees}" > 
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.email}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.DOB}</td>
                <c:forEach var="address" items="${employee.addressDto}" >
                    <td>
                    ${address.addressLine},<br>
                    ${address.city}-
                    ${address.pincode},
                    ${address.state},
                    ${address.country}
                    </td>
                </c:forEach>
                <td>
                    <a href="employeeUpdateForm?id=<c:out value='${employee.id}' />">Edit</a>
                    <a href="delete?id=<c:out value='${employee.id}' />">Delete</a>
                </td>                    
            </tr>
            </c:forEach>
        </table>
        <a href = "employee.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        <div>
            <a href = "deleteAll"><button> Delete All</button></a>
        </div>
    </div>   
</body>
</html>