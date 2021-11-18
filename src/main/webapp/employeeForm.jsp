<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>   
<body>
<center><h1>Employee Management System</h1></center>
<form action = "${action}" method="post">
    <c:if test="${cloneEmployeeDto == null}">
        Create Employee
	</c:if>
	<c:if test="${cloneEmployeeDto != null}">
        <h1>Update Employee</h1>
        
	</c:if>
    <fieldset>
        <label for="name">Employee Name</label><br>
        <input type="text" id="name" name="name" placeholder = "Enter Your Name" 
               pattern="^([A-Za-z]{3,20}+ ){1,2}+[A-Za-z]+$" value = "${cloneEmployeeDto.name}" required><br>
        <label for="salary">Salary</label><br>
        <input type="number" id="salary" name="salary" pattern="^[0-9]*[.]?[0-9]{0,2}?$"
               value = "${cloneEmployeeDto.salary}" required><br>
        <label for="email">Email</label><br>
        <input type="email" id="email" name="email" value = "${cloneEmployeeDto.email}" required><br>
        <label for="phoneNumber">Phone Number</label><br>
        <input type="number" id="phoneNumber" name="phoneNumber" value = "${cloneEmployeeDto.phoneNumber}" pattern="^(0|[6-9][0-9]{9})$"
               required><br>
        <label for="Date OF Birth">Date Of Birth</label><br>
        <input type="date" id="Date Of Birth" name="DOB" value = "${cloneEmployeeDto.DOB}" required><br>
        <c:if test="${cloneEmployeeDto != null}">
            Do You Want To Add Address?<br>
            <label>
                <input type="radio" name="toUpdate" value="Yes" required>
                    Yes
            </label>
            <label>
                <input type="radio" name="toUpdate" value="No">
                    No
            </label><br>
	    </c:if> 
        <input type="submit" value="Submit">
        <input type="reset"> 
    </fieldset>
</form>
<a href = "employee.jsp"><button> Back </button></a>
<a href = "index.jsp"><button> Home </button></a>
</body>
</html>