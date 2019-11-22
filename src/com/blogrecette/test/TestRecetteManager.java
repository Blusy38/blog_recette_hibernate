/**
 * 
 */
package com.blogrecette.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.services.RecetteManager;
import com.blogrecette.pojos.Recette;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestRecetteManager  extends TestCase {
	private static RecetteManager recetteManager = null;
	private static Recette recetteTest = null;
	
	public TestRecetteManager(String testName) {
		// TODO Auto-generated constructor stub
		super(testName);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		recetteManager = new RecetteManager();
		if(recetteTest==null) {
			//recetteTest = new Recette("testcreat", "testcreat savoyarde est un gratin de pommes de terre avec du Reblochon fondu dessus", "tartiflette.jpg", new Date(),0);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		recetteManager = null;
	}

	@Test
	//TEST CREATE Recette ****************
	public void testCreateRecette() throws SQLException {
		//Créer un jeu de test (Arrange)

		//Action (act)
		//On cree l'recette test avec l'objet recetteTest
		recetteTest = recetteManager.creatRecette(recetteTest);

		//Recupere l'objet recetteTest par son id
		Recette recetteCree = recetteManager.getRecetteFromId(recetteTest.getIdRecette());

		//Assert test si l'id du recette n'est pas egal a 0
		assertNotEquals(0, recetteTest.getIdRecette());

		//Assert test si recette test est egal a celui recupere dans la BDD (recettecree)
		assertEquals(recetteTest.getIdRecette(), recetteCree.getIdRecette());
		assertEquals(recetteTest.getTitre(), recetteCree.getTitre());
	}
	
	//TEST Recette GetByID  ****************
	public void testGetByIDRecette() throws SQLException {
		//Créer un jeu de test (Arrange)
		
		//On cree une cat dans la bdd recette avec l'objet recetteTest
		recetteTest = recetteManager.creatRecette(recetteTest);
		
		//Recupere l'objet recetteById par son id
		Recette recetteById = recetteManager.getRecetteFromId(recetteTest.getIdRecette());
		
		//Assert test si l'id du commentaire n'est pas egal a 0
		assertNotEquals(0, recetteTest.getIdRecette());
		
		//Assert test si recette test est egal a celui recupere dans la BDD (recettecree)
		assertEquals(recetteTest.getIdRecette(), recetteById.getIdRecette());
		assertEquals(recetteTest.getTitre(), recetteById.getTitre());
		
	}

	//Lancement des tests en cascade
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestRecetteManager");

		suite.addTest(new TestRecetteManager("testCreateRecette"));
		//suite.addTest(new TestRecetteManager("testGetByIDRecette"));
		return suite;	
	}
	
	//Fin de la class Test Recette Manager
}

