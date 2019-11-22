package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Tag;
import com.blogrecette.utils.HibernateUtil;

public class TagManager {
	
	//Requete pour créer un tag ***********************************
	public Tag creatTag(Tag tag) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.save(tag);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tag;
	}

	//Fonction pour afficher tous les tags ****************************************
	@SuppressWarnings("unchecked")
	public List<Tag> getAll() {
		Transaction transaction = null;
		List < Tag > tags = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			tags = session.createQuery("from Tag").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tags;

	}

	//Fonction pour afficher une tag par rapport a son ID***********************
	public Tag gettagById(int id) {

		Transaction transaction = null;
		Tag tag = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			//Recupere dans la BDD
			tag = session.get(Tag.class, id);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tag;

	}
	
	//Fonction pour afficher tous les tags par recettes ****************************************
	@SuppressWarnings("unchecked")
	public List<Tag> getAllByIdRecette(int id) throws Exception {
		Transaction transaction = null;
		List < Tag > tags = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			tags = session.createQuery("SELECT t from Tag t join t.recettes r WHERE r.id =:id").setParameter("id", id).getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tags;
	}
	
	//Fonction pour supprimer une tag dans la base de donnée******************************
	public void deleteTag(Tag tag) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.delete(tag);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
