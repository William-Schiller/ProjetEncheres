<%@ include file="/WEB-INF/fragments/header.jsp" %>

		<div class="container pt-4">
			<div class="row">
				<div class="col-md-3 m-2">
					<img src="<c:url value="public/imageArticle/${empty article.article.image ? 'image-not-found.png' : article.article.image}"></c:url>" alt="image de l'article" class="img-thumbnail">
				</div>
				<div class="col-md-8 p-4 border mb-4">
					<div class="row text-center">

						<div class="row">
							<h2>${article.article.nom_article}</h2>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Description :</p>
							</div>
							<div class="col-8">
								<p>${article.article.description}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Catégorie :</p>
							</div>
							<div class="col-8">
								<p>${categorie}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Meilleur offre :</p>
							</div>
							<div class="col-8">
								<p>${empty article.meilleurEnchere ? article.article.prix_initial : article.meilleurEnchere.montant_enchere} points</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Mise à prix :</p>
							</div>
							<div class="col-8">
								<p>${article.article.prix_initial} points</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Fin de l'enchère :</p>
							</div>
							<div class="col-8">
								<p>${article.date_fin}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Retrait :</p>
							</div>
							<div class="col-8">
								<p>${retrait.rue}</p>
								<p>${retrait.code_postale} ${retrait.ville}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<p>Vendeur :</p>
							</div>
							<div class="col-8">
								<p><a href="./Profil?sid=${article.user.no_utlisateur}" >${article.user.pseudo}</a></p>
							</div>
						</div>
						
						<c:if test="${sessionScope.myUser.pseudo != article.user.pseudo}">
							<c:if test="${!checkDateDebut}">
								<c:if test="${!checkDateFin}">
									<form action="./Encherir" method="post">
										<div class="row">
											<div class="col-4">
												<label for="idMaProposition">Ma proposition : </label>
											</div>
											<div class="col-4">
												<input class="form-control" type="number" id="idMaProposition" name="smonEnchere"
												min="${empty article.meilleurEnchere ? article.article.prix_initial : article.meilleurEnchere.montant_enchere}"
												max="${sessionScope.myUser.credit}">
											</div>
											<div class="col-4">
												<button class="btn btn-primary me-2" name="idNo_article" value="${sno_article}" type="submit">Enchérir</button>
											</div>
										</div>
									</form>
								</c:if>
							</c:if>
						</c:if>
						
						<c:if test="${sessionScope.myUser.pseudo == article.user.pseudo}">
							<c:if test="${checkDateDebut}">
								<form action="./ModifierVente" method="post">
									<div class="row">
										<div class="col-8 offset-4">
											<button class="btn btn-primary me-2" name="idNo_article" value="${sno_article}" type="submit">Modifier Vente</button>
										</div>
									</div>
								</form>
							</c:if>
						</c:if>
						
						<c:forEach var="item" items="${listeDesErreurs}">
                            <p class="my-1" style="color:red">${item}</p>
                        </c:forEach>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>