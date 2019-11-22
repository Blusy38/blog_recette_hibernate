package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.pojos.Membre;
import com.blogrecette.services.MembreManager;


/**
 * Servlet implementation class LoginServlet
 */
//Chemin d'affichage de la jsp dans le navigateur
@WebServlet(name="login",urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
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

		String email = request.getParameter( "email" );
		if ( email.isEmpty() ) {
			erreur += "<li>Le mail est vide banane !</li>";
		}

		String mdp = request.getParameter( "mdp" );
		if ( mdp.isEmpty() ) {
			erreur += "<li>Fuck off !!!mdp</li>";
		}

		//Si la variable erreur est differente de vide alors on affiche la page JSP
		if(erreur!="") {
			request.setAttribute("erreur", erreur);

			/* Transmission de la paire d'objets request/response à notre JSP */
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		} else {

				//Creation de l'objets  Membremanager pour faire la connexion a la base
				MembreManager membreManager = new MembreManager();

				//recupere le membre via son mail dans la BDD
				email = request.getParameter( "email" );
				Membre membre = null;
				try {
					membre = membreManager.getMembreFromMail(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//On verifi si on a un retour de la base
				if (membre==null) {
					erreur += "<li>Fuck off !!!</li>";
					request.setAttribute("erreur", erreur);

					/* Transmission de la paire d'objets request/response à notre JSP */
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				} else {

					//Verifi si les mdp bdd et saisi sont les memes
					String mdpBddString = membre.getMdp();
					if ( !mdpBddString.equals(mdp)) {
						erreur += "<li>Fuck off !!!</li>";
						request.setAttribute("erreur", erreur);

						/* Transmission de la paire d'objets request/response à notre JSP */
						this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
					}

					//Creation de la session
					session.setAttribute("membre", membre);

					//On affiche la page index
					response.sendRedirect("index");
				}

				}

			}
		}
