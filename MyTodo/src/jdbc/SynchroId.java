package jdbc;

// Generated 4 mars 2014 12:49:37 by Hibernate Tools 4.0.0

/**
 * SynchroId generated by hbm2java
 */
public class SynchroId implements java.io.Serializable {

	private int fkIdTodo;
	private String fkIdEvent;
	private int idCalendar;

	public SynchroId() {
	}

	public SynchroId(int fkIdTodo, String fkIdEvent, int idCalendar) {
		this.fkIdTodo = fkIdTodo;
		this.fkIdEvent = fkIdEvent;
		this.idCalendar = idCalendar;
	}

	public int getFkIdTodo() {
		return this.fkIdTodo;
	}

	public void setFkIdTodo(int fkIdTodo) {
		this.fkIdTodo = fkIdTodo;
	}

	public String getFkIdEvent() {
		return this.fkIdEvent;
	}

	public void setFkIdEvent(String fkIdEvent) {
		this.fkIdEvent = fkIdEvent;
	}

	public int getIdCalendar() {
		return this.idCalendar;
	}

	public void setIdCalendar(int idCalendar) {
		this.idCalendar = idCalendar;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SynchroId))
			return false;
		SynchroId castOther = (SynchroId) other;

		return (this.getFkIdTodo() == castOther.getFkIdTodo())
				&& ((this.getFkIdEvent() == castOther.getFkIdEvent()) || (this
						.getFkIdEvent() != null
						&& castOther.getFkIdEvent() != null && this
						.getFkIdEvent().equals(castOther.getFkIdEvent())))
				&& (this.getIdCalendar() == castOther.getIdCalendar());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getFkIdTodo();
		result = 37 * result
				+ (getFkIdEvent() == null ? 0 : this.getFkIdEvent().hashCode());
		result = 37 * result + this.getIdCalendar();
		return result;
	}

}
