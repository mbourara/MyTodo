package jdbc.test;

import static org.junit.Assert.*;

import java.util.Date;

import jdbc.TodoTest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.util.HibernateUtil;

public class InsertionTodoTest {

	@Test
	public void test() 	 
			throws HibernateException 	{

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Insertion dans la base de données.
		TodoTest t = new TodoTest("titre", "description", 2, "contexte", new Date(), 1);
		session.save(t);
		session.getTransaction().commit();
		// Récupération des informations.
		TodoTest tverif = (TodoTest) session.createQuery("select t from TodoTest t where t.fkIdUtilisateur='1'").uniqueResult();
		// Controle de la bonne insertion des données.
		assertTrue("L'insertion dans la BDD à échoué.", tverif.getContexte().equals(t.getContexte()));
		assertTrue("L'insertion dans la BDD à échoué.", tverif.getTitre().equals(t.getTitre()));
		assertTrue("L'insertion dans la BDD à échoué.", tverif.getDescription().equals(t.getDescription()));
		assertTrue("L'insertion dans la BDD à échoué.", tverif.getDegreImportance() == t.getDegreImportance());
		// Suppression de la ligne ajouté.
		Query q = session.createQuery("delete TodoTest t where t.idTodo='" + tverif.getIdTodo() + "'");
		q.executeUpdate();

		HibernateUtil.getSessionFactory().close();
	}

}
