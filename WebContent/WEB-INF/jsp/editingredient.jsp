<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div id="global">
	<article>
		<div id="">
			<c:if test="${erreur != null}">
				<span class="erreur">
					<ul>${erreur}</ul>
				</span>
			</c:if>
		</div>
		<div>
			<header>
				<c:if test="${param['id'] != null}"><form method="post" action="editingredient?id=${ingredient.idIngredient}"></c:if>
				<c:if test="${param['idrecette'] != null}"><form method="post" action="editingredient?idrecette=${param['idrecette']}"></c:if>
				<input id="titre" name="titre" type="text" class="inputChamp"
					placeholder="Donnez un nom à la ingredient *"
					value="<c:out value="${ingredient.nom}"/>"
					style="font-size: 1.5em; width: 50%; height: 50px;" /> 
					
					<input id="quantitee" name="quantitee" type="text" class="inputChamp"
					placeholder="Donnez une quantitee *"
					value="<c:out value="${ingredient.quantite}"/>"
					style="font-size: 1.5em; width: 50%; height: 50px;" /> 
					
					<input id="unite" name="unite" type="text" class="inputChamp"
					placeholder="Donnez une unite *"
					value="<c:out value="${ingredient.unit}"/>"
					style="font-size: 1.5em; width: 50%; height: 50px;" /> 
					<br>
					<c:choose>
					<c:when test="${param['idrecette'] != null}">
						<input type="submit" name="Ajouter" value="Ajouter"
							class="primaryBtn login" />
					</c:when>
					<c:otherwise>
						<input type="submit" name="update" value="Modifier"
							class="primaryBtn login" />
						<input type="submit" name="delete" value="Supprimer"
							class="primaryBtn login" />
					</c:otherwise>
				</c:choose>
				</form>
			</header>
		</div>

	</article>
</div>

<%@ include file="footer.jsp"%>