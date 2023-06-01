package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Response;

import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.Comment;
import metier.entities.Photo;
import metier.entities.Publication;
import metier.entities.User;
import supp.Help;

@MultipartConfig
public class ControleurServlet extends HttpServlet {
	private IUserDao metier;
	private Help help = new Help();
	private User useronline = null;

	public void init() throws ServletException {
		metier = new UserDaoImpl();
	}
	
	public String likeButton(int id_pub, String id_user){
		if(metier.haslikedPublication(id_user, id_pub)) {
			return "Unlike";
		}
		return "Like";
	}
	
	public String followButton(String id_follower, String id_following){
		if(metier.isfollowedby(id_follower, id_following)) {
			return "Unfollow";
		}
		return "Follow";
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.php")) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (path.equals("/createaccount.php")) {
			request.getRequestDispatcher("createaccount.jsp").forward(request, response);
		} else if (path.equals("/saveuser.php") && (request.getMethod().equals("POST"))) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String birthdate = request.getParameter("birthdate");
			int age = help.calculateAge(birthdate);
			String gender = request.getParameter("gender");
			 String username = request.getParameter("username");
				String pass = request.getParameter("password");
				String password = help.doHashing(pass);
				User U = new User(firstname, lastname, gender, age, username, password);
				User newUser=metier.save(U);
			Part file=request.getPart("profilephoto");
			
			String imageFileName=file.getSubmittedFileName();
			InputStream fileContent = file.getInputStream();
			Photo p=null;
			byte[] imageData;
			 String mimeType;
			  long fileSize;
			if(imageFileName==""){
				String defaultImage = getServletContext().getRealPath("/img/profilephotos/default.jpg");
			      File file1 = new File(defaultImage);
			      try (FileInputStream fis = new FileInputStream(file1)) {
			    	   imageData = fis.readAllBytes();
					    mimeType = getServletContext().getMimeType("default.jpg");
					    Path path1 = Paths.get(getServletContext().getRealPath("/img/profilephotos/default.jpg"));
					    fileSize = Files.size(path1);
					    fis.close();
						 p = new Photo(imageFileName, mimeType, fileSize, imageData, newUser.getId());

			      } catch (IOException ex) {
			         ex.printStackTrace();
			      }
			}
			
