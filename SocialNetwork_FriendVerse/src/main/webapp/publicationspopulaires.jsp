<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="row justify-content-center">
            <div class="col-md-6">
                
                <div class="card">
                    <div class="card-body" >
                        <h5 class="card-title"><a href="profile.php?uid=${pubpop.user.id}"><img src="PhotoServlet?id=${pubpop.user.id}" style="width:40px; height:40px;" class="rounded-circle border border-dark"></a>
                         ${pubpop.user.username} added a new post</h5>
                        <p class="card-text">${pubpop.description}</p>
                        <p class="card-text"><small class="text-muted">${pubpop.afficherdate()}</small></p>
                    </div>
                    <img src="PublicationServlet?pid=${pubpop.id}" class="card-img-bottom" >
                    
                <div class="card-footer text-center">
                    <div class="container">
                        <div class="row">
                          <div class="col">
                            <span class="fw-bold" ><a href="likes.php?pid=${pubpop.id}" class="link-primary" style="transform: translate3d(0px, 0px, 0px); transition-property: transform; transition-duration: 0.3s;" id="likes-${pubpop.id}">${pubpop.numberlikes}</a></span>
                            <button type="button" class="btn btn-primary w-auto" onclick="likePublication(${pubpop.id})" id="toggleBtn-${pubpop.id}" >${pubpop.likebutton(user.id)}</button>
                          </div>
                          <div class="col">
                            <span class="fw-bold text-secondary" id="comments">${pubpop.numbercomments}</span>
                            <button type="button" onclick="FocusOnCommentBox(${pubpop.id})" class="btn btn-secondary  w-auto "> Comment</button>

                          </div>
                        </div>
                        <br> 
                        <c:forEach items="${metiercmts.getComments(pubpop.id)}" var="c">
                        <div class="input-group flex-nowrap" id="${pubpop.id}">
                            <span class="input-group-text" id="addon-wrapping"><a href="profile.php?uid=${c.user_id}"><img src="PhotoServlet?id=${c.user_id}" style="height: 50px; width: 50px;" class="rounded-circle"></a></span>
                            <input type="text" class="form-control" value="${c.comment}" disabled aria-describedby="addon-wrapping">
                          
                          </div>
                          </c:forEach>
               <form action="addcomment.php?idP=${pubpop.id}&uid=${user.id}" method="post"> 
                        <div class="input-group flex-nowrap">
                       
                  
                            <span class="input-group-text" id="addon-wrapping"><img src="PhotoServlet?id=${user.id}" style="height: 50px; width: 50px;" class="rounded-circle"></span>
                            <input type="text" id="mycomment-${pubpop.id}" name="comment" required class="form-control" placeholder="Add your comment here" aria-label="Add your comment here" aria-describedby="addon-wrapping">
                           
                            
                            <button  id="commentbutton" type="submit" class="btn btn-outline-secondary "> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                            
  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576 6.636 10.07Zm6.787-8.201L1.591 6.602l4.339 2.76 7.494-7.493Z"/>
</svg></button>

                          </div>
                          </form>
                          
                        
                      </div>
                   
                </div>
            </div>
        </div>
      

        
        
            </div>
            