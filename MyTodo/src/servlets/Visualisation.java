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
import model.VisuTodo;

/**
 * Servlet implementation class Visualisation
 */
@WebServlet("/Visualisation")
public class Visualisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_TODO = "todo";
	public static final String ATT_VISU = "visu";
	public static final String ATT_FILTRE = "filtre";
	public static final String ATT_TRI = "tri";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE          = "/WEB-INF/visualisation.jsp";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/connexion";

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
    		ArrayList<Todo> mesTodo = visuTodo.appliquerFiltre("",request);

    		request.setAttribute( ATT_VISU, visuTodo );
    		request.setAttribute( ATT_TODO, mesTodo );
    		request.setAttribute( ATT_FILTRE, filtre);
    		request.setAttribute( ATT_TRI, tri);
    		
        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
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
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
}
