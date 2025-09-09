<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html> 
<head>
    <title>
        Employee Management System
    </title>
    <link rel="stylesheet" href="EmployeeStylePage.css">
</head>  
<body>
<center><h1>Employee Management System</h1></center>
<form:form action = "${action}" method="post" modelAttribute = "employee" >
    <c:if test="${action.equals('create')}">
        <h1>Create Employee</h1>
	</c:if>
	<c:if test="${action.equals('update')}">
        <h1>Update Employee</h1>
        
	</c:if>
    <fieldset>
        <legend> Employee Form</legend>
        <label for="name">Employee Name</label><br>
        <form:input type="text" path="name" pattern="^([A-Za-z]{3,20}+ ){1,2}+[A-Za-z]+$" required = "required"/><br>
        <label for="salary">Salary</label><br>
        <form:input type="text" id="salary" path="salary" pattern="^[0-9]*[.]?[0-9]{0,2}?$" required = "required"/><br>
        <label for="email">Email</label><br>
        <form:input type="email" id="email" path="email" required = "required"/><br>
        <label for="phoneNumber">Phone Number</label><br>
        <form:input type="text" id="phoneNumber" path="phoneNumber" pattern="^(0|[6-9][0-9]{9})$" required = "required"/><br>
        <label for="Date OF Birth">Date Of Birth</label><br>
        <form:input type="date" id="DOB" path="DOB" required = "required"/><br>
        <c:if test="${action.equals('update')}">
            Do You Want To Update Address?<br>
            <label>
               <input type="radio" name="toUpdate" value="Yes"/>
                    Yes
            </label>
            <label>
                <input type="radio" name="toUpdate" value="No"/>
                    No
            </label><br>
	    </c:if>
	    <c:if test="${action.equals('update')}">
            <button type="submit"> Update </button>
	    </c:if>
	    <c:if test="${action.equals('create')}">
            <button type="submit"> Create </button>
	    </c:if>
	    <c:if test="${action.equals('create')}">
            <button type="reset"> Reset </button> 
	    </c:if>
    </fieldset>
</form:form>
<c:if test="${action.equals('update')}">
    <a href = "viewEmployee"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</c:if>

<c:if test="${action.equals('create')}">
    <a href = "employee.jsp"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</c:if>
</body>
</html>