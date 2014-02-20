package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.util.HibernateUtil;

import jdbc.Todo;
import jdbc.Utilisateurs;

public class ModifyTodoForm {
	public static final String CHAMP_TITLE   = "title";
	public static final String CHAMP_DESC   = "description";
	public static final String CHAMP_CONTEXT   = "contexte";
	public static final String CHAMP_ECHEANCEBEGIN   = "dateb";
	public static final String CHAMP_HOURBEGIN   = "hourb";
	public static final String CHAMP_MINBEGIN   = "minb";
	public static final String CHAMP_ECHEANCEEND   = "datee";
	public static final String CHAMP_HOUREND	= "houre";
	public static final String CHAMP_MINEND   = "mine";
	public static final String CHAMP_DEGRES   = "degres";
	public static final String CHAMP_ID_TODO = "idTodo";
	public static final SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	private Map<String, String> erreurs      = new HashMap<String, String>();
	private Map<String, String> contains      = new HashMap<String, String>();
	private int selected	= 0;
	private boolean validate = true;
	
	public Todo updateTodo( HttpServletRequest request, Utilisateurs utilisateurs )
	{
		Todo todo = new Todo();

		int idTodo = Integer.parseInt(getValeurChamp(request, CHAMP_ID_TODO));
		String title = getValeurChamp( request, CHAMP_TITLE );
		String description = getValeurChamp( request, CHAMP_DESC );
		String context = getValeurChamp( request, CHAMP_CONTEXT );
		String echeanceBegin = getValeurChamp( request, CHAMP_ECHEANCEBEGIN );
		String hourBegin = getValeurChamp( request, CHAMP_HOURBEGIN );
		String minBegin = getValeurChamp( request, CHAMP_MINBEGIN );
		String echeanceEnd = getValeurChamp( request, CHAMP_ECHEANCEEND );
		String hourEnd = getValeurChamp( request, CHAMP_HOUREND );
		String minEnd = getValeurChamp( request, CHAMP_MINEND );
		int degres = Integer.parseInt(getValeurChamp( request, CHAMP_DEGRES ));

		todo.setIdTodo(idTodo);
		try {
			validationTitle( title );
		} catch ( Exception e ) {
			validate = false;
			setErreur( CHAMP_TITLE, e.getMessage() );
		}
		todo.setTitre(title);
		setContains(CHAMP_TITLE, title);
		try {
			validationDescription(description); 
		} catch ( Exception e ) {
			validate = false;
			setErreur( CHAMP_DESC, e.getMessage() );
		}
		todo.setDescription(description);
		setContains(CHAMP_DESC, description);
		try {
			validationContext(context);
		} catch ( Exception e ) {
			validate = false;
			setErreur( CHAMP_CONTEXT, e.getMessage() );
		}
		todo.setContexte(context);
		setContains(CHAMP_CONTEXT, context);
		try {
			validationEcheance(echeanceBegin);
		} catch ( Exception e ) {
			validate = false;
			setErreur( CHAMP_ECHEANCEBEGIN, e.getMessage() );
		}

		Date date;
		try {
			if(echeanceBegin != null)
			{
				echeanceBegin += " ";
				echeanceBegin += hourBegin + ":";
				echeanceBegin += minBegin + ":00";
				date = dt.parse(echeanceBegin);
				
				//todo.setEcheanceBegin(date);
			}
			else
				validate = false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			validate = false;
			e.printStackTrace();
		} 
		setContains(CHAMP_ECHEANCEBEGIN, echeanceBegin);
		try {
			if(echeanceEnd != null)
			{
				echeanceEnd += " ";
				echeanceEnd += hourEnd + ":";
				echeanceEnd += minEnd + ":00";
				date = dt.parse(echeanceEnd);
				//todo.setEcheanceEnd(date);
			}
			else
				validate = false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			validate = false;
			e.printStackTrace();
		} 
		setContains(CHAMP_ECHEANCEEND, echeanceEnd);
		
		todo.setDegreImportance(degres);
		selected = degres;
		
		if(validate)
		{
			todo.setFkIdUtilisateur(utilisateurs.getIdUtilisateur());
			update(todo);
		}

		return todo;

	}

	private void update(Todo todo)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(todo);
		session.getTransaction().commit();
	}


	private void validationTitle( String title ) throws Exception{
		if ( title == null ) {
			throw new Exception("Le champ titre est vide.");
		}
	}
	private void validationDescription( String description ) throws Exception{
		if ( description == null ) {
			throw new Exception("Le champ description est vide.");
		}
	}
	private void validationContext( String context ) throws Exception{
		if ( context == null ) {
			throw new Exception("Le champ context est vide.");
		}
	}
	private void validationEcheance( String echeance ) throws Exception{
		if ( echeance == null ) {
			throw new Exception("Le champ echeance est vide.");
		}
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
	private void setContains( String champ, String message ) {
		contains.put( champ, message );
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
