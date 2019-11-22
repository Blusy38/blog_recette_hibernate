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

import com.blogrecette.pojos.Commentaire;
import com.blogrecette.pojos.Ingredient;
import com.blogrecette.pojos.Recette;
import com.blogrecette.pojos.Tag;
import com.blogrecette.services.CommentaireManager;
import com.blogrecette.services.IngredientManager;
import com.blogrecette.services.RecetteManager;
import com.blogrecette.services.TagManager;


/**
 * Servlet implementation class RecetteServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="recette",urlPatterns= {"/recette"})
public class RecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecetteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//On recuperer l'id de la recette et on le parst string to int
		int id=Integer.parseInt(request.getParameter("id")); 

		//*************************RECETTE********************************

		//Creation de l'objets Membremanager pour faire la connexion a la base
		RecetteManager recetteManager = new RecetteManager();

		// On cree l'objet recette by ID	
		Recette recette = null;

		recette = recetteManager.getRecetteFromId(id);


		//On envoi les info de la recette a la JSP
		request.setAttribute("recette", recette);

		//*************************INGREDIENT********************************

		//Creation de l'objets Ingredientsmanager pour faire la connexion a la base
		IngredientManager ingredientManager = new IngredientManager();

		// On cree la liste objet ingredients by ID	
		List<Ingredient> ingredients = null;
		try {
			ingredients = ingredientManager.getAllByIdRecette(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//On envoi les info des ingredients a la JSP
		request.setAttribute("ingredients", ingredients);

		//***************************COMMENTAIRE******************************

		//Creation de l'objets CommentaireManager pour faire la connexion a la base
		CommentaireManager commentaireManager = new CommentaireManager();

		// On cree la liste objet ingredients by ID	
		List<Commentaire> commentaires = null;
		try {
			commentaires = commentaireManager.getAllByIdRecette(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//On envoi les info des commentaires a la JSP
		request.setAttribute("commentaires", commentaires);

		//***************************TAG******************************

		//Creation de l'objets TagManager
		TagManager tagManager = new TagManager();

		// On cree la liste objet ingredients by ID	
		List<Tag> tags = null;
		
			try {
				tags = tagManager.getAllByIdRecette(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//On envoi les info des tags a la JSP
		request.setAttribute("tags", tags);
		
		
		//Permet d'afficher la page jps
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/recette.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Creation de la session
		HttpSession session = request.getSession();

		//Intialisation des variables
		String erreur = "";

		/* Récupération et test des champs du formulaire. */	
		String auteur = request.getParameter( "auteur" );
		if ( auteur.isEmpty() ) {
			erreur += "<li>Le auteur est vide !</li>";
		}

		String contenu = request.getParameter( "contenu" );
		if ( contenu.isEmpty() ) {
			erreur += "<li>Le contenu est vide !</li>";
		}

		String note = request.getParameter( "note" );
		if ( note.isEmpty() ) {
			erreur += "<li>Le note est vide !</li>";
		}

		//On recuperer la note du commentaire et on parst string to int
		int noteCom=Integer.parseInt(request.getParameter("note"));

		//On recuperer l'id de la recette et on le parst string to int
		int id=Integer.parseInt(request.getParameter("id")); 

		//Si la variable erreur est differente de vide alors on affiche la page JSP
		if(erreur!="") {

			//Envoi de la variable erreur a la jsp
			request.setAttribute("erreur", erreur);

			//Execute la doget pour que la recette s'affiche
			this.doGet(request, response);

			/* Transmission de la paire d'objets request/response à notre JSP */
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/recette.jsp?id="+id).forward(request, response);
		} else {
				//Creation de l'objets  commentairemanager
				CommentaireManager commentaireManager = new CommentaireManager();
				
				//Creation de l'objets  RecetteManager
				RecetteManager recetteManager = new RecetteManager();
						
				//On crée l'objet commentaire avec les information du formulaire
				Commentaire commentaire = new Commentaire(auteur, contenu, noteCom, new Date());
				
				//On recupere l'objet recette by ID
				Recette recette = recetteManager.getRecetteFromId(id);
				
				//On ajoute la recette au commentaire
				commentaire.setRecette(recette);

				//Creation du commentaire dans la BDD
				try {
					commentaireManager.creatCommentaire(commentaire);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Creation de la session
				session.setAttribute("commentaire", commentaire);

				//On affiche la page index
				response.sendRedirect("recette?id="+id);
		}
	}
}
