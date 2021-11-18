<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<body>
<center><h1>Project Management</h1></center>
<form action = "${action}" method="post">
    <c:if test="${cloneProjecteDto == null}">
        <h1>Create project</h1>
	</c:if>
	<c:if test="${cloneProjectDto != null}">
        <h1>Update project</h1>   
	</c:if>
    <fieldset>
        <label for="name">Project Name</label><br>
        <input type="text" id="name" name="name" placeholder = "Enter the project name" 
               value = "${cloneProjectDto.name}" required><br>
        <label for="description">Project Description</label><br>
        <input type="text" id="description" name="description" value = "${cloneProjectDto.description}" required><br>
        <label for="manager">Manager Name</label><br>
        <input type="text" id="manager" name="manager" value = "${cloneProjectDto.manager}" required><br>
        <label for="status">Project Status</label><br>
        <label>
            <input type="radio" name="status" value="DEVELOPMENT" required>
               DEVELOPMENT
        </label>
        <label>
            <input type="radio" name="status" value="TESTING">
                TESTING
        </label>
        <label>
            <input type="radio" name="status" value="LIVE">
                LIVE
        </label><br>
        <c:if test="${cloneProjectDto != null}">
            <input type="submit" value="update">   
	    </c:if>
	    <c:if test="${cloneProjectDto == null}">
            <input type="submit" value="create">   
	    </c:if>
        <input type="reset">
    </fieldset>
    </form>
    <a href = "project.jsp"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</body>
</html>
    