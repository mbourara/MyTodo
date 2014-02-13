package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Utilisateurs;
import model.InscriptionForm;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	private static final String VUE_REDIRECTION = "http://localhost:8080/MyTodo/connexion";
	public static String VUE = "/WEB-INF/inscription.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			this.getServletContext().getRequestDispatcher(VUE)
					.forward(request, response);
		}
	}
}