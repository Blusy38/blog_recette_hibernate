<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>

<div id="global">
<div id="categorie">
<ul>
<li class="selected"><a
href="categories?idCategorie=">Entrée</a></li>
<li><a href="categorie?idCategorie=">Plat principal</a></li>
</ul>
</div>
<article>
<header>
<img class="imgRecette" src="img/veloute-de-carotte-au-cumin.jpg"
width="300px" height="242px" alt="Tartiflette" />
<a href="recette?id=">
<h1 class="titreRecette">
Velouté de carottes au cumin
</h1>
</a>
<time>
08/01/2019
</time>
</header>
<p>
Un velouté de carotte au cumin
</p>
</article>
<hr />
</div>

<%@ include file="footer.jsp"%>
