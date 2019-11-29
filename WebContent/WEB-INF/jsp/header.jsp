<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, target-densitydpi=device-dpi, initial-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
<title>Mon Blog de Recettes</title>
</head>
<body>
	<header id="header">
		<a href="index"><h1 id="titreBlog">Mon Blog de Recettes</h1></a>
		<div id="loginBar">
			<div class="login">
				<c:choose>
					<c:when test="${empty membre.idMembre || membre.idMembre==0}">
						<a class="primaryBtn login" href="inscription">Inscription</a>
						<a class="primaryBtn login" href="login">Connexion</a>
					</c:when>
					<c:otherwise>
						<a class="primaryBtn login" href="index?logout=logout">Deconnexion</a>
						<a class="primaryBtn login" href="admin">Administration</a>
						<a class="primaryBtn login" href="inscription">${membre.nom}</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div id="categorie">
		<ul>
			<li><a href="index">Toutes les recettes</a></li>
			<c:forEach items="${categories}" var="categories">
			<c:if test="${categories.idCategorie !=7}">
				<li><a href="index?idCategorie=${categories.idCategorie}">${categories.nom}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${not empty membre.idMembre && membre.idMembre!=0}">
				<li><a href="index?idMembre=${membre.idMembre}">Mes
						recettes</a></li>
				<li><a href="editrecette?id=0">Ajouter une recette</a></li>
			</c:if>
		</ul>
	</div>