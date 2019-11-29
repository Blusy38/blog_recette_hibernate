<%@ include file="header.jsp"%>
<div id="global">
	<h1>Login</h1>
	<div id="inscription">
		<!-- formulaire de login -->
		<form method="post" action="login">
			<input id="email" name="email" type="text" class="inputChamp"
				placeholder="Votre email *" value="<c:out value="${membre.email}"/>" /><br />
			<input id="mdp" name="mdp" type="password" class="inputChamp"
				placeholder="Votre mot de passe *" value="<c:out value="${membre.mdp}"/>" /><br />
			<input type="submit" name="login"value="Je me connect" class="submitBtn" />
		</form>
	</div>
		<c:if test="${erreur != null}">
			<div id="erreur">
				<span class="erreur">
					<ul>${erreur}</ul>
				</span>
			</div>
		</c:if>
</div>
<%@ include file="footer.jsp"%>