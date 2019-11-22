/**
 * 
 */
package com.blogrecette.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.services.CategorieManager;
import com.blogrecette.pojos.Categorie;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestCategorieManager  extends TestCase {
	private static CategorieManager categorieManager = null;
	private static Categorie categorieTest = null;
	
	public TestCategorieManager(String testName) {
		// TODO Auto-generated constructor stub
		super(testName);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		////connection = DataConnect.getConnection();
		categorieManager = new CategorieManager();
		if(categorieTest==null) {
			categorieTest = new Categorie("Boissons");
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		////connection = DataConnect.getConnection();
		categorieManager = null;
	}

	@Test
	//TEST CREATE Categorie ****************
	public void testCreateCategorie() throws SQLException {
		//Créer un jeu de test (Arrange)

		//Action (act)
		//On cree l'categorie test avec l'objet categorieTest
		categorieTest = categorieManager.creatCategorie(categorieTest);

		//Recupere l'objet categorieTest par son id
		//Categorie categorieCree = categorieManager.getCategorieById(categorieTest.getIdCategorie());

		//Assert test si l'id du categorie n'est pas egal a 0
		assertNotEquals(0, categorieTest.getIdCategorie());

		//Assert test si categorie test est egal a celui recupere dans la BDD (categoriecree)
		//assertEquals(categorieTest.getIdCategorie(), categorieCree.getIdCategorie());
		//assertEquals(categorieTest.getNom(), categorieCree.getNom());
	}
	
	//TEST Categorie GetByID  ****************
	public void testGetByIDCategorie() throws SQLException {
		//Créer un jeu de test (Arrange)
		
		//On cree une cat dans la bdd categorie avec l'objet categorieTest
		categorieTest = categorieManager.creatCategorie(categorieTest);
		
		//Recupere l'objet categorieById par son id
		//Categorie categorieById = categorieManager.getCategorieById(categorieTest.getIdCategorie());
		
		//Assert test si l'id du commentaire n'est pas egal a 0
		assertNotEquals(0, categorieTest.getIdCategorie());
		
		//Assert test si categorie test est egal a celui recupere dans la BDD (categoriecree)
		//assertEquals(categorieTest.getIdCategorie(), categorieById.getIdCategorie());
		//assertEquals(categorieTest.getNom(), categorieById.getNom());
		
	}

	//Lancement des tests en cascade
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestCategorieManager");

		suite.addTest(new TestCategorieManager("testCreateCategorie"));
		suite.addTest(new TestCategorieManager("testGetByIDCategorie"));
		return suite;	
	}
	
	//Fin de la class Test Categorie Manager
}

