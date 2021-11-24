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
        <h2>Projects</h2>
        <c:if test="${not empty projects}">
        <table border="1">
            <tr>
                <th>Project ID</th>
                <th>Project Name</th>
                <th>description</th>
                <th>Manager</th>
                <th>Project Status</th>
                <th>Employees</th>
                <th>Assign UnAssign Employee</th>
                <th>Select</th>
            </tr>
            
            <c:forEach var="project" items="${projects}" > 
                <tr>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.description}</td>
                    <td>${project.manager}</td>
                    <td>${project.status}</td>
                <td>
                <c:forEach var="employee" items = "${project.employeesDto}">
                    Employee id :
                    ${employee.id} <br>
                    Employee Name :
                    ${employee.name} <br>
                </c:forEach>
                </td>
                <td>
                    <a href="assignEmployee?id=<c:out value='${project.id}' />">Assign</a>
                    <a href="unAssignEmployee?id=<c:out value='${project.id}' />">UnAssign</a>
                </td>
                <td>
                    <a href="projectUpdateForm?id=<c:out value='${project.id}' />">Edit</a>
                    <a href="deleteProject?id=<c:out value='${project.id}' />">Delete</a>
                </td> 
            </tr>
            </c:forEach>
        </table>
       </c:if> 
        <c:if test="${empty projects}">
            <h2> No Projects Found!!!</h2><br>
        </c:if>
        <a href = "project.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        <c:if test="${not empty projects}">
            <div>
                <a href = "deleteAllProject"><button> Delete All</button></a>
            </div>
        </c:if>
    </div>
</body>
</html>