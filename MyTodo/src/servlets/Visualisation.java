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
import model.CreateContextForm;
import model.CreateTodoForm;
import model.SynchronisationForm;
import model.VisuTodo;

/**
 * Servlet implementation class Visualisation
 */
@WebServlet("/Visualisation")
public class Visualisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TODO = "todo";
	public static final String ATT_CONT = "contexte";
	public static final String ATT_VISU = "visu";
	public static final String ATT_FILTRE = "filtre";
	public static final String ATT_TRI = "tri";
	public static final String ATT_FORM = "form";
	public static final String ATT_FORM_SYNCHRO = "formSynchro";
	public static final String ATT_PAGE = "page";

	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE          = "/WEB-INF/visualisation.jsp";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/index";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
			/* Redirection vers la page de connexion */
			response.sendRedirect( VUE_CONNEXION );
		} else {
			String filtre = request.getParameter("typeFiltre");
			String tri = request.getParameter("typeTri");

			if(filtre==null){
				filtre = "Tous";
			}
			if(tri==null){
				tri="Date de creation";
			}

			VisuTodo visuTodo = new VisuTodo();
			CreateContextForm form = new CreateContextForm();
			ArrayList<Todo> mesTodo = visuTodo.appliquerFiltre("",request);
			ArrayList<Contexte> mesCont = form.chargementContextes(request);

			request.setAttribute( ATT_FILTRE, filtre);
			request.setAttribute( ATT_TRI, tri);
			request.setAttribute( ATT_VISU, visuTodo );
			request.setAttribute( ATT_TODO, mesTodo );
			request.setAttribute( ATT_CONT, mesCont );
			request.setAttribute( ATT_PAGE, "visu");

			initSynchro(request, response, session);
			
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean valid = false;
		String page = request.getParameter(ATT_PAGE);
		HttpSession session = request.getSession();
		
		if(page.equals("createTodo")) {
			valid = createTodo(request, response, session);
		}
		else if(page.equals("createContext")){
			valid = createContext(request, response, session);
		}
		else if(page.equals("synchro")) {
			synchroCalendar(request, response, session);
			valid = true;
		}
		else if(page.equals("visu")) {
			request.setAttribute( ATT_PAGE, "visu");
		}
		
		if(valid){
			request.setAttribute( ATT_PAGE, "visu");
		}
		
		getVisu(request, response, session);
	}
	
	private void initSynchro(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SynchronisationForm formSynchro = new SynchronisationForm();
		try {
			formSynchro.generateCalendar((Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute( ATT_FORM_SYNCHRO, formSynchro );
	}
	
	private void synchroCalendar(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SynchronisationForm formSynchro = new SynchronisationForm();
		formSynchro.synchro(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		
		request.setAttribute( ATT_FORM_SYNCHRO, formSynchro );
	}

	private void getVisu(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		/* Filtres & Tris */
		String filtre = request.getParameter("typeFiltre");
		String tri = request.getParameter("typeTri");

		/* Filtre */
		if(filtre==null){
			String dernierFiltre = (String) session.getAttribute(ATT_FILTRE);
			if(dernierFiltre==null){
				filtre = "Tous";
			}
			else{
				filtre = dernierFiltre;
			}
		}

		/* Tri */
		if(tri==null){
			String dernierTri = (String) session.getAttribute(ATT_TRI);
			if(dernierTri==null){
				tri="Date de creation";
			}
			else{
				tri = dernierTri;
			}
		}
	
		VisuTodo visuTodo = new VisuTodo();
		ArrayList<Todo> mesTodo = visuTodo.appliquerFiltre(filtre, request);

		/* Tri */
		mesTodo = visuTodo.appliquerTri(tri, mesTodo, request);

		request.setAttribute( ATT_VISU, visuTodo );
		request.setAttribute( ATT_TODO, mesTodo );
		request.setAttribute( ATT_FILTRE, filtre);
		request.setAttribute( ATT_TRI, tri);
		session.setAttribute( ATT_FILTRE, filtre);
		session.setAttribute( ATT_TRI, tri);

		CreateContextForm form = new CreateContextForm();
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		
		request.setAttribute( ATT_CONT, mesCont );

		initSynchro(request, response, session);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	private boolean createContext(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		CreateContextForm form = new CreateContextForm();
		form.inscrireContexte(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		request.setAttribute( ATT_PAGE, "createContext");
		return form.getValidate();
	}

	private boolean createTodo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		CreateTodoForm form = new CreateTodoForm();
		form.inscrireTodo(request, (Utilisateurs) session.getAttribute(ATT_SESSION_USER));
		ArrayList<Contexte> mesCont = form.chargementContextes(request);
		request.setAttribute( ATT_CONT, mesCont );
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_PAGE, "createTodo");
		return form.getValidate();
	}
}
