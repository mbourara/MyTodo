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
import model.CreateTodoForm;

/**
 * Servlet implementation class CreateTodo
 */
@WebServlet("/CreateTodo")
public class CreateTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE = "/WEB-INF/createTodo.jsp";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_CONT = "contexte";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CreateTodoForm form = new CreateTodoForm();
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		request.setAttribute( ATT_CONT, mesCont );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* Recuperation de la session depuis la requête*/
		HttpSession session = request.getSession();
		
		CreateTodoForm form = new CreateTodoForm();
		Todo todo = form.inscrireTodo(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		request.setAttribute( ATT_CONT, mesCont );
		
		if(form.getValidate())
			response.sendRedirect(VUE_VISU);
		else
		{
			request.setAttribute( ATT_FORM, form );
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
	}

}
