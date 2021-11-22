<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>  
    <body>
        <form action = "${action}" method="post">
            <div align="center">
            <c:if test='${action.equals("allocateProject")}'>
                <h1>Available Projects to Assign</h1>
	        </c:if>
	        
	        <c:if test='${action.equals("unAllocateProject")}'>
                <h1>Available Projects to unAssign</h1>
	        </c:if>

            <table border="5">
                <c:if test='${not empty availableProjects}'>
                <tr>
                    <th>Project Id</th>
                    <th>Project Name</th>
                    <th>Select Projects</th>
                </tr>
                <c:forEach var="availableProject" items="${availableProjects}" > 
                    <tr>
                        <td>${availableProject.id}</td>
                        <td>${availableProject.name}</td>
                        <td><input type="checkbox" name="selectedProjects" value="${availableProject.id}"><td>
                    </tr>
                 </c:forEach>
            </c:if>
            <c:if test='${empty availableProjects}'>
                <c:if test='${action.equals("allocateProject")}'>
                    No Projects to Assign!!!
	            </c:if>
	            <c:if test='${action.equals("unAllocateProject")}'>
                    No Projects to unAssign!!!
	            </c:if>
	        </c:if>
            </table>
            <c:if test='${not empty availableProjects}'>
                <c:if test='${action.equals("allocateProject")}'>
                    <input type="submit" value="Allocate">
	            </c:if>
	             <c:if test='${action.equals("unAllocateProject")}'>
                     <input type="submit" value="unAllocate">
	            </c:if>
	        </c:if>
            </div>
        </form>
        <center>
        <a href = "employee.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        </center>
    </body>
</html>
                    