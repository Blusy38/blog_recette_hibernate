package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.pojos.Categorie;
import com.blogrecette.pojos.Ingredient;
import com.blogrecette.pojos.Membre;
import com.blogrecette.pojos.Recette;
import com.blogrecette.pojos.Tag;
import com.blogrecette.services.CategorieManager;
import com.blogrecette.services.IngredientManager;
import com.blogrecette.services.RecetteManager;
import com.blogrecette.services.TagManager;
import com.blogrecette.utils.HibernateUtil;


/**
 * Servlet implementation class EditRecetteServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="editrecette",urlPatterns= {"/editrecette"})
public class EditRecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditRecetteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Creation de la session
		HttpSession session = request.getSession();

		//On test si session membre exist
		if (session.getAttribute("membre") != null) {

			//Kill la session ingredient avant la redirection
			session.removeAttribute("recette");
			session.removeAttribute("ingredients");
			//On recuperer l'id de la recette et on le parst string to int
			int id=Integer.parseInt(request.getParameter("id")); 

			if (id != 0) {
				//**********************************AFFICHAGE POUR MODIFICATION RECETTE***************************
				//**************************************RECETTE********************************

				//Creation de l'objets RecetteManager
				RecetteManager recetteManager = new RecetteManager();

				// On cree l'objet recette by ID	
				Recette recette = recetteManager.getRecetteFromId(id);



				//*********************************INGREDIENT****************************************

				//Creation de l'objets Ingredientsmanager
				IngredientManager ingredientManager = new IngredientManager();

				// On cree la liste objet ingredients by ID	
				List<Ingredient> ingredients;
				try {
					ingredients = ingredientManager.getAllByIdRecette(id);

					//On envoi les info des ingredients a la JSP
					session.setAttribute("ingredients", ingredients);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//************************************TAG***********************************************

				//Creation de l'objets Tagmanager
				TagManager tagManager = new TagManager();

				//Crée un variable list tags avec les informations de la fonction Getall
				List<Tag> tags = null;
				tags = tagManager.getAll();

				//On envoi le resulat des requettes a la jsp 
				session.setAttribute("tags", tags);
				//On envoi les info de la recette a la JSP
				session.setAttribute("recette", recette);
			} 
			//******************************AFFICHAGE POUR CREATION RECETTE***************************

			//***********************************CATEGORIE********************************

			//Creation de l'objets Ingredientsmanager
			CategorieManager categorieManager = new CategorieManager();

			// On cree la liste objet categories by ID	
			List<Categorie> categories;
			categories = categorieManager.getAll();

			//On envoi les info des categories a la JSP
			session.setAttribute("categories", categories);

			//************************************TAG***********************************************

			//Creation de l'objets Tagmanager
			TagManager tagManager = new TagManager();

			//Crée un variable list tags avec les informations de la fonction Getall
			List<Tag> tags = null;
			tags = tagManager.getAll();

			//On envoi le resulat des requettes a la jsp 
			session.setAttribute("tags", tags);

			//Permet d'afficher la page jps
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editrecette.jsp?id="+id).forward(request, response);
		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Creation de la session
		HttpSession session = request.getSession();
		//On test si session membre exist
		if (session.getAttribute("membre") != null) {

			//Intialisation des variables
			String erreur = "";

			//On recuperer l'id de la recette et on le parst string to int
			int id=Integer.parseInt(request.getParameter("id")); 

			/* Récupération et test des champs du formulaire. */	
			String titre = request.getParameter( "titre" );
			if ( titre.isEmpty() ) {
				erreur += "<li>Merci de donner un titre à votre recette.</li>";
			}

			int idCategorie=Integer.parseInt(request.getParameter("categorie")); 
			if ( idCategorie==0 ) {
				erreur += "<li>Veuillez choisir une categorie !</li>";
			}

			String photo = request.getParameter( "photo" );
			if (photo.isEmpty()) {
				erreur += "<li>Veuillez choisir un image !</li>";
			}

			String description = request.getParameter( "description" );
			if ( description.isEmpty() ) {
				erreur += "<li>Il n'y a pas de description !</li>";
			}

			//Creation de l'objets  recetteManager
			RecetteManager recetteManager = new RecetteManager();

			//On crée l'objet Recette avec les information du formulaire
			Recette recette = new Recette(titre, description, photo, new Date(),null) ;

			//Si la variable erreur est differente de vide alors on affiche la page JSP
			if(erreur!="") {

				//*******************************GESTION ERREURS**************************************

				//Envoi de la variable erreur a la jsp
				request.setAttribute("erreur", erreur);

				//Creation de la session
				session.setAttribute("recette", recette);

				/* Transmission de la paire d'objets request/response à notre JSP */
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editrecette.jsp?id="+id).forward(request, response);

			} else {

				//On test le nom du bouton pour savoir si on fait une inscription
				if (request.getParameter("Ajouter") != null) {

					//*******************************AJOUT DE RECETTE**************************************

					//***********************************CATEGORIE********************************

					//Creation de l'objets CategorieManager
					CategorieManager categorieManager = new CategorieManager();

					//On recupere l'objet categorie by idCategorie
					Categorie categorie = categorieManager.getcategorieById(idCategorie);

					//On ajoute la categorie a la recette
					recette.setCategorie(categorie);

					//***********************************MEMBRE********************************					

					//On recupere le membre depuis la session on le cast de objet to membre
					Membre membre = (Membre) session.getAttribute("membre");

					//On ajoute le membre a la recette
					recette.setMembre(membre);

					//***********************************RECETTE********************************		

					//Creation de la recette.
					recetteManager.creatRecette(recette);

					//**************************************TAG***************************************
					//Creation de l'objets TagManager
					TagManager tagManager = new TagManager();

					//Creation de l'objets Tag
					Tag tag = new Tag();

					//On recupere les tags depuis le formulaire dans un tableau tags
					String[] tags = request.getParameterValues("tags");

					//Tant qu'il y a des tags dans le tableau
					for (int i = 0; i < tags.length; i++) {

						//On recupere l'id du tage et on le parse de string to int
						int idTag=Integer.parseInt(tags[i]);

						//On recupere l'objet tag par son ID
						tag = tagManager.gettagById(idTag);
						System.out.println("******tag a ajouter************");
						System.out.println(tag);

						//On ajoute le tag a la recette.
						recette.addTag(tag);
						Transaction transaction = null;
						try (Session session2 = HibernateUtil.getSessionFactory().openSession()) {
						transaction = session2.beginTransaction();
						session2.update(recette);
						session2.update(recette);

						session2.flush();
						transaction.commit();}
					}

					//On recupere l'id de la recette pour l'envoyer a la creation d'ingredients
					id = recette.getIdRecette();


					response.sendRedirect("editingredient?idrecette="+id);

					//On test le nom du bouton pour savoir si on fait une mise a jour				
				} else if (request.getParameter("update") != null) {

					//*******************************UPDATE DE RECETTE**************************************

					//Creation d'un objet recette depuis les attributs de la session
					recette = (Recette) session.getAttribute("recette");

					//****************CATEGORIE****************************
					//Creation de l'objets CategorieManager
					CategorieManager categorieManager = new CategorieManager();

					//On recupere l'objet categorie par son ID
					Categorie categorie = categorieManager.getcategorieById(idCategorie);

					//On ajoute la categorie a la recette.
					recette.setCategorie(categorie);

					//****************TAG****************************
					//Creation de l'objets TagManager
					TagManager tagManager = new TagManager();

					//Creation de l'objets Tag
					Tag tag = new Tag();

					//On reset les tag pour la recette
					recette.resetTag();
					if ( request.getParameterValues("tags") != null) {
					//On recupere les tags depuis le formulaire dans un tableau tags
					String[] tags = request.getParameterValues("tags");

					//Tant qu'il y a des tags dans le tableau
					for (int i = 0; i < tags.length; i++) {

						//On recupere l'id du tage et on le parse de string to int
						int idTag=Integer.parseInt(tags[i]);

						//On recupere l'objet tag par son ID
						tag = tagManager.gettagById(idTag);

						//On ajoute le tag a la recette.
						recette.addTag(tag);
					}
					}

					//**********RECETTE********************

					//UPDATE On met a jour les champs utiles dans l'objet recette
					recette.setTitre(titre);
					recette.setDescription(description);
					recette.setPhoto(photo);

					//update du recette dans la BDD
					recetteManager.updateRecette(recette);

					//On affiche la page index
					response.sendRedirect("inscription");

					//On test le nom du bouton pour savoir si on fait un delete				
				} else if (request.getParameter("delete") != null) {

					//*******************************SUPPRESSION DE RECETTE**************************************

					//Creation d'un recette depuis les attributs de la session
					recette = (Recette) session.getAttribute("recette");

					//DELETE du recette dans la BDD
					recetteManager.deleteRecette(recette);

					//On affiche la page index
					response.sendRedirect("inscription");
				}
			}
		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}
	}

	//fin de la classe edit recette
}
