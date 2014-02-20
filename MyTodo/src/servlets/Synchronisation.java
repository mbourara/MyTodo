package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SynchronisationForm;
import jdbc.Utilisateurs;

/**
 * Servlet implementation class Synchronisation
 */
@WebServlet("/Synchronisation")
public class Synchronisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE = "/WEB-INF/synchronisation.jsp";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_FORM = "form";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Synchronisation() {
        super();
        // TODO Auto-generated constructor stub        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		SynchronisationForm form = new SynchronisationForm();
		try {
			form.generateCalendar((Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute( ATT_FORM, form );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* Recuperation de la session depuis la requête*/
		HttpSession session = request.getSession();
		
		SynchronisationForm form = new SynchronisationForm();
		form.synchro(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		
		request.setAttribute( ATT_FORM, form );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
