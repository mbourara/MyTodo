package jdbc;

// Generated 19 févr. 2014 13:52:00 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Todo generated by hbm2java
 */
public class Todo implements java.io.Serializable {

	private Integer idTodo;
	private String titre;
	private String description;
	private int degreImportance;
	private String contexte;
	private Date echeanceBegin;
	private Date echeanceEnd;
	private int fkIdUtilisateur;
	private String idCalendar;

	public Todo() {
	}

	public Todo(String titre, String description, int degreImportance,
			String contexte, Date echeanceBegin, Date echeanceEnd,
			int fkIdUtilisateur) {
		this.titre = titre;
		this.description = description;
		this.degreImportance = degreImportance;
		this.contexte = contexte;
		this.echeanceBegin = echeanceBegin;
		this.echeanceEnd = echeanceEnd;
		this.fkIdUtilisateur = fkIdUtilisateur;
	}

	public Todo(String titre, String description, int degreImportance,
			String contexte, Date echeanceBegin, Date echeanceEnd,
			int fkIdUtilisateur, String idCalendar) {
		this.titre = titre;
		this.description = description;
		this.degreImportance = degreImportance;
		this.contexte = contexte;
		this.echeanceBegin = echeanceBegin;
		this.echeanceEnd = echeanceEnd;
		this.fkIdUtilisateur = fkIdUtilisateur;
		this.idCalendar = idCalendar;
	}

	public Integer getIdTodo() {
		return this.idTodo;
	}

	public void setIdTodo(Integer idTodo) {
		this.idTodo = idTodo;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDegreImportance() {
		return this.degreImportance;
	}

	public void setDegreImportance(int degreImportance) {
		this.degreImportance = degreImportance;
	}

	public String getContexte() {
		return this.contexte;
	}

	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	public Date getEcheanceBegin() {
		return this.echeanceBegin;
	}

	public void setEcheanceBegin(Date echeanceBegin) {
		this.echeanceBegin = echeanceBegin;
	}

	public Date getEcheanceEnd() {
		return this.echeanceEnd;
	}

	public void setEcheanceEnd(Date echeanceEnd) {
		this.echeanceEnd = echeanceEnd;
	}

	public int getFkIdUtilisateur() {
		return this.fkIdUtilisateur;
	}

	public void setFkIdUtilisateur(int fkIdUtilisateur) {
		this.fkIdUtilisateur = fkIdUtilisateur;
	}

	public String getIdCalendar() {
		return this.idCalendar;
	}

	public void setIdCalendar(String idCalendar) {
		this.idCalendar = idCalendar;
	}

}
