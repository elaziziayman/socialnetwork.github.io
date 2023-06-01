<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FriendVerse</title>
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
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<br>
	<br>
	<br>
<h4>Popular Publications in FriendVerse</h4>
			<div class="container" id="container">
				<%@include file="popular.jsp"%>
				<br> <br>
			</div>
		
	<br>
	<br>
	<br>
	<h4>Your friends Publications in FriendVerse</h4>
	<c:forEach items="${publications}" var="p">

			<div class="container" id="container">
				<%@include file="acceuil.jsp"%>
				<br> <br>
			</div>
		</c:forEach>
		
		<script>
		function likePublication(publicationId) {
			var toggleBtn = document.getElementById("toggleBtn-"
					+ publicationId);
			var currentState = toggleBtn.innerHTML;
			var xhr = new XMLHttpRequest();

			if (currentState === "Like") {
				toggleBtn.innerHTML = "Unlike";
				xhr.onreadystatechange = function() {
					if (xhr.readyState === XMLHttpRequest.DONE) {
						if (xhr.status === 200) {
							// Update the likes count
							var likes = parseInt(document
									.getElementById("likes-" + publicationId).innerHTML);
							document.getElementById("likes-" + publicationId).innerHTML = likes + 1;
						} else {
							alert("Error liking publication.");
						}
					}
				};
				xhr.open("POST", "LikeServlet?pid=" + publicationId, true);
				xhr.send();

			} else {
				toggleBtn.innerHTML = "Like";
				var xhr2 = new XMLHttpRequest();
				xhr2.onreadystatechange = function() {
					if (xhr2.readyState === XMLHttpRequest.DONE) {
						if (xhr2.status === 200) {
							// Update the likes count
							var likes = parseInt(document
									.getElementById("likes-" + publicationId).innerHTML);
							document.getElementById("likes-" + publicationId).innerHTML = likes - 1;
						} else {
							alert("Error unliking publication.");
						}
					}
				};
				xhr2.open("POST", "UnlikeServlet?pid=" + publicationId, true);
				xhr2.send();

			}

		}
		function followUser(userId) {
			var followBtn = document.getElementById("followBtn");
			var currentState = followBtn.innerHTML;
			var xhr3 = new XMLHttpRequest();
			if (currentState === "Follow") {
				followBtn.innerHTML = "Unfollow";
				xhr3.onreadystatechange = function() {
					if (xhr3.readyState === XMLHttpRequest.DONE) {
						if (xhr3.status === 200) {
							var follows = parseInt(document.getElementById("follower-"+userId).innerHTML);
							document.getElementById("follower-"+userId).innerHTML = follows + 1;
						} else {
							alert("Error following user.");
						}
					}
				};
				xhr3.open("POST", "followuser.php?uid=" + userId, true);
				xhr3.send();

			} else {
				followBtn.innerHTML = "Follow";
				var xhr4 = new XMLHttpRequest();
				xhr4.onreadystatechange = function() {
					if (xhr4.readyState === XMLHttpRequest.DONE) {
						if (xhr4.status === 200) {
							// Update the likes count
							var follows = parseInt(document.getElementById("follower-" + userId).innerHTML);
							document.getElementById("follower-" + userId).innerHTML = follows - 1;
						} else {
							alert("Error unfollowing user.");
						}
					}
				};
				xhr4.open("POST", "unfollowuser.php?uid=" + userId, true);
				xhr4.send();

			}
		}
		function FocusOnCommentBox(publicationId){
		    document.getElementById("mycomment-"+publicationId).focus();
		}
		
		
	</script>
	


	


</body>
</html>