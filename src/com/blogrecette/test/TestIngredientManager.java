package com.blogrecette.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.pojos.Ingredient;
import com.blogrecette.services.IngredientManager;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestIngredientManager extends TestCase{

	private static IngredientManager ingredientManager = null;
	private static Ingredient ingredientTest = null;

	public TestIngredientManager(String testName) {
		// TODO Auto-generated constructor stub
		super(testName);
	}
	@Before
	public void setUp() throws Exception {

		////connection = DataConnect.getConnection();
		ingredientManager = new IngredientManager();
		if(ingredientTest==null) {
			ingredientTest = new Ingredient("nomjunit",250,"gr");
		}

	}

	@After
	public void tearDown() throws Exception {
		////connection = DataConnect.getConnection();
		ingredientManager = null;
	}

	@Test
	//TEST CREATE Ingredient ****************
	public void testCreateIngredient() throws SQLException {
		//Créer un jeu de test (Arrange)

		//Action (act)
		//On cree l'ingredient test avec l'objet ingredientTest
		ingredientTest = ingredientManager.creatIngredient(ingredientTest);

		//Recupere l'objet ingredientTest par son id
		Ingredient ingredientCree = ingredientManager.getIngredientFromId(ingredientTest.getIdIngredient());

		//Assert test si l'id du ingredient n'est pas egal a 0
		assertNotEquals(0, ingredientTest.getIdIngredient());

		//Assert test si ingredient test est egal a celui recupere dans la BDD (ingredientcree)
		//System.out.println(ingredientTest.getIdRecette());
		//System.out.println(ingredientCree.getIdRecette());
		//assertEquals(ingredientTest.getIdRecette(), ingredientCree.getIdRecette());
		assertEquals(ingredientTest.getNom(), ingredientCree.getNom());
		assertEquals(ingredientTest.getQuantite(), ingredientCree.getQuantite());
		assertEquals(ingredientTest.getUnit(), ingredientCree.getUnit());
	}

	//TEST UPDATE Ingredient *************
	public void testUdateIngredient() throws SQLException {
		//Créer un jeu de test (Arrange)		
		//ingredientTest.setIdRecette(2);
		ingredientTest.setNom("nomupdatejunit");
		ingredientTest.setQuantite(500);
		ingredientTest.setUnit("Kg");

		//Action (act)

		ingredientManager.updateIngredient(ingredientTest);
		Ingredient ingredientUpdate = ingredientManager.getIngredientFromId(ingredientTest.getIdIngredient());

		//Assert test si ingredient test crée est egal a celui recupere dans la BDD (ingredientcree)			
		//assertEquals(ingredientTest.getIdRecette(), ingredientUpdate.getIdRecette());
		assertEquals(ingredientTest.getNom(), ingredientUpdate.getNom());
		assertEquals(ingredientTest.getQuantite(), ingredientUpdate.getQuantite());
		assertEquals(ingredientTest.getUnit(), ingredientUpdate.getUnit());
	}
	//TEST DELETE Ingredient ****************
	public void testDeleteIngredient() throws SQLException {
		ingredientManager.deleteIngredient(ingredientTest);
		ingredientTest = ingredientManager.getIngredientFromId(ingredientTest.getIdIngredient());
		
		assertNull(ingredientTest);
	}
	//TEST Ingredient by RecetteServlet ID****************
	public void testIngredientByIdRecette() throws SQLException {
		
	}
	
	//Lancement des tests en cascade
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestIngredientManager");

		suite.addTest(new TestIngredientManager("testCreateIngredient"));
		suite.addTest(new TestIngredientManager("testUdateIngredient"));
		suite.addTest(new TestIngredientManager("testDeleteIngredient"));
		suite.addTest(new TestIngredientManager("testIngredientByIdRecette"));
		return suite;	
	}
	
	//Fin de la class Test Ingredient Manager
}

