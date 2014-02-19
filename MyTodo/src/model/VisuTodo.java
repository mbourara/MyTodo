package model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdbc.Todo;
import jdbc.Utilisateurs;

import org.hibernate.Session;

import com.util.HibernateUtil;

public class VisuTodo {
	private ArrayList<Todo> listTodo = new ArrayList<Todo>();
	private int nbTodo=0;
	
	public ArrayList<Todo> getListTodo(){
		return listTodo;
	}
	
	public int getNbTodo(){
		return nbTodo;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Todo> chargementTodo( HttpServletRequest request ){
		//Recuperation de l'id de l'utilisateur connecté
		int connectedUser;
		HttpSession sessionUser = request.getSession();
		Utilisateurs us = (Utilisateurs) sessionUser.getAttribute("sessionUtilisateur");
		connectedUser = us.getIdUtilisateur();
		
		//Recuperation dans la BD
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		
		List<Todo> ListeTodo;
		ListeTodo= (List<Todo>)session.createQuery("select u from Todo u").list();
		
		//Recherche des Todo de l'utilisateur connecté
		for(Todo t : ListeTodo){
			if(t.getFkIdUtilisateur()==connectedUser){
				listTodo.add(t);
				nbTodo=nbTodo+1;
			}
		}
		
		//Fermeture de session
		session.getTransaction().commit(); 
		session.close();

		return listTodo;
	}
}
