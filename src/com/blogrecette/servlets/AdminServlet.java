package com.blogrecette.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.pojos.Categorie;
import com.blogrecette.pojos.Recette;
import com.blogrecette.pojos.Tag;
import com.blogrecette.services.CategorieManager;
import com.blogrecette.services.RecetteManager;
import com.blogrecette.services.TagManager;

/**
 * Servlet implementation class AdminServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="admin",urlPatterns= {"/admin"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Creation de la session
		HttpSession session = request.getSession();

		//On test si session membre exist
		if (session.getAttribute("membre") != null) {
			//Membre loger
			//************************************CATEGORIES***********************************************

			//Si idCategorie existe alors traitement
			if(request.getParameter("idCategorie")!=null) {
				//*******************************DELETE************************************
				//On recupere l'idrecette et on le parst string to int
				int idCategorie=Integer.parseInt(request.getParameter("idCategorie"));

				//Creation de l'objets  CategorieManager
				CategorieManager categorieManager = new CategorieManager();

				//On recupere l'objet categorie by ID
				Categorie categorie = categorieManager.getcategorieById(idCategorie);

				//On recupere l'objet categorie par defaut by ID
				Categorie newcategorie = categorieManager.getcategorieById(7);

				//On cherche les recettes appartenant a cette categorie

				//Creation de l'objets  RecetteManager
				RecetteManager recetteManager = new RecetteManager();

				//Crée un variable list recette avec les informations de la fonction Getallbyidcat
				List<Recette> recettes = null;
				recettes = recetteManager.getAllByIdCat(idCategorie);

				for (Recette recette : recettes) {

					//UPDATE On met a jour la categorie dans l'objet recette
					recette.setCategorie(newcategorie);
					recetteManager.updateRecette(recette);
				}

				//DELETE On supprime la categorie
				categorieManager.deleteCategorie(categorie);
			}
			//************************************TAG***********************************************

			//Si idTag existe alors traitement
			if(request.getParameter("idTag")!=null) {
				//*************************************DELETE************************************
				//On recupere l'idtag et on le parst string to int
				int idTag=Integer.parseInt(request.getParameter("idTag"));

				//Creation de l'objets  TagManager
				TagManager tagManager = new TagManager();

				//On recupere l'objet tag by ID
				Tag tag = tagManager.gettagById(idTag);

				//DELETE On supprime la tag
				tagManager.deleteTag(tag);
			}
			//************************************AFFICHAGE************************************
			//************************************CATEGORIES***********************************************

			//Creation de l'objets  Categoriemanager pour faire la connexion a la base
			CategorieManager categorieManager = new CategorieManager();

			//Crée un variable list categories avec les informations de la fonction Getall
			List<Categorie> categories = null;
			categories = categorieManager.getAll();

			//On envoi le resulat des requettes a la jsp 
			request.setAttribute("categories", categories);

			//************************************TAG***********************************************

			//Creation de l'objets Tagmanager
			TagManager tagManager = new TagManager();

			//Crée un variable list tags avec les informations de la fonction Getall
			List<Tag> tags = null;
			tags = tagManager.getAll();

			//On envoi le resulat des requettes a la jsp 
			request.setAttribute("tags", tags);

			//On affiche la jsp
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Creation de la session
		HttpSession session = request.getSession();
		//On test si session membre exist
		if (session.getAttribute("membre") != null) {
			//Membre loger

			//************************************CATEGORIES***********************************************			
			String categoriename = request.getParameter( "categoriename" );
			if (request.getParameter("savecat") != null) {
				if ( categoriename.isEmpty() ) {
					//Envoi de la variable erreur a la jsp
					request.setAttribute("erreurcat", "Merci de donner un nom à votre catégorie.");
					//Execute la doget pour que la recette s'affiche
				} else {
					//Creation de l'objets  CategorieManager
					CategorieManager categorieManager = new CategorieManager();

					//Creation d'un objet ingredient depuis le formulaire
					Categorie categorie = new Categorie(categoriename);

					//Creation de la categorie dans la BDD
					categorieManager.creatCategorie(categorie);
				}
			}
			//************************************TAG***********************************************			
			String tagname = request.getParameter( "tagname" );
			if (request.getParameter("savetag") != null) {
				if ( tagname.isEmpty() ) {
					//Envoi de la variable erreur a la jsp
					request.setAttribute("erreurtag", "Merci de donner un nom à votre tag.");
					//Execute la doget pour que la recette s'affiche
				} else {
					//Creation de l'objets  TagManager
					TagManager tagManager = new TagManager();

					//Creation d'un objet ingredient depuis le formulaire
					Tag tag = new Tag(tagname);

					//Creation de la tag dans la BDD
					tagManager.creatTag(tag);
				}		
			}		
			//On reload le DOGet pour affichier les categories et tags
			this.doGet(request, response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);

		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}		
	}

}
