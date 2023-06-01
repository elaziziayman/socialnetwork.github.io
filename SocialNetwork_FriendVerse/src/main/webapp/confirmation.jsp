<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container col-md-8 col-md-offset-2">
	<div class="panel panel-primary">
		<div class="panel-heading"> Profil Information</div>
		<div class="panel-body">
			<form action="confirmer.php" method="post">
			<div class="form-group">
			<label class="control-label">ID : ${user.id}</label>
			</div>
			<div class="form-group">
			<label class="control-label">First Name : ${user.firstname} </label>
			</div>
			<div class="form-group">
			<label class="control-label">Last Name : ${user.lastname} </label>
			</div>
			<div class="form-group">
			<label class="control-label">Age : ${user.age} </label>
			</div>
			<div class="form-group">
			<label class="control-label">Gender : ${user.gender}</label>
			</div>
			<div class="form-group">
			<label class="control-label">Username : ${user.username}</label>
			</div>
			
			<button type="submit" class="btn btn-success" >Go to welcome page</button>
			</form>
		</div>
	</div>
	</div>
</body>
</html>