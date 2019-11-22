/**
 * 
 */
package com.blogrecette.services;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Commentaire;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class CommentaireManager {

	//Fonction pour créer un commentaire ***********************************
	public Commentaire creatCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
				session.save(commentaire);
				session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}

	//Fonction pour afficher tous les commentaires par recettes ****************************************
	@SuppressWarnings("unchecked")
	public List<Commentaire> getAllByIdRecette(int id) throws SQLException {
		Transaction transaction = null;
		List < Commentaire > commentaires = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			commentaires = session.createQuery("FROM Commentaire C where C.recette ="+id).getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaires;
	}

	//Fonction pour afficher un commentaire par rapport a son ID***********************
	public Commentaire getCommentaireFromId(int id) throws SQLException {
		Transaction transaction = null;
		Commentaire commentaire = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			commentaire = session.get(Commentaire.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;

	}

	//Fonction pour afficher moyenne des notes des commentaires par rapport a l'ID de la recette ***********************
	public Double getNoteMoy(int id) throws Exception {

		//On initialise la variable notemoy
		Double notemoy = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Query query = session.createQuery("SELECT avg(C.note) as notemoy FROM Commentaire C JOIN C.recette recette where recette.id = :id");
			query.setParameter("id", id);
			notemoy = (Double) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notemoy;
	}
	//fin de la class commentaire manager
}
