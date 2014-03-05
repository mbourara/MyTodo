package jdbc;

// Generated 5 mars 2014 14:03:57 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Todo generated by hbm2java
 */
public class Todo implements java.io.Serializable {

	private Integer idTodo;
	private String titre;
	private String description;
	private int degreImportance;
	private int contexte;
	private Date echeance;
	private int fkIdUtilisateur;

	public Todo() {
	}

	public Todo(String titre, String description, int degreImportance,
			int contexte, Date echeance, int fkIdUtilisateur) {
		this.titre = titre;
		this.description = description;
		this.degreImportance = degreImportance;
		this.contexte = contexte;
		this.echeance = echeance;
		this.fkIdUtilisateur = fkIdUtilisateur;
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

	public int getContexte() {
		return this.contexte;
	}

	public void setContexte(int contexte) {
		this.contexte = contexte;
	}

	public Date getEcheance() {
		return this.echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public int getFkIdUtilisateur() {
		return this.fkIdUtilisateur;
	}

	public void setFkIdUtilisateur(int fkIdUtilisateur) {
		this.fkIdUtilisateur = fkIdUtilisateur;
	}

}
