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

import com.blogrecette.pojos.Membre;
import com.blogrecette.services.MembreManager;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestMembreService extends TestCase {

	private static MembreManager membreManager = null;
	private static Membre membreTest = null;

public TestMembreService(String testName) {
	// TODO Auto-generated constructor stub
	super(testName);
}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		
		membreManager = new MembreManager();
		if(membreTest==null) {
			membreTest = new Membre("nomjunit","pseudojunit","junit@toto.com","passwordjunit", new Date());
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		////connection = DataConnect.getConnection();
		membreManager = null;
	}

	@SuppressWarnings("deprecation")
	@Test 
	//TEST CREATE MEMBRE ****************
	public void testCreateMembre() throws SQLException {
		//Créer un jeu de test (Arrange)

		//Action (act)
		membreTest = membreManager.creatMembre(membreTest);

		//Recupere un membre par son id
		Membre membreCree = membreManager.getMembreFromId(membreTest.getIdMembre());

		//Assert test si l'id du membre n'est pas egal a 0
		assertNotEquals(0, membreTest.getIdMembre());
		
		//Assert test si membre test crée est egal a celui recupere dans la BDD (membrecree)
		assertEquals(membreTest.getNom(), membreCree.getNom());
		assertEquals(membreTest.getPseudo(), membreCree.getPseudo());
		assertEquals(membreTest.getMdp(), membreCree.getMdp());
		assertEquals(membreTest.getEmail(), membreCree.getEmail());
		assertEquals(membreTest.getDateInscription().getDate(), membreCree.getDateInscription().getDate());

	}
	//TEST UPDATE MEMBRE *************
	@SuppressWarnings("deprecation")
	public void testUdateMembre() throws SQLException {
		//Créer un jeu de test (Arrange)
		Date newDate = new Date("2019/11/04");
		
		membreTest.setNom("updatenom");
		membreTest.setPseudo("updatepseudo");
		membreTest.setMdp("updatemdp");
		membreTest.setEmail("update@toto.com");
		membreTest.setDateInscription(newDate);
		
		//Action (act)

		membreManager.updateMembre(membreTest);
		Membre membreUpdate = membreManager.getMembreFromId(membreTest.getIdMembre());
	
		//Assert test si membre test crée est egal a celui recupere dans la BDD (membrecree)
		assertEquals(membreUpdate.getNom(), membreTest.getNom());
		assertEquals(membreUpdate.getPseudo(), membreTest.getPseudo());
		assertEquals(membreUpdate.getMdp(), membreTest.getMdp());
		assertEquals(membreUpdate.getEmail(), membreTest.getEmail());
		assertEquals(membreUpdate.getDateInscription().getDate(), membreTest.getDateInscription().getDate());
	}
	//TEST DELETE MEMBRE ****************
	public void testDeleteMembre() throws SQLException {
		membreManager.deleteMembre(membreTest);
		membreTest = membreManager.getMembreFromId(membreTest.getIdMembre());
		
		assertNull(membreTest);
	}
	//Lancement des tests en cascade
	public static junit.framework.Test suite() {
		
		TestSuite suite = new TestSuite("Suite TestMembreService");
		
		suite.addTest(new TestMembreService("testCreateMembre"));
		suite.addTest(new TestMembreService("testUdateMembre"));
		suite.addTest(new TestMembreService("testDeleteMembre"));
		return suite;
		
		
		
	}

}
