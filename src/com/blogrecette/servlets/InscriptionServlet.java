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

import com.blogrecette.pojos.Membre;
import com.blogrecette.pojos.Recette;
import com.blogrecette.services.MembreManager;
import com.blogrecette.services.RecetteManager;

/**
 * Servlet implementation class InscriptionServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="inscription",urlPatterns= {"/inscription"})
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//************************RECETES*******************************************
		//Creation de l'objets  recetteManager
		RecetteManager recetteManager = new RecetteManager();

		//Creation de la session
		HttpSession session = request.getSession();

		if (session.getAttribute("membre") != null) {
			//On recupere le membre depuis la session on le cast de objet to membre
			Membre membre = (Membre) session.getAttribute("membre");
			int idMem = membre.getIdMembre();


			//Crée un variable list RecetteS avec les informations de la fonction Getall
			List<Recette> recettes = null;
			//On recupere toutes les recettes par membres
			recettes = recetteManager.getAllByIdMembre(idMem);
			request.setAttribute("recettes", recettes);
		}

		//Permet d'afficher la page jps
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);

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
		String nom = request.getParameter( "nom" );
		if ( nom.isEmpty() ) {
			erreur += "<li>Le nom est vide !</li>";
		}

		String pseudo = request.getParameter( "pseudo" );
		if ( pseudo.isEmpty() ) {
			erreur += "<li>Le pseudo est vide !</li>";
		}

		String email = request.getParameter( "email" );
		if ( email.isEmpty() ) {
			erreur += "<li>Le mail est vide !</li>";
		}

		String mdp = request.getParameter( "mdp" );
		if ( mdp.isEmpty() ) {
			erreur += "<li>Le mot de passe est vide !</li>";
		}

		String mdpconf = request.getParameter( "mdpconf" );
		//Pour verifier 2 string il faut faire un equals
		if ( !mdp.equals(mdpconf)) {
			erreur += "<li>Les mots de passe ne sont pas identiques !</li>";
		}

		//Si la variable erreur est differente de vide alors on affiche la page JSP
		if(erreur!="") {
			request.setAttribute("erreur", erreur);

			//On crée l'objet Member avec les information du formulaire
			Membre membre = new Membre(nom, pseudo, email, mdp,new Date());

			//Creation de la session
			session.setAttribute("membre", membre);

			/* Transmission de la paire d'objets request/response à notre JSP */
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		} else {
			//Si la variable erreur est vide alors on fait le traitement des données

			//Creation de l'objets  Membremanager pour faire la connexion a la base
			MembreManager membreManager = new MembreManager();

			//On test le nom du bouton pour savoir si on fait une inscription
			if (request.getParameter("suscrib") != null) {

				//INSCRIPTION On crée l'objet Member avec les information du formulaire
				Membre membre = new Membre(nom, pseudo, email, mdp,new Date());

				//Creation du membre dans la BDD
				try {
					membreManager.creatMembre(membre);

					//Creation de la session
					session.setAttribute("membre", membre);

					//On affiche la page index
					response.sendRedirect("inscription");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				//On test le nom du bouton pour savoir si on fait une mise a jour				
			} else if (request.getParameter("update") != null) {

				//Creation d'un membre depuis les attributs de la session
				Membre membre = (Membre) session.getAttribute("membre");
				
				try {
					//UPDATE On met a jour des champs utiles dans l'objet membre
					membre.setNom(nom);
					membre.setPseudo(pseudo);
					membre.setMdp(mdp);
					membre.setEmail(email);
					
					//update du membre dans la BDD
					membreManager.updateMembre(membre);
					
					//Creation de la session
					session.setAttribute("membre", membre);

					//Renvoi vers la page inscription
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//On test le nom du bouton pour savoir si on fait un delete				
			} else if (request.getParameter("delete") != null) {

				//Creation d'un membre depuis les attributs de la session
				Membre membre = (Membre) session.getAttribute("membre");

				try {
					//DELETE du membre dans la BDD
					membreManager.deleteMembre(membre);
					
					//Suppression de la session
					HttpSession Session=request.getSession();
					Session.invalidate();

					//On affiche la page index
					response.sendRedirect("index");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	}
}
