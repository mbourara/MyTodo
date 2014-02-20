package model;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import com.util.HibernateUtil;

public class DeleteTodo {
	
	public void suppressionTodo(HttpServletRequest request, int ID_Todo){
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		
		Query q = session.createQuery("delete Todo t where t.idTodo ='" + ID_Todo + "'");
		q.executeUpdate();

		session.getTransaction().commit(); 
		session.close();
	}
}
