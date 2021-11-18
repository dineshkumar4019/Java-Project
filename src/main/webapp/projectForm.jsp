<!DOCTYPE html>  
<html>  
<body>
    <h1> Create Project </h1>
<form action = "/create" method="post">
    <fieldset>
        <label for="name">Project Name</label><br>
        <input type="text" id="name" name="name" placeholder = "Enter the project name" 
               required><br>
        <label for="description">Project Description</label><br>
        <input type="text" id="description" name="description" required><br>
        <label for="manager">Manager Name</label><br>
        <input type="text" id="manager" name="manager" required><br>
        <label for="status">Project Status</label><br>
        <input type="radio" id="status" name="status">
        <label for="DEVELOPMENT">DEVELOPMENT</label>
        <input type="radio" id="TESTING" name="status">
        <label for="TESTING">TESTING</label>
        <input type="radio" id="LIVE" name="status">
        <label for="LIVE">LIVE</label><br>
        <input type="submit" value="create">
        <input type="reset">
    </fieldset>
    </form>
    <a href = "project.jsp"><button> Back </button></a>
    <a href = "index.jsp"><button> Home </button></a>
</body>
</html>
    