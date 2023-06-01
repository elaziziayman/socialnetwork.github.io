<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRIENDVERSE - Log in or Sign up</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<link rel="icon" href="img/ico-nobg.png" type="image/x-icon">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
</head>
<body>

	<div class="container col-md-4 col-md-offset-4">
		<img src="img/FRIENDVERSE.png" class="img-fluid">
		<div class="card border-info mb-3">
			<div class="card-body">
				<form action="login.php" method="post">
				${errorMessage}
				
				
					<div class="mb-3">
						<input type="text" class="form-control" name="username"
							placeholder="Username" required> 
					</div>
					<div class="mb-3">
						<input type="password" name="password" class="form-control"
							placeholder="Password" required> <span></span>
					</div>
					<div class="d-grid gap-2" >
					
					<button type="submit" class="btn btn-primary btn-block">Log
						in</button>
						</div>

				</form>
			</div>
		</div>
		<div class="col text-center">
		<form action="createaccount.php" method="get">
        		<button type="submit" class="btn btn-success" >Create New Account</button>
        
      </form>
		</div>
	</div>
	
	
	
</body>
</html>