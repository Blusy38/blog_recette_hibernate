<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<!--Tag lib qui permet d'utiliser le fmt pour convertir la date -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="global">
	<article>
		<div>
			<header>
				<a href="recette?id=${recette.idRecette}">
					<h1 class="titreRecette">${recette.titre}</h1>
				</a>
				<time>
					<fmt:formatDate value="${recette.dateCreation}" type="date"
						dateStyle="full" />
				</time>
			</header>
		</div>
		<div id="leftrecette">
			<a href="recette?id=${recette.idRecette}"><img
				src="img/${recette.photo}" width="300px" height="242px"
				alt="Tartiflette" /></a>
			<c:forEach var="i" begin="1" end="${recette.moyNote}" step="1">
				<span class="fa fa-star checked"></span>
			</c:forEach>
			<c:forEach var="i" begin="${recette.moyNote+1}" end="5" step="1">
				<span class="fa fa-star"></span>
			</c:forEach>
		</div>
		<div id="rightrecette">
			<h1 id="titreIngredient">Ingrédients</h1>
			<ul>
				<c:forEach items="${ingredients}" var="ingredients">
					<li>${ingredients.quantite}${ingredients.unit}
						${ingredients.nom}</li>
				</c:forEach>
			</ul>
		</div>
		<div>${recette.description}</div>
		<c:if test="${not empty tags}">
		<div>Tags : <c:forEach items="${tags}" var="tags"> - <a href="index?idTag=${tags.idTag}">${tags.nom}</a></c:forEach> - </div>
		</c:if>
	</article>
	<h2 id="titreCommentaire">Commentaires</h2>
	<c:forEach items="${commentaires}" var="commentaires">
		<div class="divCommentaire">
			<p class="titreCommentaire">${commentaires.auteur}</p>
			<div id="contenuCommentaire">${commentaires.contenu}</div>
			<p id="infoCommentaire">
				<c:forEach var="i" begin="1" end="${commentaires.note}" step="1">
					<span class="fa fa-star checked"></span>
				</c:forEach>
				<c:forEach var="i" begin="${commentaires.note+1}" end="5" step="1">
					<span class="fa fa-star"></span>
				</c:forEach>
				le
				<fmt:formatDate value="${commentaires.dateCreation}" type="date"
					dateStyle="full" />
			</p>
		</div>
	</c:forEach>
	<c:if test="${erreur != null}">
		<div id="erreur">
			<span class="erreur">
				<ul>${erreur}</ul>
			</span>
		</div>
	</c:if>
	<div id="formulaire">
		<form method="post" action="recette?id=${recette.idRecette}">
			<input id="auteur" name="auteur" type="text"
				placeholder="Votre nom *" class="inputChamp" style="width: 50%;"
				value="<c:out value="${param.auteur}"/>" /><br />
			<textarea id="txtCommentaire" name="contenu" rows="4"
				placeholder="Votre commentaire *" class="inputTextArea"><c:out
					value="${param.contenu}" /></textarea>
			<br /> <label for="note">Note</label><br /> <select name="note"
				id="note" class="select">
				<option value="5">J'adore</option>
				<option value="4">Trés bon</option>
				<option value="3" selected>Moyen</option>
				<option value="2">Sympa</option>
				<option value="1">Pas trés bon</option>
			</select> <br /> <input type="submit" value="Commenter" class="submitBtn" />
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>
