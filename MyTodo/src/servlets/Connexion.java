package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.Utilisateurs;
import model.ConnexionForm;
import model.InscriptionForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/index.jsp";
	public static final String VUE_VALID_CONNEXION = "http://localhost:8080/MyTodo/visualisation";
	private static final String VUE_REDIRECTION = "http://localhost:8080/MyTodo/index";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
		else{
			response.sendRedirect( VUE_VALID_CONNEXION );
		}
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String type = request.getParameter("type");
		
		if(type.equals("connexion")) {
		
			/* Préparation de l'objet formulaire */
			ConnexionForm form = new ConnexionForm();
	
			/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
			Utilisateurs utilisateur = form.connecterUtilisateur( request );
	
			/* Recuperation de la session depuis la requête*/
			HttpSession session = request.getSession();
	
			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute( ATT_FORM, form );
			request.setAttribute( ATT_USER, utilisateur );
	
			if ( form.getErreurs().isEmpty() ) {
				session.setAttribute( ATT_SESSION_USER, utilisateur );
				//this.getServletContext().getRequestDispatcher( VUE_VALID_CONNEXION ).forward( request, response );
				//Plutôt cette méthode car sinon l'utilisateur ne voit pas le changement de page dans sa barre d'addresse
				response.sendRedirect( VUE_VALID_CONNEXION );
			} else {
				session.setAttribute( ATT_SESSION_USER, null );
				this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			}
		}
		else if (type.equals("inscription")) {
			/* Préparation de l'objet formulaire */
			InscriptionForm form = new InscriptionForm();

			/*
			 * Appel au traitement et à la validation de la requête, et récupération
			 * du bean en résultant
			 */
			Utilisateurs u = form.inscrireUtilisateur(request);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, u);
			if (form.getErreurs().isEmpty()) {
				response.sendRedirect(VUE_REDIRECTION);

			} else {
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			}
		}
	} 
}