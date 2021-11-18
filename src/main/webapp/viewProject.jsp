<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<body>
    <center><h1>Project Management</h1></center>
    <div align="center">
        <h2>Projects</h2>
        <table border="1">
            <tr>
                <th>Project ID</th>
                <th>Project Name</th>
                <th>description</th>
                <th>Manager</th>
                <th>Project Status</th>
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
                    <a href="projectUpdateForm?id=<c:out value='${project.id}' />">Edit</a>
                    <a href="deleteProject?id=<c:out value='${project.id}' />">Delete</a>
                </td>                    
            </tr>
            </c:forEach>
        </table>
        <a href = "project.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        <div>
            <a href = "deleteAllProject"><button> Delete All</button></a>
        </div>
    </div>
</body>
</html>