package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.Photo;
import metier.entities.User;
import supp.Help;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {
	private IUserDao metier;
	

	public void init() throws ServletException {
		metier = new UserDaoImpl();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Get the user ID parameter from the request
		String user_id = (String) request.getParameter("id");
	      // Retrieve the photo data from the database
	      Photo photo = metier.getPhoto(user_id);
	      byte[] photoData =  photo.getData();
 
	      // Set the content type and write the photo data to the response
	      response.setContentType("image/jpeg");
	      response.getOutputStream().write(photoData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
