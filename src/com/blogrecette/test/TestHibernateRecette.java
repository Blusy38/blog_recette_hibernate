/**
 * 
 */
package com.blogrecette.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Recette;
import com.blogrecette.pojos.Tag;
import com.blogrecette.services.CommentaireManager;
import com.blogrecette.services.RecetteManager;
import com.blogrecette.services.TagManager;
import com.blogrecette.utils.HibernateUtil;


class TestHibernateRecette {

	protected Session session;
	protected SessionFactory sessionFactory;
	public static void main(String args[]) throws Exception {
	
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			//Creation de l'objets  RecetteManager
			RecetteManager recetteManager = new RecetteManager();
			//CommentaireManager commentaireManager = new CommentaireManager();
			TagManager tagManager = new TagManager();
			

			//Id pour test
			int idRecette = 28;
			int idTag = 1;
			
			System.out.println("******************RECETTEBYID********************");			
			//On recupere la recette via son ID 
			Recette recette = (Recette) recetteManager.getAllByidTag(idTag);
			System.out.println(recette);
			
			//System.out.println("********************TAGBYID*********************");		
			//On recupere un tag via son ID 
			//Tag tag = tagManager.gettagById(idTag);
			//System.out.println(tag);
		
			//***************DELETE TAG FROM RECETTE*************************
			//System.out.println("********************DELETE TAG BY ID RECETTE*********************");	
			//recette.resetTag();
			
			//System.out.println("********************ADD TAG TO RECETTE*********************");
			//recette.addTag(tag);
			//System.out.println(tag);	

			//System.out.println("******************MOYENNE DES NOTES********************");			
			//On recupere la moyenne de note pour la recette 
			//recette.setMoyNote(commentaireManager.getNoteMoy(idRecette));
				
			System.out.println("********************UPDATE RECETTE*********************");
			//session.update(recette);

			session.flush();
			transaction.commit();}



	}

}
