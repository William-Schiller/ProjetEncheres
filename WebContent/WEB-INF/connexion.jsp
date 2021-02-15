<%@ include file="/WEB-INF/fragments/header.jsp" %>
	
	<body>
		<div class="text-danger row text-center">
			<c:forEach var="item" items="${ listError }">
				<p>${ item }</p>	
			</c:forEach>
		</div>
		
		<form action="<%= request.getContextPath() %>/Connexion" method="post">
			<div class="row">
				<div class="col-3 offset-3">
					<label for="idIdentifiant">Identifiant : </label>
				</div>
				<div class="row col-3">
					<input type="text" id="idIdentifiant" name="spseudo" value="${ empty pseudo? (empty pseudoError? '' : pseudoError ) : pseudo }">
				</div>
			</div>
			<div class="row">
				<div class="col-3 offset-3">
					<label for="idPassword">Mot de passe : </label>
				</div>
				<div class="row col-3">
					<input type="password" id="idPassword" name="spassword" placeholder="${ empty passwordError? '' : passwordError }" >
				</div>
			</div>
			<div class="row">
				<div class="col-3 offset-3">
					<button type="submit">Connexion</button>
				</div>
				<div class="col-3">
					<input id="idSSDM" type="checkbox" name="sSeSouvenirDeMoi" value="true">
					<label for="idSSDM">Se souvenir de moi : </label>
					<br>
					<a href="#">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		
		<div class="row col-6 offset-3">
			<form action="./inscription" method="get">
				<button type="submit">Créer un compte</button>
			</form>
		</div>
	</body>
</html>