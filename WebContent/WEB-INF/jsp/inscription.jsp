<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div id="global">
	<h1>Inscription</h1>
	<article>
		<div id="leftrecette">
			<!-- formulaire de mise a jour -->
			<form method="post" action="inscription">
				<input id="nom" name="nom" type="text" class="inputChamp"
					placeholder="Votre nom *" value="<c:out value="${membre.nom}"/>" /><br />
				<input id="pseudo" name="pseudo" type="text" class="inputChamp"
					placeholder="Votre pseudo *"
					value="<c:out value="${membre.pseudo}"/>" /><br /> <input
					id="email" name="email" type="text" class="inputChamp"
					placeholder="Votre email *"
					value="<c:out value="${membre.email}"/>" /><br /> <input id="mdp"
					name="mdp" type="password" class="inputChamp"
					placeholder="Votre mot de passe *"
					value="<c:out value="${membre.mdp}"/>" /><br /> <input
					id="mdpconf" name="mdpconf" type="password" class="inputChamp"
					placeholder="Confirmez votre mot de passe *"
					value="<c:out value="${membre.mdp}"/>" /><br /> 
				<c:choose>
					<c:when test="${membre.idMembre == null || membre.idMembre ==0}">
						<input type="submit" name="suscrib" value="Je m'inscris"
							class="primaryBtn login" />
					</c:when>
					<c:otherwise>
						<input type="submit" name="update" value="Mise à jour"
							class="primaryBtn login" />
						<input type="submit" name="delete" value="Supprimer"
							class="primaryBtn login" />
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<div id="rightrecette">
			<c:if test="${erreur != null}">
				<span class="erreur">
					<ul>${erreur}</ul>
				</span>
			</c:if>
		</div>
	</article>
	<div class="w">
		<section>
			<c:forEach items="${recettes}" var="recettes">
				<div id="cadre">
					<div><a class="primaryBtn login" href="editrecette?id=${recettes.idRecette}">Modifier</a>
						<hr>
						<header>
							<a href="recette?id=${recettes.idRecette}"><h1 class="titreRecette">${recettes.titre}</h1></a>
							<time>
								<fmt:formatDate value="${recettes.dateCreation}" type="date" dateStyle="full" />
							</time>
						</header>
					</div>
					<div>
						<a href="recette?id=${recettes.idRecette}"><img
							class="imgRecette" src="img/${recettes.photo}" width="300px"
							height="242px" alt="Tartiflette" /> </a>
					</div>
					<div>
						<c:forEach var="i" begin="1" end="${recettes.moyNote}" step="1">
							<span class="fa fa-star checked"></span>
						</c:forEach>
						<c:forEach var="i" begin="${recettes.moyNote+1}" end="5" step="1">
							<span class="fa fa-star"></span>
						</c:forEach>
						<p>${recettes.description}</p>

					</div>
				</div>
			</c:forEach>
		</section>
	</div>
</div>
<%@ include file="footer.jsp"%>