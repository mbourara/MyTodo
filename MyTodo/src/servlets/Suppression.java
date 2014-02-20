package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteTodo;

@WebServlet("/Suppression")
public class Suppression extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE          = "/WEB-INF/visualisation.jsp";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/connexion";
	public static final String VUE_VISUALISATION = "http://localhost:8080/MyTodo/visualisation";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		String paramDelete = request.getParameter("deleteTodo");
		
		if ( session.getAttribute( ATT_SESSION_USER ) == null || paramDelete==null ) {
			response.sendRedirect( VUE_CONNEXION );
		} else {

			DeleteTodo deleteTodo = new DeleteTodo();

			if(paramDelete!=null){
				if(!paramDelete.isEmpty()){
					int todoID = Integer.parseInt(paramDelete);
					deleteTodo.suppressionTodo(request,todoID);
				}
			}
			response.sendRedirect( VUE_VISUALISATION );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
