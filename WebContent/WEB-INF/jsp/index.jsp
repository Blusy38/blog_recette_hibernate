<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!--Tag lib qui permet d'utiliser le fmt pour convertir la date -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="global">
	<div class="w">
		<section>
			<c:if test="${msgEmpty != null}">
				<div id="cadre"></div>
				<div id="cadre">
					<div>
						<p>${msgEmpty}</p>
					</div>
					<div>
						<header>
							<c:choose>
								<c:when test="${empty membre.idMembre || membre.idMembre==0}">
									<a href="inscription"><h3 class="titreRecette">Créez
											un compte!!</h3></a>
								</c:when>
								<c:otherwise>
									<a href="editrecette?id=0"><h3 class="titreRecette">Ajoutez
											une recette !!</h3></a>
								</c:otherwise>
							</c:choose>
						</header>
					</div>
					<div>
						<c:choose>
							<c:when test="${empty membre.idMembre || membre.idMembre==0}">
								<a href="inscription"><img class="imgRecette"
									src="img/defaut.jpg" alt="Ajoutez une recette."> </a>
							</c:when>
							<c:otherwise>
								<a href="editrecette?id=0"><img class="imgRecette"
									src="img/defaut.jpg" alt="Ajoutez une recette."> </a>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
				<div id="cadre"></div>
			</c:if>

			<c:forEach items="${recettes}" var="recettes">
				<div id="cadre">
					<div>
						<header>
							<a href="recette?id=${recettes.idRecette}">
								<h1 class="titreRecette">${recettes.titre}</h1>
							</a>
							<time>
								<fmt:formatDate value="${recettes.dateCreation}" type="date"
									dateStyle="full" />
							</time>
						</header>
					</div>
					<div>
						<a href="recette?id=${recettes.idRecette}"><img
							class="imgRecette" src="img/${recettes.photo}"
							alt="${recettes.titre}" /> </a>
					</div>
					<div>
						<c:forEach var="i" begin="1" end="${recettes.moyNote}" step="1">
							<span class="fa fa-star checked"></span>
						</c:forEach>
						<c:forEach var="i" begin="${recettes.moyNote+1}" end="5" step="1">
							<span class="fa fa-star"></span>
						</c:forEach>
						<c:if test="${recettes.membre.idMembre == membre.idMembre}">
							<a class="primaryBtn login"
								href="editrecette?id=${recettes.idRecette}">Modifier</a>
							<br>
						</c:if>
						<p>${recettes.description}</p>
					</div>
				</div>
			</c:forEach>
		</section>
	</div>
</div>
<%@ include file="footer.jsp"%>