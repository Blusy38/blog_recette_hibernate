package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.pojos.Ingredient;
import com.blogrecette.pojos.Recette;
import com.blogrecette.services.IngredientManager;
import com.blogrecette.services.RecetteManager;



/**
 * Servlet implementation class EditIngredientServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="editingredient",urlPatterns= {"/editingredient"})
public class EditIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditIngredientServlet() {
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
			session.removeAttribute("ingredients");

			//Creation de l'objets IngredientManager pour faire la connexion a la base
			IngredientManager ingredientManager = new IngredientManager();

			if (request.getParameter("id") != null) {
				//************************AFFICHAGE POUR MODIFICATION INGREDIENT***************************

				//On recuperer l'id de l'ingredient et on le parst string to int
				int id=Integer.parseInt(request.getParameter("id")); 

				// On cree l'objet ingredient by ID	
				Ingredient ingredient = ingredientManager.getIngredientFromId(id);

				//On envoi les info de l'ingredient a la session
				session.setAttribute("ingredient", ingredient);

				//Permet d'afficher la page jps
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editingredient.jsp?id="+id).forward(request, response);
			} else {
				//*************************AFFICHAGE POUR CREATE INGREDIENT********************************

				//Permet d'afficher la page jps
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editingredient.jsp").forward(request, response);

			}
		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Recuperation de la session
		HttpSession session = request.getSession();

		//On test si session membre exist
		if (session.getAttribute("membre") != null) {

			//Intialisation des variables
			String erreur = "";
			int quantite = 0;

			/* Récupération et test des champs du formulaire. */	
			String unite = request.getParameter( "unite" );

			String titre = request.getParameter( "titre" );
			if ( titre.isEmpty() ) {
				erreur += "<li>Merci de donner un titre à votre ingredient.</li>";
			}

			String quantitee = request.getParameter( "quantitee" );
			if ( quantitee.isEmpty() ) {
				erreur += "<li>Il n'y a pas de quantitee !</li>";
			} else {
				//On recupere la quantite du formulaire et on le parse de string a int
				quantite=Integer.parseInt(request.getParameter("quantitee")); 
			}

			//Si erreur et id existe alors traitement erreur MODIF INGREDIENT
			if(erreur!="" && request.getParameter("id")!=null) {

				//Envoi de la variable erreur a la jsp
				request.setAttribute("erreur", erreur);

				/* Transmission de la paire d'objets request/response à notre JSP */
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editingredient.jsp?id="+request.getParameter("id")).forward(request, response);

				//Si erreur et idRecette existe alors traitement erreur CREATE INGREDIENT
			} else if(erreur!="" && request.getParameter("idrecette")!=null) {

				//Envoi de la variable erreur a la jsp
				request.setAttribute("erreur", erreur); 

				//On crée l'objet Member avec les information du formulaire
				Ingredient ingredient = new Ingredient(titre, quantite, unite);

				//Creation de la session
				session.setAttribute("ingredient", ingredient);

				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editingredient.jsp?idrecette="+request.getParameter("idrecette")).forward(request, response);
			} else {

				//Creation de l'objets  ingredientManager
				IngredientManager ingredientManager = new IngredientManager();

				if (request.getParameter("id") != null) {

					//*******************TRAITEMENT POUR MODIFICATION ou SUPPRESSION INGREDIENT************

					//Creation d'un objet ingredient depuis les attributs de la session
					Ingredient ingredient = (Ingredient) session.getAttribute("ingredient");

					if (request.getParameter("delete") != null) {

						//DELETE On supprime l'ingredient
						ingredientManager.deleteIngredient(ingredient);

					} else if (request.getParameter("update") != null) {

						//UPDATE On met a jour des champs utiles dans l'objet ingredient
						ingredient.setNom(titre);
						ingredient.setQuantite(quantite);
						ingredient.setUnit(unite);

						//update de la ingredient dans la BDD
						ingredientManager.updateIngredient(ingredient);		
					}

					int idRecette = ingredient.getRecette().getIdRecette();

					//On renvoi vers la page edition recette 
					response.sendRedirect("editrecette?id="+idRecette);
					
				} else {
					//*************************TRAITEMENT POUR CREATE INGREDIENT********************************
					//On recupere l'idrecette et on le parst string to int
					int idRecette=Integer.parseInt(request.getParameter("idrecette"));

					//On crée l'objet ingredient avec les information du formulaire
					Ingredient ingredient = new Ingredient(titre, quantite, unite);

					//Creation de l'objets  RecetteManager
					RecetteManager recetteManager = new RecetteManager();

					//On recupere l'objet recette by ID
					Recette recette = recetteManager.getRecetteFromId(idRecette);

					//On ajoute la recette au commentaire
					ingredient.setRecette(recette);

					try {
						ingredientManager.creatIngredient(ingredient);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Kill la session ingredient avant la redirection
					session.removeAttribute("ingredients");
					
					//On renvoi vers la page edition recette 
					response.sendRedirect("editrecette?id="+idRecette);
				}
			}
		} else {
			//Pas de session membre on redirige vers l'index
			response.sendRedirect("index");
		}
	}

	//fin de la classe edit recette
}
