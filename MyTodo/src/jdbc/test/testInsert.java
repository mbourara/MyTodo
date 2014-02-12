package jdbc.test;


import jdbc.Utilisateurs;

import org.hibernate.*;

import com.util.HibernateUtil;

public class testInsert {

	 public static void main(String[] args)
		throws HibernateException {
		 
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 
		 Utilisateurs u = new Utilisateurs("kiki22","kiki33","kiki@gmail.com","tata","toto");

		 session.save(u);
		 
		 session.getTransaction().commit();
		 HibernateUtil.getSessionFactory().close();
	 }
}	