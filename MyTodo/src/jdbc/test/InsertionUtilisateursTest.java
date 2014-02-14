package jdbc.test;

import static org.junit.Assert.*;
import jdbc.Utilisateurs;
import jdbc.UtilisateursTest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.util.HibernateUtil;

public class InsertionUtilisateursTest {

	@Test
	public void test() 
			throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Insertion dans la base de données.
		UtilisateursTest u = new UtilisateursTest("user","usermdp","user@adresse.com","userfirstname","userlastname","user@gmail.com");
		session.save(u);
		session.getTransaction().commit();
		// Récupération des informations.
		UtilisateursTest uverif = (UtilisateursTest) session.createQuery("select u from UtilisateursTest u where u.login='user'").uniqueResult();
		// Controle de la bonne insertion des données.
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getLogin().equals(u.getLogin()));
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getMotDePasse().equals(u.getMotDePasse()));
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getMail().equals(u.getMail()));
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getNom().equals(u.getNom()));
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getPrenom().equals(u.getPrenom()));
		assertTrue("L'insertion dans la BDD à échoué.", uverif.getGmail().equals(u.getGmail()));
		// Suppression de la ligne ajouté.
		Query q = session.createQuery("delete UtilisateursTest u where u.login='" + uverif.getLogin() + "'");
		q.executeUpdate();
		HibernateUtil.getSessionFactory().close();
	}

}
