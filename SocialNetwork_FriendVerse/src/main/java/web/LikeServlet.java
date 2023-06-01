package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IUserDao;
import dao.UserDaoImpl;
import metier.entities.Publication;
import metier.entities.User;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private IUserDao metier;
	

	public void init() throws ServletException {
		metier = new UserDaoImpl();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User currentuser = (User) request.getSession().getAttribute("currentuser");
	    String userId =  currentuser.getId();

	    // Retrieve publication ID from request parameters
	    int publicationId = Integer.parseInt(request.getParameter("pid"));
	    
	    Publication P = metier.getPublication(publicationId);
	    metier.likePublication(userId, publicationId);
	    int like = P.getNumberlikes()+1;
	    P.setNumberlikes(like);
	    response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(like));

	}

}
