<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
<head>
    <title>
        Employee Management System
    </title>
    <link rel="stylesheet" href="EmployeeStylePage.css">
</head>  
<body>
<center><h1>Employee Management System</h1></center>
<form action = "${action}" method="post">
    <c:if test="${cloneEmployeeDto == null}">
        <h1>Create Employee</h1>
	</c:if>
	<c:if test="${cloneEmployeeDto != null}">
        <h1>Update Employee</h1>
        
	</c:if>
    <fieldset>
        <legend> Employee Form</legend>
        <label for="name">Employee Name</label><br>
        <input type="text" id="name" name="name" placeholder = "Enter Your Name" 
               pattern="^([A-Za-z]{3,20}+ ){1,2}+[A-Za-z]+$" value = "${cloneEmployeeDto.name}" required><br>
        <label for="salary">Salary</label><br>
        <input type="text" id="salary" name="salary" pattern="^[0-9]*[.]?[0-9]{0,2}?$"
               value = "${cloneEmployeeDto.salary}" required><br>
        <label for="email">Email</label><br>
        <input type="email" id="email" name="email" value = "${cloneEmployeeDto.email}" required><br>
        <label for="phoneNumber">Phone Number</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber" value = "${cloneEmployeeDto.phoneNumber}" pattern="^(0|[6-9][0-9]{9})$"
               required><br>
        <label for="Date OF Birth">Date Of Birth</label><br>
        <input type="date" id="Date Of Birth" name="DOB" value = "${cloneEmployeeDto.DOB}" required><br>
        <c:if test="${cloneEmployeeDto != null}">
            Do You Want To Update Address?<br>
            <label>
                <input type="radio" name="toUpdate" value="Yes" required>
                    Yes
            </label>
            <label>
                <input type="radio" name="toUpdate" value="No">
                    No
            </label><br>
	    </c:if>
	    <c:if test="${cloneEmployeeDto != null}">
            <button type="submit"> Update </button>
	    </c:if>
	    <c:if test="${cloneEmployeeDto == null}">
            <button type="submit"> Create </button>
	    </c:if>
	    <c:if test="${cloneEmployeeDto == null}">
            <button type="reset"> Reset </button> 
	    </c:if>
    </fieldset>
</form>
<c:if test="${cloneEmployeeDto != null}">
    <a href = "viewEmployee"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</c:if>

<c:if test="${cloneEmployeeDto == null}">
    <a href = "employee.jsp"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</c:if>
</body>
</html>