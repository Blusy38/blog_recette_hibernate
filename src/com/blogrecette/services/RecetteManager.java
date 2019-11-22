/**
 * 
 */
package com.blogrecette.services;




import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Recette;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class RecetteManager {

	protected Session session;
	protected SessionFactory sessionFactory;

	//Requete pour créer une recette ***********************************
	public Recette creatRecette(Recette recette) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.save(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette;
	}

	//Fonction pour afficher une recette par rapport a son ID***********************
	public Recette getRecetteFromId(int id) {

		Transaction transaction = null;
		Recette recette = null;
		CommentaireManager commentaireManager = new CommentaireManager();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			//Recupere dans la BDD
			recette = session.get(Recette.class, id);
			
			//On recupere la moyenne de note pour la recette 
			recette.setMoyNote(commentaireManager.getNoteMoy(id));
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette;

	}


	//Fonction pour afficher tous les recettes ****************************************
	@SuppressWarnings("unchecked")
	public List<Recette> getAll() {
		Transaction transaction = null;
		List < Recette > recettes = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
			recettes = session.createQuery("from Recette").getResultList();
			//On recupere la moyenne de note pour la recette 
			CommentaireManager commentaireManager = new CommentaireManager();
			for (Recette recette : recettes) {
				recette.setMoyNote(commentaireManager.getNoteMoy(recette.getIdRecette()));
			}
			
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recettes;

	}

	//Fonction pour afficher tous les recettes par categories ****************************************
	@SuppressWarnings("unchecked")
	public List<Recette> getAllByIdCat(int id) {
		Transaction transaction = null;
		List < Recette > recettes = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			recettes = session.createQuery("FROM Recette R where R.categorie ="+id).getResultList();
			//On recupere la moyenne de note pour la recette 
			CommentaireManager commentaireManager = new CommentaireManager();
			for (Recette recette : recettes) {
				recette.setMoyNote(commentaireManager.getNoteMoy(recette.getIdRecette()));
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recettes;
	}

	//Fonction pour afficher tous les recettes par membres ****************************************
	@SuppressWarnings("unchecked")
	public List<Recette> getAllByIdMembre(int id) {
		Transaction transaction = null;
		List < Recette > recettes = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			recettes = session.createQuery("FROM Recette R where R.membre ="+id).getResultList();
			//On recupere la moyenne de note pour la recette 
			CommentaireManager commentaireManager = new CommentaireManager();
			for (Recette recette : recettes) {
				recette.setMoyNote(commentaireManager.getNoteMoy(recette.getIdRecette()));
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recettes;
	}

	//Fonction pour update une recette dans la base de donnée*****************************
	public void updateRecette(Recette recette) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.update(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}


	//Fonction pour supprimer une recette dans la base de donnée******************************
	public void deleteRecette(Recette recette) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Fin de la class RecetteManager
}
