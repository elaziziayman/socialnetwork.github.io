<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Account</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="icon" href="img/ico-nobg.png" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container col-md-4 col-md-offset-4">
		<div class="card border-info mb-3">
			<div class="card-header text-center">Modify Your Account</div>
			<div class="card-body">
				<form action="modifyaccount.php" method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label class="control-label">First Name</label> <input type="text"
							name="firstname" class="form-control" required value="${user.firstname}">
						<span></span>
					</div>
					<div class="mb-3">
						<label class="control-label">Last Name</label> <input type="text"
							name="lastname" class="form-control"  required value="${user.lastname}">
						<span></span>
					</div>
					<div class="mb-3">
						<label class="control-label">Date of Birth</label> <input
							type="date" name="birthdate" required class="form-control"> <span></span>
					</div>
					<div class="mb-3">
						<label class="control-label">Gender</label>
						<div class="form-check"  >
							<input class="form-check-input" type="radio" name="gender"
								id="flexRadioDefault1" value="male" required > <label class="form-check-label" 
								for="flexRadioDefault1"> Male </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender"
								id="flexRadioDefault2" value="female" required > <label
								class="form-check-label" for="flexRadioDefault2"> Female
							</label>
						</div>
						<span></span>
					</div>
					<div class="mb-3">
					<label class="form-label" for="customFile">Add your profile photo</label>
<input type="file" class="form-control"  accept="image/jpeg" id="customFile" name="profilephoto" />
</div>
					<div class="mb-3">
						<label class="control-label">Username</label> <input type="text"
							name="username" class="form-control" required value="${user.username}">
						<span></span>
					</div>
					<div class="mb-3">
						<label class="control-label">Password</label> <input type="password"
							name="password" class="form-control" required> <span></span>
					</div>
					<div class="col text-center">
						<button type="submit" class="btn btn-danger">Confirm</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>