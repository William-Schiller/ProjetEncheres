<%@ include file="/WEB-INF/fragments/header.jsp" %>

		<div class="container pt-4">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10 p-4 border">
					<div class="row text-center">
	
				
						<div class="text-danger row text-center">
							<c:forEach var="item" items="${ listError }">
								<p>${ item }</p>	
							</c:forEach>
						</div>
						
						<div class="row mb-4" style="justify-content: center">
						
							<form class="row" action="<%= request.getContextPath() %>/Connexion" method="post">
								<div class="row">
									<div class="col-4 offset-2">
										<label for="idIdentifiant">Identifiant : </label>
									</div>
									<div class="row col-4">
										<input class="form-control" type="text" id="idIdentifiant" name="spseudo" value="${ empty pseudo? (empty pseudoError? '' : pseudoError ) : pseudo }">
									</div>
								</div>
								<div class="row mt-2">
									<div class="col-4 offset-2">
										<label for="idPassword">Mot de passe : </label>
									</div>
									<div class="row col-4">
										<input class="form-control" type="password" id="idPassword" name="spassword" placeholder="${ empty passwordError? '' : passwordError }" >
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-4 offset-2">
										<button class="btn btn-success me-2" type="submit">Connexion</button>
									</div>
									<div class="col-4">
										<input class="form-check-input" id="idSSDM" type="checkbox" name="sSeSouvenirDeMoi" value="true">
										<label class="form-check-label" for="idSSDM">Se souvenir de moi : </label>
										<br>
										<a href="#">Mot de passe oublié</a>
									</div>
								</div>
							</form>
						
							<div class="row col-6 mt-3 offset-3">
								<form action="./inscription" method="get">
									<button class="btn btn-primary me-2" type="submit">Créer un compte</button>
								</form>
							</div>
						
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>