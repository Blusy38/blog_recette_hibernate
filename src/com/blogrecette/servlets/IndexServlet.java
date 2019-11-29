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
import com.blogrecette.services.CategorieManager;
import com.blogrecette.services.RecetteManager;


/**
 * Servlet implementation class IndexServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="index",urlPatterns= {"", "/index"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//suppression de la session si lien logout
		String logout=request.getParameter("logout");   
		if(logout!=null && logout.equals("logout")){ 
			HttpSession Session=request.getSession();
			Session.invalidate();
		}

		//****************CATEGORIES***********************************************
		//Creation de l'objets  Categoriemanager pour faire la connexion a la base
		CategorieManager categorieManager = new CategorieManager();

		//Crée un variable list categories avec les informations de la fonction Getall
		List<Categorie> categories = null;
		categories = categorieManager.getAll();
		request.setAttribute("categories", categories);

		//************************RECETTES*******************************************
		//Creation de l'objets  recetteManager pour faire la connexion a la base
		RecetteManager recetteManager = new RecetteManager();

		//On recupere les variables en string
		String idCategorie=request.getParameter("idCategorie");  
		String idMembre=request.getParameter("idMembre"); 

		//On crée l'objet liste recettes
		List<Recette> recettes = null;

		//On verifi si il y a un categorie demandé via idCategorie 
		if(idCategorie ==null  && idMembre==null){ 

			//On recupere toutes les recettes
			recettes = recetteManager.getAll();
			
			if(recettes.isEmpty() ) {
				request.setAttribute("msgEmpty", "<h1>Soyez le premier à ajouter une recette !</h1>");
			}
		} else if (idCategorie!=null){

			int idCat=Integer.parseInt(request.getParameter("idCategorie")); 

			//On recupere toutes les recettes par categorie
			recettes = recetteManager.getAllByIdCat(idCat);
			
			//Si il n'y a pas de resulat alors on affiche un message.
			if(recettes.isEmpty() ) {
				request.setAttribute("msgEmpty", "<h1>Oups! Catégorie vide.</h1> Pourquoi ne pas ajouter une recette dés maintenant ?");
			}
		} else if (idMembre!=null) {

			int idMem=Integer.parseInt(request.getParameter("idMembre")); 

			//On recupere toutes les recettes par membres
			recettes = recetteManager.getAllByIdMembre(idMem);
			
			//Si il n'y a pas de resulat alors on affiche un message.
			if(recettes.isEmpty() ) {
				request.setAttribute("msgEmpty", "<h1>Vous n'avez pas encore créé de recette !</h1>Pourquoi ne pas commencer maintenant ?");
			}
		}
		//On envoi le resulat des requettes a la jsp 
		request.setAttribute("recettes", recettes);
		
		//On affiche la jsp
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		//code toujours avant le forward !!! apres ce n'est pas interprété
	}		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Rien dans le dopost car il n'y a pas d'information envoyé a la jsp

	}

}
