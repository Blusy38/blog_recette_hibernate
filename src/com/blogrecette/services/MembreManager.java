/**
 * 
 */
package com.blogrecette.services;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blogrecette.pojos.Membre;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class MembreManager {

	protected Session session;
	protected SessionFactory sessionFactory;

	//Fonction pour créer un membre ***********************************
	public Membre creatMembre(Membre membre) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.save(membre);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return membre;
	}

	//Fonction pour update un membre dans la base de donnée*****************************
	public void updateMembre(Membre membre) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.update(membre);
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
	//Fonction pour supprimer un membre dans la base de donnée******************************
	public void deleteMembre(Membre membre) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.delete(membre);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Fonction pour afficher un membre par rapport a son ID***********************
	public Membre getMembreFromId(int id) throws SQLException {
		Transaction transaction = null;
		Membre membre = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			membre = session.get(Membre.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return membre;

	}


	//Fonction pour afficher un membre par rapport a son Mail***********************
	public Membre getMembreFromMail(String email) throws SQLException {
		Transaction transaction = null;
		Membre membre = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Query<?> query = session.createQuery("FROM Membre M where M.email =:email").setParameter("email", email);
			List<?> list = query.getResultList();
			membre = (Membre) list.toArray()[0];

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return membre;

	}

	//Fin de la class membre manager
}
