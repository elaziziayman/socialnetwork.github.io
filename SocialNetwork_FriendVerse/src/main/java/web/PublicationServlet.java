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
import metier.entities.Publication;

/**
 * Servlet implementation class PublicationServlet
 */
@WebServlet("/PublicationServlet")
public class PublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private IUserDao metier;
	

	public void init() throws ServletException {
		metier = new UserDaoImpl();
	}
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pub_id = Integer.parseInt(request.getParameter("pid")) ;
	      // Retrieve the photo data from the database
	      Publication pub = metier.getPublication(pub_id);
	      byte[] photoData =  pub.getData();

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
