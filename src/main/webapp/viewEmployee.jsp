<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>  
<html>  
<head>
    <title>
        Employee Management System
    </title>
    <link rel="stylesheet" href="EmployeeStylePage.css">
</head>
<body>
    <div align="center">
        <h2>Employees</h2>
        <c:if test="${not empty employees}">
        <table border="1">
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Date Of Birth</th>
                <th>Address</th>
                <th>Projects</th>
                <th>Assign or UnAssign Projects</th>
                <th>Edit or Delete</th>
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
                <table >
                  <tr>
                    <th>Project Id</th>
                    <th>Project Name</th>
                 </tr>
                <c:forEach var="project" items = "${employee.projectsDto}">
                 <tr>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                 </tr>
                </c:forEach>
                </table>
                </td>
                <td>
                    <a href="assign?id=<c:out value='${employee.id}' />"><button>Assign</button></a>
                    <a href="unAssign?id=<c:out value='${employee.id}' />"><button>UnAssign</button></a>
                </td>
                <td>
                    <a href="employeeUpdateForm?id=<c:out value='${employee.id}' />"><button>Edit</button></a>
                    <a href="delete?id=<c:out value='${employee.id}' />"><button>Delete</button></a>
                </td>
            </tr>
            </c:forEach>
        </table>
       </c:if>
         <c:if test="${empty employees}">
             <h2>No Employees Found!!!</h2><br>
         </c:if>
        <a href = "employee.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        <c:if test="${not empty employees}">
           <div>
                <a href = "deleteAll"><button class="btn-grad"> Delete All</button></a>
            </div>
        </c:if>
    </div>   
</body>
</html>