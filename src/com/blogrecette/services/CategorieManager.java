/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Categorie;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class CategorieManager {

	//Requete pour créer une categorie ***********************************
	public Categorie creatCategorie(Categorie categorie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.save(categorie);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}

	//Fonction pour afficher tous les categories ****************************************
	@SuppressWarnings("unchecked")
	public List<Categorie> getAll() {
		Transaction transaction = null;
		List < Categorie > categories = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			categories = session.createQuery("from Categorie").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categories;

	}

	//Fonction pour afficher une categorie par rapport a son ID***********************
	public Categorie getcategorieById(int id) {

		Transaction transaction = null;
		Categorie categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			//Recupere dans la BDD
			categorie = session.get(Categorie.class, id);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;

	}

	//Fonction pour supprimer une categorie dans la base de donnée******************************
	public void deleteCategorie(Categorie categorie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.delete(categorie);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//Fin class categorie manager
}
