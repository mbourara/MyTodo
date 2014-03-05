package servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.Todo;
import jdbc.Utilisateurs;
import model.CreateTodoForm;
import model.VisuTodo;

/**
 * Servlet implementation class Visualisation
 */
@WebServlet("/Visualisation")
public class Visualisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TODO = "todo";
	public static final String ATT_VISU = "visu";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE          = "/WEB-INF/visualisation.jsp";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/index";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
        /*if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page de connexion 
            response.sendRedirect( VUE_CONNEXION );
        } else {*/
    		//VisuTodo visuTodo = new VisuTodo();
    		//ArrayList<Todo> mesTodo = visuTodo.chargementTodo(request);

    		//request.setAttribute( ATT_VISU, visuTodo );
    		//request.setAttribute( ATT_TODO, mesTodo );
    		
        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
       // }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type.equals("createTodo")) {
			/* Recuperation de la session depuis la requête*/
			HttpSession session = request.getSession();
			
			CreateTodoForm form = new CreateTodoForm();
			Todo todo = form.inscrireTodo(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));

			
			if(form.getValidate())
				response.sendRedirect(VUE_VISU);
			else
			{
				request.setAttribute( ATT_FORM, form );
				this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			}
		}
	}
}