			else {
			
		    imageData = fileContent.readAllBytes();
		    mimeType = getServletContext().getMimeType(imageFileName);
		    fileSize = file.getSize();
		   fileContent.close();
			 p = new Photo(imageFileName, mimeType, fileSize, imageData, newUser.getId());

			}
		   
		  
			Photo profilePhoto = metier.addPhoto(p, newUser);
			request.setAttribute("user", newUser);
			request.getSession().setAttribute("currentuser", newUser);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			
			
			
			
			

		} else if (path.equals("/login.php") && (request.getMethod().equals("POST"))) {
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			String password = help.doHashing(pass);
			User U = metier.getUser(username, password);
			if (U != null) {
				request.setAttribute("user", U);
				session.setAttribute("currentuser", U);
				response.sendRedirect("welcome.php");

			} else {
				
				String errorMessage ="<svg xmlns=\"http://www.w3.org/2000/svg\" style=\"display: none;\">\r\n"
						+ "  <symbol id=\"exclamation-triangle-fill\" fill=\"currentColor\" viewBox=\"0 0 16 16\">\r\n"
						+ "    <path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>\r\n"
						+ "  </symbol>\r\n"
						+ "</svg>"
						+ ""
						+ "<div class=\"alert alert-danger d-flex align-items-center\" role=\"alert\">\r\n"
						+ "  <svg class=\"bi flex-shrink-0 me-2\" width=\"24\" height=\"24\" role=\"img\" aria-label=\"Danger:\"><use xlink:href=\"#exclamation-triangle-fill\"/></svg>\r\n"
						+ "  <div>\r\n"
						+ "   Username or Password incorrect!\r\n"
						+ "  </div>\r\n"
						+ "</div>";
		        request.setAttribute("errorMessage", errorMessage );
		          request.getRequestDispatcher("index.jsp").forward(request, response); 

			}

		}
		else if (path.equals("/disconnect.php")){
			HttpSession session = request.getSession();
			session.invalidate();
			request.removeAttribute("user");
		    response.sendRedirect("index.php");
		    return;
			
		}
		else if (path.equals("/confirmer.php")){
			HttpSession session = request.getSession();

			User currentuser = (User) request.getSession().getAttribute("currentuser"); 
			session.setAttribute("currentuser", currentuser);
			request.setAttribute("user", currentuser);
			response.sendRedirect("welcome.php");
		    
			
		}
		

		else if (path.equals("/updateuser.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser"); 
			request.setAttribute("user", currentuser);
			request.getRequestDispatcher("modifyaccount.jsp").forward(request, response);

		}
		else if (path.equals("/modifyaccount.php") && (request.getMethod().equals("POST"))) {
			  String firstname = request.getParameter("firstname");
			  String lastname = request.getParameter("lastname");
			  String birthdate = request.getParameter("birthdate");
			  int age = help.calculateAge(birthdate);
			  String gender = request.getParameter("gender");
			 String username = request.getParameter("username"); String pass =
			  request.getParameter("password");
			 String password = help.doHashing(pass);
			 User user = new User(firstname, lastname, gender, age, username,password);
			 User currentuser = (User) request.getSession().getAttribute("currentuser"); 
			 user.setId(currentuser.getId());
			  User U = metier.update(user);

			 Part file=request.getPart("profilephoto");
				
				String imageFileName=file.getSubmittedFileName();
				InputStream fileContent = file.getInputStream();
				Photo p=null;
				byte[] imageData;
				 String mimeType;
				  long fileSize;
				if(imageFileName==""){
					imageFileName = metier.getPhoto(currentuser.getId()).getFilename();
					p = metier.getPhoto(currentuser.getId());

				      
				}
				
				else {
				
			    imageData = fileContent.readAllBytes();
			    mimeType = getServletContext().getMimeType(imageFileName);
			    fileSize = file.getSize();
			   fileContent.close();
				 p = new Photo(imageFileName, mimeType, fileSize, imageData,U.getId());

				}
				Photo profilePhoto = metier.updatePhoto(p, U);

			  
				

			  request.getSession().setAttribute("currentuser", U);
			response.sendRedirect("welcome.php");


		}
		else if (path.equals("/welcome.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			List<Publication> pubs = null;
			List<Publication> pubspops=null;
			if(currentuser==null) {
				response.sendError(Response.SC_UNAUTHORIZED);
				

			}
			else {
				request.setAttribute("user", currentuser);
				String uid = currentuser.getId();
				pubs = metier.getfollowingPublications(uid);
				pubspops = metier.getPublicationsbyPopularity(uid);
				
				request.setAttribute("publications", pubs);
				request.setAttribute("publicationspopulaires", pubspops);
				request.setAttribute("metiercmts", metier);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}
			
			
		}
		else if (path.equals("/search.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			String motCle = request.getParameter("motCle");
			UserModel model = new UserModel();
			model.setMc(motCle);
			List<User> users = metier.usersParMC("%" + motCle + "%");
			if (users == null) {
				response.sendError(Response.SC_NOT_FOUND);
			}
			model.setUsers(users);
			request.setAttribute("model", model);
			request.setAttribute("user", currentuser);
			request.getRequestDispatcher("searchresult.jsp").forward(request, response);
		}
		else if (path.equals("/addimage.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			Part file=request.getPart("photo");
			String descr = request.getParameter("description");
			
			String imageFileName=file.getSubmittedFileName();
			InputStream fileContent = file.getInputStream();
			Publication p=null;
			byte[] imageData;
			 String mimeType;
			  long fileSize;
			  int n_likes=0,n_comments = 0;
			  long date_pub = System.currentTimeMillis() / 1000;
			
			
		    imageData = fileContent.readAllBytes();
		    mimeType = getServletContext().getMimeType(imageFileName);
		    fileSize = file.getSize();
		   fileContent.close();
			 p = new Publication(imageFileName, mimeType, fileSize, imageData,n_likes,n_comments,descr ,  date_pub, currentuser.getId());

			
		   
		  
			Publication pub = metier.addPublication(p, currentuser);
			String id = currentuser.getId();
			response.sendRedirect("profile.php?uid="+id);
		}
		else if (path.equals("/addcomment.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String comment= request.getParameter("comment");	
			Comment c= new Comment(currentuser.getId(),comment);
			int pub_id =  Integer.parseInt(request.getParameter("idP"));
			Publication p = metier.getPublication(pub_id);
			Comment cmt = metier.commentPublication(currentuser.getId(),pub_id,comment);
			String userid = request.getParameter("uid");
			System.out.println(userid);
			System.out.println(currentuser.getId());
			if(userid.equals(currentuser.getId())) {
				response.sendRedirect("welcome.php?#"+pub_id);

			} 
			else {
				response.sendRedirect("profile.php?uid="+userid+"#"+pub_id);

			}
		}
		else if (path.equals("/addcommentonpopular.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String comment= request.getParameter("comment");	
			Comment c= new Comment(currentuser.getId(),comment);
			int pub_id =  Integer.parseInt(request.getParameter("idP"));
			Publication p = metier.getPublication(pub_id);
			Comment cmt = metier.commentPublication(currentuser.getId(),pub_id,comment);
			String userid = request.getParameter("uid");
			System.out.println(userid);
			System.out.println(currentuser.getId());
			response.sendRedirect("welcome.php?#publication-"+pub_id);
			
		}
		else if (path.equals("/followuser.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String userid = request.getParameter("uid");
			User followedUser = metier.getUser(userid); 
			metier.followUser(currentuser.getId(), userid);
			int n_followers = followedUser.getNumberfollowers()+1;
		    followedUser.setNumberfollowers(n_followers);
		    response.setContentType("text/plain");
	        response.getWriter().write(String.valueOf(n_followers));

		}
		else if (path.equals("/unfollowuser.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String userid = request.getParameter("uid");
			User followedUser = metier.getUser(userid); 
			metier.unfollowUser(currentuser.getId(), userid);
			int n_followers = followedUser.getNumberfollowers()-1;
		    followedUser.setNumberfollowers(n_followers);
		    response.setContentType("text/plain");
	        response.getWriter().write(String.valueOf(n_followers));

		}
		else if (path.equals("/profile.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			String uidd = currentuser.getId();
			request.setAttribute("user", currentuser);
			String userid = request.getParameter("uid");
			List<User> following = metier.getfollowingUsers(userid);
			request.setAttribute("following", following);
			User u = metier.getUser(userid);
			request.setAttribute("userprofile", u);
			String button_edit = null;
			String button_add = null;
			if (u.getId().equals(currentuser.getId())) {
				button_edit="  <form action=\"updateuser.php\" method=\"post\">\r\n"
						+ "      <button class=\"btn btn-outline-primary\" type=\"submit\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-pencil-square\" viewBox=\"0 0 16 16\">\r\n"
						+ "  <path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"/>\r\n"
						+ "  <path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"/>\r\n"
						+ "</svg> Edit profile</button>\r\n"
						+ "</form>";
				button_add="<form action=\"addimage.php\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
						+ "   	<label class=\"form-label\" for=\"customFile\">Choose a photo here</label>\r\n"
						+ "<input required type=\"file\"  accept=\"image/png, image/jpeg\" class=\"form-control\" id=\"customFile\" name=\"photo\" />\r\n"
						+ "<br>\r\n"
						+ "   	<label class=\"form-label\" for=\"customFile\">Add the description here</label>\r\n"
						+ "<input type=\"text\"   class=\"form-control\" id=\"customFile\" name=\"description\" />\r\n"
						+ "<br>\r\n"
						+ "<div class=\"text-center\">\r\n"
						+ "	<button class=\"btn btn-outline-primary \" type=\"submit\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-camera\" viewBox=\"0 0 16 16\">\r\n"
						+ "  <path d=\"M15 12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V6a1 1 0 0 1 1-1h1.172a3 3 0 0 0 2.12-.879l.83-.828A1 1 0 0 1 6.827 3h2.344a1 1 0 0 1 .707.293l.828.828A3 3 0 0 0 12.828 5H14a1 1 0 0 1 1 1v6zM2 4a2 2 0 0 0-2 2v6a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2h-1.172a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 9.172 2H6.828a2 2 0 0 0-1.414.586l-.828.828A2 2 0 0 1 3.172 4H2z\"/>\r\n"
						+ "  <path d=\"M8 11a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5zm0 1a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7zM3 6.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z\"/>\r\n"
						+ "</svg> Add photo</button>\r\n"
						+ "</div>\r\n"
						+ "</form>";
				
				request.setAttribute("buttonAdd", button_add);

			} else {
				String b = u.followbutton(uidd);
				button_edit= "<button class=\"btn btn-primary\" onclick=\"followUser('"+userid+"')\" id=\"followBtn\">"+b+"</button>\r\n";
				System.out.println(button_edit);
				
				
			}
			List<Publication> publications = metier.getPublicationsbyUser(userid);
			request.setAttribute("metiercmts", metier);
			request.setAttribute("publications", publications);
			request.setAttribute("buttonEdit", button_edit);
			request.setAttribute("uidd", uidd);
			request.getRequestDispatcher("profile.jsp").forward(request, response);

		}
		else if (path.equals("/following.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String userid = request.getParameter("uid");
			List<User> following = metier.getfollowingUsers(userid);
			request.setAttribute("following", following);
			User u = metier.getUser(userid);
			request.setAttribute("userprofile", u);
			request.getRequestDispatcher("following.jsp").forward(request, response);

		}
		else if (path.equals("/follower.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			String userid = request.getParameter("uid");
			List<User> follower = metier.getfollowerUsers(userid);
			request.setAttribute("follower", follower);
			User u = metier.getUser(userid);
			request.setAttribute("userprofile", u);
			request.getRequestDispatcher("follower.jsp").forward(request, response);

		}
		else if (path.equals("/likes.php")) {
			User currentuser = (User) request.getSession().getAttribute("currentuser");
			request.setAttribute("user", currentuser);
			int pid = Integer.parseInt(request.getParameter("pid")) ;
			List<User> likers = metier.getLikers(pid);
			request.setAttribute("likers", likers);
			request.getRequestDispatcher("likes.jsp").forward(request, response);

		}


		else {
			response.sendError(Response.SC_NOT_FOUND);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
