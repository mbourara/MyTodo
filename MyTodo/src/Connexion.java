

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE          = "/WEB-INF/connexion.jsp";
	public static final String CHAMP_PASS   = "motdepasse";
	public static final String CHAMP_LOGIN  = "login";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
       
    public Connexion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
		Boolean connexion;
		Map<String, String> erreurs = new HashMap<String, String>();

		/* Récupération des champs du formulaire. */
		String login = request.getParameter( CHAMP_LOGIN );
		String motDePasse = request.getParameter( CHAMP_PASS );

		/* Validation du champ login. */
		try {
			validationLogin( login );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_LOGIN, e.getMessage() );
		}

		/* Validation des champs mot de passe */
		try {
			validationMotsDePasse( motDePasse );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_PASS, e.getMessage() );
		}

		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès de la connexion.";
			connexion = true;
		} else {
			resultat = "Échec de la connexion.";
			connexion = false;
		}

		/* Stockage du résultat et des messages d'erreur dans l'objet request */
		request.setAttribute( ATT_ERREURS, erreurs );
		request.setAttribute( ATT_RESULTAT, resultat );		
		request.setAttribute("connexion", connexion);

		/* Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * Valide le mot de passe n'est pas vide.
	 */
	private void validationMotsDePasse( String motDePasse ) throws Exception{
		if ( motDePasse == null || motDePasse.trim().length() == 0 ) {
				throw new Exception("Mot de passe invalide.");
		}
		validationBD(motDePasse, "mdp");
	}

	/**
	 * Verification que le login de l'utilisateur n'est pas vide.
	 */
	private void validationLogin( String login ) throws Exception {
		if ( login == null || login.trim().length() == 0) {
			throw new Exception( "Le login est invalide." );
		}
		validationBD(login, "login");
	}
	
	/**
	 * Verification dans la base de donnée.
	 */
	private void validationBD( String attribut, String type ) throws Exception {
		if(type.equals("mdp")){
			if(!attribut.equals("adr")){
				throw new Exception( "Le mot de passe n'existe pas." );
			}
		}
		else if(type.equals("login")){
			if(!attribut.equals("adrien")){
				throw new Exception( "Le login n'existe pas." );
			}
		}
	}
}
