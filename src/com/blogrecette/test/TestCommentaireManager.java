package com.blogrecette.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.pojos.Commentaire;
import com.blogrecette.services.CommentaireManager;


import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCommentaireManager extends TestCase{
	private static CommentaireManager commentaireManager = null;
	private static Commentaire commentaireTest = null;

	public TestCommentaireManager(String testName) {
		// TODO Auto-generated constructor stub
		super(testName);
	}
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		////connection = DataConnect.getConnection();
		commentaireManager = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	//TEST CREATE Commentaire ****************
	public void testCreateCommentaire() throws SQLException {
		//Créer un jeu de test (Arrange)

		//Action (act)
		//On cree l'commentaire test avec l'objet commentaireTest
		commentaireTest = commentaireManager.creatCommentaire(commentaireTest);

		//Recupere l'objet commentaireTest par son id
		Commentaire commentaireCree = commentaireManager.getCommentaireFromId(commentaireTest.getIdCommentaire());

		//Assert test si l'id du commentaire n'est pas egal a 0
		assertNotEquals(0, commentaireTest.getIdCommentaire());

		//Assert test si commentaire test est egal a celui recupere dans la BDD (commentairecree)
		//System.out.println(commentaireTest.getIdRecette());
		//System.out.println(commentaireCree.getIdRecette());
		//assertEquals(commentaireTest.getIdRecette(), commentaireCree.getIdRecette());
		assertEquals(commentaireTest.getAuteur(), commentaireCree.getAuteur());
		assertEquals(commentaireTest.getContenu(), commentaireCree.getContenu());
		assertEquals(commentaireTest.getNote(), commentaireCree.getNote());
		assertEquals(commentaireTest.getDateCreation().getDate(), commentaireCree.getDateCreation().getDate());

	}

	//TEST UPDATE Commentaire *************
	public void testUdateCommentaire() throws SQLException {

	}
	//TEST DELETE Commentaire ****************
	public void testDeleteCommentaire() throws SQLException {

	}
	//TEST MOYENNES NOTES Commentaire ****************
	public void testgetNoteMoy() throws SQLException {
		
		//Recupere la moyenne des notes
		//int noteMoy = commentaireManager.getNoteMoy(1);
		
		//Assert test si la moyenne n'est pas egal a 0
		//assertNotEquals(0, noteMoy);
		
	}
	
	//Lancement des tests en cascade
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestCommentaireManager");

		suite.addTest(new TestCommentaireManager("testCreateCommentaire"));
		//suite.addTest(new TestCommentaireManager("testgetNoteMoy"));
		//suite.addTest(new TestCommentaireManager("testDeleteCommentaire"));
		return suite;	
	}
	
	//Fin de la class Test Commentaire Manager
}

