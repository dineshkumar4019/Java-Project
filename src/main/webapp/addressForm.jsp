<!DOCTYPE html>  
<html>  
<body>
    <h1> Create Address </h1>
<form action = "${action}" method="post">
    <fieldset>
        <label for="addressLine">Address Line</label><br>
        <input type="text" id="addressLine" name="addressLine" placeholder = "Eg(No-09/10 xxxxtown)" required><br>
        <label for="city">City</label><br>
        <input type="text" id="city" name="city" required><br>
        <label for="pincode">Pin Code</label><br>
        <input type="number" id="pincode" name="pincode" maxlength="6" required><br>
        <label for="state">State</label><br>
        <input type="text" id="state" name="state" required><br>
        <label for="country">Country</label><br>
        <input type="text" id="country" name="country" required><br>
        <input type="submit" value="create">
        <input type="reset">
    </fieldset>
</form>
</body>
</html>
    