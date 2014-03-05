package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.util.HibernateUtil;

import jdbc.Contexte;
import jdbc.Utilisateurs;

public class CreateContextForm {
	public static final String CHAMP_NAME   = "contexte";
	public static final String CHAMP_COLOR   = "color";

	private Map<String, String> erreurs      = new HashMap<String, String>();
	private boolean validate = true;
	private ArrayList<Contexte> listContexte = new ArrayList<Contexte>();

	public void inscrireContexte( HttpServletRequest request, Utilisateurs utilisateurs )
	{
		Contexte contexte = new Contexte();
		String name = getValeurChamp( request, CHAMP_NAME );
		String color = getValeurChamp( request, CHAMP_COLOR );

		try {
			validationName( name , utilisateurs);
		} catch ( Exception e ) {
			validate = false;
			setErreur( CHAMP_NAME, e.getMessage() );
		}
		contexte.setNom(name);

		if(validate)
		{
			contexte.setCouleur(color);
			contexte.setFkIdUtilisateur(utilisateurs.getIdUtilisateur());
			insertion(contexte);
		}

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Contexte> chargementContextes( HttpServletRequest request ){
		//Recuperation de l'id de l'utilisateur connecté
		int connectedUser;
		HttpSession sessionUser = request.getSession();
		Utilisateurs us = (Utilisateurs) sessionUser.getAttribute("sessionUtilisateur");
		connectedUser = us.getIdUtilisateur();
		
		//Recuperation dans la BD
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		
		List<Contexte> listeContexte;
		listeContexte= (List<Contexte>)session.createQuery("select c from Contexte c where "
				+ "c.fkIdUtilisateur ='"+ connectedUser +"'").list();

		for(Contexte t : listeContexte){
				listContexte.add(t);
		}
		
		//Fermeture de session
		session.getTransaction().commit(); 
		session.close();

		return listContexte;
	}
	
	
	
	
	private void insertion(Contexte contexte)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(contexte);
		session.getTransaction().commit();
	}


	@SuppressWarnings("unchecked")
	private void validationName( String name , Utilisateurs u) throws Exception{
		if ( name == null ) {
			throw new Exception("Le champ du contexte est vide.");
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Contexte> ListerUtil;
		ListerUtil= (List<Contexte>)session.createQuery("select c from Contexte c where "
				+ "c.fkIdUtilisateur = '"+ u.getIdUtilisateur() +"'").list();
		
		for(Contexte a : ListerUtil){
			if(name.equals(a.getNom())){
				throw new Exception(
						"Veuillez utiliser un autre nom de contexte.");
			}
		}
		session.getTransaction().commit(); 
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}
	
	public boolean getValidate()
	{
		return validate;
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
