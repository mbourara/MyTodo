package model;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.util.HibernateUtil;
import com.util.MD5Util;

import jdbc.Utilisateurs;

public final class ConnexionForm {
	public static final String CHAMP_PASS   = "motdepasseC";
	public static final String CHAMP_LOGIN  = "loginC";
	public static final String CHAMP_BD  = "bd";

	private String              resultat;
	private Boolean				validConnexion;
	private Map<String, String> erreurs      = new HashMap<String, String>();
	private Map<String, String> contains      = new HashMap<String, String>();
	
	public String getResultat() {
		return resultat;
	}

	public Boolean getValidConnexion(){
		return validConnexion;
	}
	public Map<String, String> getContains() {
		return contains;
	}
	private void setContains( String champ, String message ) {
		contains.put( champ, message );
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public Utilisateurs connecterUtilisateur( HttpServletRequest request ) {
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String login = getValeurChamp( request, CHAMP_LOGIN );

		setContains("loginC", login);
		setContains("motdepasseC", motDePasse);
		
		Utilisateurs utilisateur = new Utilisateurs();

		try {
			validationMotsDePasse( motDePasse );
		} catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		utilisateur.setMotDePasse( motDePasse );

		try {
			validationLogin( login );
		} catch ( Exception e ) {
			setErreur( CHAMP_LOGIN, e.getMessage() );
		}
		utilisateur.setLogin( login );

		if ( erreurs.isEmpty() ) {
			//Verification dans la BD
			try {
				validConnexion = validationBD(login, motDePasse, utilisateur);
			} catch (Exception e) {
				setErreur( CHAMP_BD, e.getMessage());
			}

			if(validConnexion){
				resultat = "Succès de la connexion.";
			}
			else{
				setErreur( CHAMP_BD, "Login ou Mdp invalide.");
				resultat = "Échec de la connexion.";
			}
		} else {
			resultat = "Échec de la connexion.";
		}
		return utilisateur;
	}

	private void validationMotsDePasse( String motDePasse ) throws Exception{
		if ( motDePasse == null ) {
			throw new Exception("Mot de passe invalide.");
		}
	}

	private void validationLogin( String login ) throws Exception {
		if ( login == null ) {
			throw new Exception( "Le login est invalide." );
		}
	}

	@SuppressWarnings("unchecked")
	private boolean validationBD( String login , String mdp, Utilisateurs utilisateur) throws Exception {
		Boolean loginTrouve = false;
		Utilisateurs user = null;
		Boolean valide = false;

		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 

		List<Utilisateurs> ListerUtil;
		ListerUtil= (List<Utilisateurs>)session.createQuery("select u from Utilisateurs u").list();

		//Recherche du login
		for(Utilisateurs a : ListerUtil){
			if(login.equals(a.getLogin())){
				loginTrouve = true;
				user = a;
			}
		}
		if(loginTrouve){
			//Verification du mdp
			if(MD5Util.cryptageMD5(mdp).equals(user.getMotDePasse())){
				valide = true;
				utilisateur.setGmail(user.getGmail());
				utilisateur.setIdUtilisateur(user.getIdUtilisateur());
				utilisateur.setLogin(user.getLogin());
				utilisateur.setMail(user.getMail());
				utilisateur.setMotDePasse(user.getMotDePasse());
				utilisateur.setNom(user.getNom());
				utilisateur.setPrenom(user.getPrenom());
				utilisateur.setGmotDePasse(user.getGmotDePasse());
			}
		}	

		//Fermeture de session
		session.getTransaction().commit(); 
		session.close();

		return valide;
	}

	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur.trim();
		}
	}
}
