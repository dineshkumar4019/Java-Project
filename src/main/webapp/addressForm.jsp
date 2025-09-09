<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>  
<html>
<head>
    <title>
        Employee Management System
    </title>
    <link rel="stylesheet" href="EmployeeStylePage.css">
</head>  
<body>
    <h1> Create Address </h1>
<form:form action = "${action}" method="post" modelAttribute = "address">
    <fieldset>
        <label for="addressLine">Address Line</label><br>
        <form:input type="text" path="addressLine" placeholder = "Eg(No-09/10 xxxxtown)" /><br>
        <label for="city">City</label><br>
        <form:input type="text" id="city" path="city" /><br>
        <label for="pincode">Pin Code</label><br>
        <form:input type="number" id="pincode" path="pincode" maxlength="6"/><br>
        <label for="state">State</label><br>
        <form:input type="text" id="state" path="state" /><br>
        <label for="country">Country</label><br>
        <form:input type="text" id="country" path="country"/><br>
        <button type="submit">Submit</button>
    </fieldset>
</form:form> 
</body>
</html>
    