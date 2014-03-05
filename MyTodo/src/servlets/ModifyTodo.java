package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.Contexte;
import jdbc.Todo;
import jdbc.Utilisateurs;
import model.ModifyTodoForm;
import model.VisuTodo;

/**
 * Servlet implementation class ModifyTodo
 */
@WebServlet("/ModifyTodo")
public class ModifyTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TODO = "todo";
	public static final String ATT_FORM = "form";
	public static final String ATT_CONT = "contexte";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE          = "/WEB-INF/modifyTodo.jsp";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/connexion";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyTodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ModifyTodoForm form = new ModifyTodoForm();
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		request.setAttribute( ATT_CONT, mesCont );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Recuperation de la session depuis la requête*/
		HttpSession session = request.getSession();
		VisuTodo visu = new VisuTodo();
		ModifyTodoForm form = new ModifyTodoForm();
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		request.setAttribute( ATT_CONT, mesCont );
		
		if(request.getParameter("IDTodo")!= null) {
			int idTodo = Integer.parseInt(request.getParameter("IDTodo"));
			
			Todo todo = visu.getTodoByID(request, idTodo);
						
			request.setAttribute("todo", todo);
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
		else {
		
			Todo todo2 = form.updateTodo(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
	
			
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
