<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="metier.entities.Publication"%> 
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 



	<% 
	List<Publication> pbs = (ArrayList) request.getAttribute("publicationspopulaires");
	if (pbs.size() < 3) { %>
  <p>No popular publications at the moment</p>
<% } else { %>
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active " >
    <div class="row justify-content-center">
      <div class="col-md-6">
                
                <div class="card" id="publication-${publicationspopulaires[0].id}">
                    <div class="card-body" >
                        <h5 class="card-title"><a href="profile.php?uid=${publicationspopulaires[0].user.id}"><img src="PhotoServlet?id=${publicationspopulaires[0].user.id}" style="width:40px; height:40px;" class="rounded-circle border border-dark"></a>
                         ${publicationspopulaires[0].user.username} added a new post</h5>
                        <p class="card-text">${publicationspopulaires[0].description}</p>
                        <p class="card-text"><small class="text-muted">${publicationspopulaires[0].afficherdate()}</small></p>
                    </div>
                    <img src="PublicationServlet?pid=${publicationspopulaires[0].id}" class="card-img-bottom" >
                    
                <div class="card-footer text-center">
                    <div class="container">
                        <div class="row">
                          <div class="col">
                            <span class="fw-bold" ><a href="likes.php?pid=${publicationspopulaires[0].id}" class="link-primary" style="transform: translate3d(0px, 0px, 0px); transition-property: transform; transition-duration: 0.3s;" id="likes-${publicationspopulaires[0].id}">${publicationspopulaires[0].numberlikes}</a></span>
                            <button type="button" class="btn btn-primary w-auto" onclick="likePublication(${publicationspopulaires[0].id})" id="toggleBtn-${publicationspopulaires[0].id}" >${publicationspopulaires[0].likebutton(user.id)}</button>
                          </div>
                          <div class="col">
                            <span class="fw-bold text-secondary" id="comments">${publicationspopulaires[0].numbercomments}</span>
                            <button type="button" class="btn btn-secondary  w-auto" onclick="FocusOnCommentBox(${publicationspopulaires[0].id})" > Comment</button>

                          </div>
                        </div>
                        <br> 
                        <c:forEach items="${metiercmts.getComments(publicationspopulaires[0].id)}" var="c">
                        <div class="input-group flex-nowrap" id="${publicationspopulaires[0].id}">
                            <span class="input-group-text" id="addon-wrapping"><a href="profile.php?uid=${c.user_id}"><img src="PhotoServlet?id=${c.user_id}" style="height: 50px; width: 50px;" class="rounded-circle"></a></span>
                            <input type="text" class="form-control" value="${c.comment}" disabled aria-describedby="addon-wrapping">
                          
                          </div>
                          </c:forEach>
               <form action="addcommentonpopular.php?idP=${publicationspopulaires[0].id}&uid=${user.id}" method="post"> 
                        <div class="input-group flex-nowrap">
                       
                  
                            <span class="input-group-text" id="addon-wrapping"><img src="PhotoServlet?id=${user.id}" style="height: 50px; width: 50px;" class="rounded-circle"></span>
                            <input type="text" id="mycomment-${publicationspopulaires[0].id}" name="comment" required class="form-control" placeholder="Add your comment here" aria-label="Add your comment here" aria-describedby="addon-wrapping">
                           
                            
                            <button  id="commentbutton" type="submit" onclick="ScrollIntoCommentBox(${publicationspopulaires[0].id})" class="btn btn-outline-secondary "> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                            
  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576 6.636 10.07Zm6.787-8.201L1.591 6.602l4.339 2.76 7.494-7.493Z"/>
</svg></button>

                          </div>
                          </form>
                          
                        
                      </div>
                   
                </div>
            </div>
        </div>
        </div>
    </div>
    <div class="carousel-item">
    <div class="row justify-content-center">
      <div class="col-md-6">
                
                <div class="card" id="publication-${publicationspopulaires[1].id}">
                    <div class="card-body" >
                        <h5 class="card-title"><a href="profile.php?uid=${publicationspopulaires[1].user.id}"><img src="PhotoServlet?id=${publicationspopulaires[1].user.id}" style="width:40px; height:40px;" class="rounded-circle border border-dark"></a>
                         ${publicationspopulaires[1].user.username} added a new post</h5>
                        <p class="card-text">${publicationspopulaires[1].description}</p>
                        <p class="card-text"><small class="text-muted">${publicationspopulaires[1].afficherdate()}</small></p>
                    </div>
                    <img src="PublicationServlet?pid=${publicationspopulaires[1].id}" class="card-img-bottom" >
                    
                <div class="card-footer text-center">
                    <div class="container">
                        <div class="row">
                          <div class="col">
                            <span class="fw-bold" ><a href="likes.php?pid=${publicationspopulaires[1].id}" class="link-primary" style="transform: translate3d(0px, 0px, 0px); transition-property: transform; transition-duration: 0.3s;" id="likes-${publicationspopulaires[1].id}">${publicationspopulaires[1].numberlikes}</a></span>
                            <button type="button" class="btn btn-primary w-auto" onclick="likePublication(${publicationspopulaires[1].id})" id="toggleBtn-${publicationspopulaires[1].id}" >${publicationspopulaires[1].likebutton(user.id)}</button>
                          </div>
                          <div class="col">
                            <span class="fw-bold text-secondary" id="comments">${publicationspopulaires[1].numbercomments}</span>
                            <button type="button" class="btn btn-secondary  w-auto" onclick="FocusOnCommentBox(${publicationspopulaires[1].id})" > Comment</button>

                          </div>
                        </div>
                        <br> 
                        <c:forEach items="${metiercmts.getComments(publicationspopulaires[1].id)}" var="c">
                        <div class="input-group flex-nowrap" id="${publicationspopulaires[1].id}">
                            <span class="input-group-text" id="addon-wrapping"><a href="profile.php?uid=${c.user_id}"><img src="PhotoServlet?id=${c.user_id}" style="height: 50px; width: 50px;" class="rounded-circle"></a></span>
                            <input type="text" class="form-control" value="${c.comment}" disabled aria-describedby="addon-wrapping">
                          
                          </div>
                          </c:forEach>
               <form action="addcommentonpopular.php?idP=${publicationspopulaires[1].id}&uid=${user.id}" method="post"> 
                        <div class="input-group flex-nowrap">
                       
                  
                            <span class="input-group-text" id="addon-wrapping"><img src="PhotoServlet?id=${user.id}" style="height: 50px; width: 50px;" class="rounded-circle"></span>
                            <input type="text" id="mycomment-${publicationspopulaires[1].id}" name="comment" required class="form-control" placeholder="Add your comment here" aria-label="Add your comment here" aria-describedby="addon-wrapping">
                           
                            
                            <button  id="commentbutton" type="submit" onclick="ScrollIntoCommentBox(${publicationspopulaires[1].id})" class="btn btn-outline-secondary "> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                            
  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576 6.636 10.07Zm6.787-8.201L1.591 6.602l4.339 2.76 7.494-7.493Z"/>
</svg></button>

                          </div>
                          </form>
                          
                        
                      </div>
                   
                </div>
            </div>
        </div>
        </div>
    </div>
    <div class="carousel-item" >
    <div class="row justify-content-center">
      <div class="col-md-6" id="publication-${publicationspopulaires[2].id}">
                
                <div class="card"  >
                    <div class="card-body" >
                        <h5 class="card-title"><a href="profile.php?uid=${publicationspopulaires[2].user.id}"><img src="PhotoServlet?id=${publicationspopulaires[2].user.id}" style="width:40px; height:40px;" class="rounded-circle border border-dark"></a>
                         ${publicationspopulaires[2].user.username} added a new post</h5>
                        <p class="card-text">${publicationspopulaires[2].description}</p>
                        <p class="card-text"><small class="text-muted">${publicationspopulaires[2].afficherdate()}</small></p>
                    </div>
                    <img src="PublicationServlet?pid=${publicationspopulaires[2].id}" class="card-img-bottom" >
                    
                <div class="card-footer text-center">
                    <div class="container">
                        <div class="row">
                          <div class="col">
                            <span class="fw-bold" ><a href="likes.php?pid=${publicationspopulaires[2].id}" class="link-primary" style="transform: translate3d(0px, 0px, 0px); transition-property: transform; transition-duration: 0.3s;" id="likes-${publicationspopulaires[2].id}">${publicationspopulaires[2].numberlikes}</a></span>
                            <button type="button" class="btn btn-primary w-auto" onclick="likePublication(${publicationspopulaires[2].id})" id="toggleBtn-${publicationspopulaires[2].id}" >${publicationspopulaires[2].likebutton(user.id)}</button>
                          </div>
                          <div class="col">
                            <span class="fw-bold text-secondary" id="comments">${publicationspopulaires[2].numbercomments}</span>
                            <button type="button" class="btn btn-secondary  w-auto" onclick="FocusOnCommentBox(${publicationspopulaires[2].id})" > Comment</button>

                          </div>
                        </div>
                        <br> 
                        <c:forEach items="${metiercmts.getComments(publicationspopulaires[2].id)}" var="c">
                        <div class="input-group flex-nowrap" id="${publicationspopulaires[2].id}">
                            <span class="input-group-text" id="addon-wrapping"><a href="profile.php?uid=${c.user_id}"><img src="PhotoServlet?id=${c.user_id}" style="height: 50px; width: 50px;" class="rounded-circle"></a></span>
                            <input type="text" class="form-control" value="${c.comment}" disabled aria-describedby="addon-wrapping">
                          
                          </div>
                          </c:forEach>
               <form action="addcommentonpopular.php?idP=${publicationspopulaires[2].id}&uid=${user.id}"  method="post"> 
                        <div class="input-group flex-nowrap">
                       
                  
                            <span class="input-group-text" id="addon-wrapping"><img src="PhotoServlet?id=${user.id}" style="height: 50px; width: 50px;" class="rounded-circle"></span>
                            <input type="text" id="mycomment-${publicationspopulaires[2].id}" name="comment" required class="form-control" placeholder="Add your comment here" aria-label="Add your comment here" aria-describedby="addon-wrapping">
                           
                            
                            <button  id="commentbutton" onclick="ScrollIntoCommentBox(${publicationspopulaires[2].id})" type="submit" class="btn btn-outline-secondary "> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                            
  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576 6.636 10.07Zm6.787-8.201L1.591 6.602l4.339 2.76 7.494-7.493Z"/>
</svg></button>

                          </div>
                          </form>
                          
                        
                      </div>
                   
                </div>
            </div>
        </div>
                </div>
        
    </div>
  </div>
  <a class="carousel-control-prev" style="color: black;" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" style="color: black;" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>

<% } %>
	


<script>

  document.addEventListener('DOMContentLoaded', function() {
    var carousel = document.getElementById('carouselExampleControls');
    var carouselInstance = new bootstrap.Carousel(carousel);

    var prevButton = document.querySelector('.carousel-control-prev');
    var nextButton = document.querySelector('.carousel-control-next');

    prevButton.addEventListener('click', function() {
      carouselInstance.prev();
    });

    nextButton.addEventListener('click', function() {
      carouselInstance.next();
    });
    
  });
  function ScrollIntoCommentBox(publicationId) {
	  var publicationElement = document.getElementById('publication-' + publicationId);
	  
	    publicationElement.scrollIntoView({ behavior: 'smooth' });
	}
  
</script>




				

