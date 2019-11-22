<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!--Tag lib qui permet d'utiliser le fmt pour convertir la date -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="global">
	<div class="w">
		<section>
			<div id="cadre">
				<div>
					<header>
						<h1 class="titreRecette">Gestion des catégories</h1>
					</header>
				</div>
				<div>
					<fieldset>
						<legend>Categories existants:</legend>
						<ul>
							<c:forEach items="${categories}" var="categories">

								<li><a href="index?idCategorie=${categories.idCategorie}">${categories.nom}</a>
									<c:if test="${categories.idCategorie !=7}"> - <a
											href="admin?idCategorie=${categories.idCategorie}"><i
											class="far fa-trash-alt"></i></a>
									</c:if></li>

							</c:forEach>
						</ul>
					</fieldset>

				</div>
				<div>
					<form method="post" action="admin">
						<fieldset>
							<legend>Ajoutez des catégories:</legend>
							<span id="erreur">${erreurcat}</span> Nom de la catégorie : <input
								type="text" name="categoriename"> <br> <input
								type="submit"name="savecat" value="Enregistrer">
						</fieldset>
					</form>
				</div>
			</div>
			<div id="cadre">
				<div>
					<header>
						<h1 class="titreRecette">Gestion des tags</h1>
					</header>
				</div>
				<div>
					<fieldset>
						<legend>Tags existants:</legend>
						<ul>
							<c:forEach items="${tags}" var="tags">
								<li><a href="index?idTag=${tags.idTag}">${tags.nom}</a> - <a
									href="admin?idTag=${tags.idTag}"><i
										class="far fa-trash-alt"></i></a></li>

							</c:forEach>
						</ul>
					</fieldset>

				</div>
				<div>
					<form method="post" action="admin">
						<fieldset>
							<legend>Ajoutez des tags:</legend>
							<span id="erreur">${erreurtag}</span> Nom du tag : <input
								type="text" name="tagname"> <br> <input
								type="submit" name="savetag" value="Enregistrer">
						</fieldset>
					</form>
				</div>
			</div>
		</section>
	</div>
</div>
<%@ include file="footer.jsp"%>