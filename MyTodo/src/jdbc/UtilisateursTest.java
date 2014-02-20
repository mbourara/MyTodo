package jdbc;

// Generated 20 févr. 2014 12:59:52 by Hibernate Tools 4.0.0

/**
 * UtilisateursTest generated by hbm2java
 */
public class UtilisateursTest implements java.io.Serializable {

	private Integer idUtilisateur;
	private String login;
	private String motDePasse;
	private String mail;
	private String nom;
	private String prenom;
	private String gmail;
	private String gmotDePasse;

	public UtilisateursTest() {
	}

	public UtilisateursTest(String login, String motDePasse, String mail,
			String nom, String prenom) {
		this.login = login;
		this.motDePasse = motDePasse;
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
	}

	public UtilisateursTest(String login, String motDePasse, String mail,
			String nom, String prenom, String gmail, String gmotDePasse) {
		this.login = login;
		this.motDePasse = motDePasse;
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.gmail = gmail;
		this.gmotDePasse = gmotDePasse;
	}

	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGmail() {
		return this.gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getGmotDePasse() {
		return this.gmotDePasse;
	}

	public void setGmotDePasse(String gmotDePasse) {
		this.gmotDePasse = gmotDePasse;
	}

}
