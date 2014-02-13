package model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jdbc.Utilisateurs;

import org.hibernate.Session;

import com.util.HibernateUtil;

public final class InscriptionForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_GMAIL = "email";

	private static final String CHAMP_PASS = "motdepasse";
	private static final String CHAMP_CONF = "confirmation";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateurs inscrireUtilisateur(HttpServletRequest request) {
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String Gmail = getValeurChamp(request, CHAMP_GMAIL);

		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		String confirmation = getValeurChamp(request, CHAMP_CONF);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String login = getValeurChamp(request, CHAMP_LOGIN);

		Utilisateurs utilisateur = new Utilisateurs();

		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setMail(email);
		try {
			validationEmail(Gmail);
		} catch (Exception e) {
			setErreur(CHAMP_GMAIL, e.getMessage());
		}
		utilisateur.setMail(Gmail);
		try {
			validationMotsDePasse(motDePasse, confirmation);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
			setErreur(CHAMP_CONF, null);
		}
		utilisateur.setMotDePasse(motDePasse);
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
			resultat = "Succès de l'inscription.";
			try {
				insertBD(login, motDePasse, email, nom, prenom);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resultat = "Échec de l'inscription.";
		}

		return utilisateur;
	}

	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String confirmation)
			throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception(
						"Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.length() < 3) {
				throw new Exception(
						"Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception(
					"Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.length() < 3) {
			throw new Exception(
					"Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void validationlogin(String login) throws Exception {
		if (login != null && login.length() < 3) {
			throw new Exception("Le login doit contenir au moins 3 caractères.");
		}
	}

	private void validationPrenom(String prenom) throws Exception {
		if (prenom != null && prenom.length() < 3) {
			throw new Exception(
					"Le prenom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	@SuppressWarnings({ "unused" })
	private void insertBD(String login, String motDePasse, String mail,
			String nom, String prenom) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Utilisateurs u = new Utilisateurs();

		u.setLogin(login);
		u.setMotDePasse(motDePasse);
		u.setMail(mail);
		u.setGmail(mail);
		u.setNom(nom);
		u.setPrenom(prenom);

		session.save(u);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();

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
