package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdbc.Utilisateurs;

import org.hibernate.Query;
import org.hibernate.Session;

import com.util.HibernateUtil;
import com.util.MD5Util;

public final class ModifyCompteForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_GMAIL = "gmail";
	private static final String CHAMP_PASS = "motdepasse";
	private static final String CHAMP_CONF = "confirmation";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";
	private static final String EXTEND_GMAIL = "@gmail.com";
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	private Map<String, String> contains      = new HashMap<String, String>();
	private int selected	= 0;
	private boolean validate = false;

	
public Utilisateurs ModifyUtilisateur(HttpServletRequest request) {

	Utilisateurs utilisateur = new Utilisateurs();	

	String email = getValeurChamp(request, CHAMP_EMAIL);
	String gmail = getValeurChamp(request, CHAMP_GMAIL);
	String motDePasse = getValeurChamp(request, CHAMP_PASS);
	String confirmation = getValeurChamp(request, CHAMP_CONF);
	String nom = getValeurChamp(request, CHAMP_NOM);
	String prenom = getValeurChamp(request, CHAMP_PRENOM);
	String login = getValeurChamp(request, CHAMP_LOGIN);

	//Recuperation de l'id de l'utilisateur connecté
	int connectedUser;
	HttpSession sessionUser = request.getSession();
	Utilisateurs us = (Utilisateurs) sessionUser.getAttribute("sessionUtilisateur");
	connectedUser = us.getIdUtilisateur();

	utilisateur.setIdUtilisateur(connectedUser);		
	try {
		validationEmail(email);
	} catch (Exception e) {
		setErreur(CHAMP_EMAIL, e.getMessage());
	}
	utilisateur.setMail(email);
	try {
		//validationEmail(gmail);
	} catch (Exception e) {
		setErreur(CHAMP_GMAIL, e.getMessage());
	}
	utilisateur.setGmail(gmail+EXTEND_GMAIL);
	try {
		validationMotsDePasse(motDePasse, confirmation);
	} catch (Exception e) {
		setErreur(CHAMP_PASS, e.getMessage());
		setErreur(CHAMP_CONF, null);
	}
	if (motDePasse != null) {
		utilisateur.setMotDePasse(MD5Util.cryptageMD5(motDePasse));
	}
	try {
		validationlogin(login);
	} catch (Exception e) {
		setErreur(CHAMP_LOGIN, e.getMessage());
	}
	utilisateur.setLogin(login);
	try {
		validationNom(nom);
	} catch (Exception e) {
		setErreur(CHAMP_NOM, e.getMessage());
	}
	utilisateur.setNom(nom);
	try {
		validationPrenom(prenom);
	} catch (Exception e) {
		setErreur(CHAMP_PRENOM, e.getMessage());
	}
	utilisateur.setPrenom(prenom);

	if (erreurs.isEmpty()) {
		validate = true;
		try {
			update(utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	return utilisateur;
}
	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		}
		else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String confirmation)
			throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception(
						"Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			}
			else if (motDePasse.length() < 3) {
				throw new Exception(
						"Les mots de passe doivent contenir au moins 3 caractères.");
			}
		}
		else {
			throw new Exception(
					"Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom != null) {
			if(nom.length() < 3) {
				throw new Exception(
						"Le nom d'utilisateur doit contenir au moins 3 caractères.");
			}
		}
		else
			throw new Exception(
					"Le nom d'utilisateur doit être rempli.");

	}

	@SuppressWarnings("unchecked")
	private void validationlogin(String login) throws Exception {
		if(login == null) {
			throw new Exception("Le login doit être rempli.");
		}

		//Vérification que le login n'existe pas déjà !
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Utilisateurs> ListerUtil;
		ListerUtil= (List<Utilisateurs>)session.createQuery("select u from Utilisateurs u ").list();

		for(Utilisateurs a : ListerUtil){
			if(login.equals(a.getLogin())){
				throw new Exception("Veuillez utiliser un autre login.");
			}
		}
		session.getTransaction().commit(); 
		//session.close();
	}

	private void validationPrenom(String prenom) throws Exception {
		if (prenom != null){
			if(prenom.length() < 3) {
				throw new Exception(
						"Le prenom de l'utilisateur doit contenir au moins 3 caractères.");
			}
		}
		else
			throw new Exception(
					"Le prénom de l'utilisateur doit être rempli.");
	}
	
	// Modification dans la base de donnée
	private void update(Utilisateurs u)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
	}
	
	public int getSelected()
	{
		return selected;
	}
	
	public boolean getValidate()
	{
		return validate;
	}
	
	public Map<String, String> getContains() {
		return contains;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}

	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}
}