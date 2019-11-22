<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div id="global">
	<article>
		<div>${bouton}
			<c:if test="${erreur != null}">
				<span class="erreur">
					<ul>${erreur}</ul>
				</span>
			</c:if>
		</div>
		<c:if test="${recette != null}">
			<form method="post" action="editrecette?id=${recette.idRecette}">
		</c:if>
		<c:if test="${recette == null}">
			<form method="post" action="editrecette?id=0">
		</c:if>
		<div>
			<header>
				<input id="titre" name="titre" type="text" class="inputChamp"
					placeholder="Donnez un nom à la recette *"
					value="<c:out value="${recette.titre}"/>"
					style="font-size: 1.5em; width: 50%; height: 50px;" /><br> <select
					name="categorie" id="categorie" class="select">
					<option value="0">---------Selectionnez un
						catégorie--------</option>
					<c:forEach items="${categories}" var="categories">
						<option
							<c:if test="${recette.categorie.idCategorie == categories.idCategorie}">selected </c:if>
							value="${categories.idCategorie}">${categories.nom}</option>
					</c:forEach>
				</select>
			</header>
		</div>
		<div id="leftrecette">
			<c:if test="${not empty recette.photo}">
				<img src="img/${recette.photo}" width="300px" height="242px"
					alt="Cliquez pour ajouter une image" />
			</c:if>
			<input type="text" id="photo" name="photo" value="${recette.photo}"
				placeholder="Saisir nom d'un image *" />
		</div>
		<c:if test="${not empty recette.idRecette}">
			<div id="rightrecette">
				<h3 id="titreIngredient">Ingrédients (modifiables)</h3>
				<ul>
					<c:forEach items="${ingredients}" var="ingredients">
						<li><a href="editingredient?id=${ingredients.idIngredient}"
							target="blank">${ingredients.quantite}${ingredients.unit}
								${ingredients.nom}</a></li>
					</c:forEach>
				</ul>
				<a class="primaryBtn login"
					href="editingredient?idrecette=${recette.idRecette}" target="blank">Ajouter
					ingredients</a>
			</div>
		</c:if>
		<textarea id="txtCommentaire" name="description" rows="4"
			placeholder="Saisir une description *" class="inputTextArea"><c:out
				value="${recette.description}" /></textarea>
		<br />
		<div id="rightrecette">
			<fieldset>
				<legend>Tags existants:</legend>
				<c:forEach items="${tags}" var="tags">
					<input type="checkbox" name="tags" value="${tags.idTag}"
						<c:forEach items="${recette.tags}" var="i" varStatus="i"><c:if test="${tags.idTag == recette.tags[i.count-1].idTag}">checked </c:if></c:forEach>> ${tags.nom}<br>
				</c:forEach>
			</fieldset>
		</div>
		<c:choose>
			<c:when test="${empty recette.idRecette}">
				<input type="submit" name="Ajouter" value="Ajouter"
					class="submitBtn" />
			</c:when>
			<c:otherwise>
				<input type="submit" name="update" value="Modifier"
					class="submitBtn" />
				<input type="submit" name="delete" value="Supprimer"
					class="submitBtn" />
			</c:otherwise>
		</c:choose>
		</form>
	</article>
</div>

<%@ include file="footer.jsp"%>