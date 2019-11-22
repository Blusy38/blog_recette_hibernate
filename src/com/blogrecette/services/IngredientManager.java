/**
 * 
 */
package com.blogrecette.services;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.blogrecette.pojos.Ingredient;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class IngredientManager {

	//Fonction pour créer un ingredient ***********************************
	public Ingredient creatIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.save(ingredient);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}

	//Fonction pour afficher tous les ingredients par idRecette ****************************************
	@SuppressWarnings("unchecked")
	public List<Ingredient> getAllByIdRecette(int id) throws SQLException {
		Transaction transaction = null;
		List < Ingredient > listOfIngredient = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			listOfIngredient = session.createQuery("FROM Ingredient I where I.recette ="+id).getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfIngredient;
	}
	
	//Fonction pour afficher une ingredient par rapport a son ID***********************
	public Ingredient getIngredientFromId(int id) {

		Transaction transaction = null;
		Ingredient ingredient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ingredient = session.get(Ingredient.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;

	}
	
	//Fonction pour update une ingredient dans la base de donnée*****************************
	public void updateIngredient(Ingredient ingredient) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.update(ingredient);
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


	//Fonction pour supprimer une ingredient dans la base de donnée******************************
	public void deleteIngredient(Ingredient ingredient) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//Fin de la class ingredient manager
}
