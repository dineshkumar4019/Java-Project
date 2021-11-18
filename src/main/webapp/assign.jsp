<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>  
<body>
<form action = "${action}" method="post">
    <div align="center">
        <h1>Available Projects</h1>
        <table border="1">
            <tr>
                <th>Project Id</th>
                <th>Project Name</th>
                <th>Select Projects</th>
            </tr>
        <c:forEach var="availableProject" items="${availableProjects}" > 
            <tr>
                <td>${availableProject.id}</td>
                <td>${availableProject.name}</td>
                <td>
                    <input type="checkbox" name="selectedProjects" value="${availableProject.id}">
                <td>
            </tr>
         </c:forEach>
     </table>
     <a href = "allocateProject?id=<c:out value='${id}' />"> Allocate
</div>
</body>
</html>
                    