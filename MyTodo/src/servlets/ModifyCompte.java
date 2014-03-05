package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.Utilisateurs;
import model.ModifyCompteForm;

@WebServlet("/ModifyCompte")
public class ModifyCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE          = "/WEB-INF/modifyCompte.jsp";
	public static final String VUE_VISU = "http://localhost:8080/MyTodo/visualisation";
	public static final String VUE_CONNEXION = "http://localhost:8080/MyTodo/connexion";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ModifyCompteForm form = new ModifyCompteForm();

		Utilisateurs u = form.ModifyUtilisateur(request);

		if(form.getValidate()){
			session.setAttribute( ATT_SESSION_USER, u );
			response.sendRedirect(VUE_VISU);
		}
		else
		{
			request.setAttribute( ATT_FORM, form );
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
	}
}