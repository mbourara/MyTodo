package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

import org.hibernate.Session;

import com.util.HibernateUtil;

import jdbc.Todo;
import jdbc.Utilisateurs;

public class VisuTodo {
	private ArrayList<Todo> listTodo = new ArrayList<Todo>();
	private int nbTodo=0;
	public static final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	
	public ArrayList<Todo> getListTodo(){
		return listTodo;
	}
	
	public int getNbTodo(){
		return nbTodo;
	}
	
	public ArrayList<Todo> appliquerFiltre(String filtre, HttpServletRequest request){
		
		//Recuperation de l'id de l'utilisateur connecté
		int connectedUser;
		HttpSession sessionUser = request.getSession();
		Utilisateurs us = (Utilisateurs) sessionUser.getAttribute("sessionUtilisateur");
		connectedUser = us.getIdUtilisateur();
		
		//Recuperation dans la BD
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		
		if(filtre==null){
			filtre="";
		}
		
		switch(filtre){
		case "Tous" : filtreTous(session, connectedUser); break;
		case "Aujourd'hui" : filtreAdj(session, connectedUser); break;
		default : filtreTous(session, connectedUser); break;
		}
		
		//Fermeture de session
		session.getTransaction().commit(); 
		session.close();
		
		return listTodo;
	}
	
	@SuppressWarnings("unchecked")
	public void filtreAdj(Session session, int connectedUser){
		List<Todo> ListeTodo;
		Date dateAdj=new Date();
		
		ListeTodo= (List<Todo>)session.createQuery("select u from Todo u").list();

		for(Todo t : ListeTodo){
			if(t.getFkIdUtilisateur()==connectedUser){
				if(dt.format(t.getEcheance()).equals(dt.format(dateAdj))){
					listTodo.add(t);
					nbTodo=nbTodo+1;
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void filtreTous(Session session, int connectedUser){
		List<Todo> ListeTodo;
		ListeTodo= (List<Todo>)session.createQuery("select u from Todo u").list();
		
		//Recherche des Todo de l'utilisateur connecté
		for(Todo t : ListeTodo){
			if(t.getFkIdUtilisateur()==connectedUser){
				listTodo.add(t);
				nbTodo=nbTodo+1;				
			}
		}
	}
	
	public Todo getTodoByID(HttpServletRequest request, int idTodo){ 
		
		//Recuperation dans la BD
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		
		Todo todo;
		todo = (Todo)session.createQuery("select t from Todo t where ID_TODO = "+idTodo).uniqueResult();
		
		//Fermeture de session
		session.getTransaction().commit(); 
		session.close();
		
		return todo;
	}
	
	public ArrayList<Todo> appliquerTri(String tri, ArrayList<Todo> mesTodo, HttpServletRequest request){
		Comparator<Todo> pc = new PriorityComparator();
		
		switch(tri){
		case "Date de creation" : break;
		case "Priorite" :  Collections.sort(mesTodo, pc); break;
		default :  break;
		}
		
		return mesTodo;
	}
}
