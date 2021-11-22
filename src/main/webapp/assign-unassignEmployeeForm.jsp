<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>  
    <body>
        <form action = "${action}" method="post">
            <div align="center">
            <c:if test='${action.equals("allocateEmployee")}'>
                <h1>Available Employees to Assign</h1>
	        </c:if>
	        
	        <c:if test='${action.equals("unAllocateEmployee")}'>
                <h1>Available Projects to unAssign</h1>
	        </c:if>

            <table border="5">
                <c:if test='${not empty availableEmployees}'>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee Name</th>
                    <th>Select Employees</th>
                </tr>
                <c:forEach var="availableEmployee" items="${availableEmployees}" > 
                    <tr>
                        <td>${availableEmployee.id}</td>
                        <td>${availableEmployee.name}</td>
                        <td><input type="checkbox" name="selectedEmployees" value="${availableEmployee.id}"><td>
                    </tr>
                 </c:forEach>
            </c:if>
            <c:if test='${empty availableEmployees}'>
                <c:if test='${action.equals("allocateEmployee")}'>
                    No Employees to Assign!!!
	            </c:if>
	            <c:if test='${action.equals("unAllocateEmployee")}'>
                    No Employees to unAssign!!!
	            </c:if>
	        </c:if>
            </table>
            <c:if test='${not empty availableEmployees}'>
                <c:if test='${action.equals("allocateEmployee")}'>
                    <input type="submit" value="Allocate">
	            </c:if>
	             <c:if test='${action.equals("unAllocateEmployee")}'>
                     <input type="submit" value="unAllocate">
	            </c:if>
	        </c:if>
            </div>
        </form>
        <center>
        <a href = "project.jsp"><button> Back </button></a>
        <a href = "index.jsp"><button> Home </button></a>
        </center>
    </body>
</html>
                    