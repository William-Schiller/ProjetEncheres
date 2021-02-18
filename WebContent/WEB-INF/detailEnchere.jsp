<%@ include file="/WEB-INF/fragments/header.jsp" %>


	<main>
		<div class="container-fluid" id="bg" style="padding: 5% 13%">
			<div class="row" id="form">
				<div class="col-md-3 m-2 p-4">
					<img src="<c:url value="public/imageArticle/${empty article.article.image ? 'image-not-found.png' : article.article.image}"></c:url>" alt="image de l'article" class="img-thumbnail">
				</div>
				<div class="col-md-8 p-4 mb-4">
					<div class="row ">
						<div class="row">
							<h2 class="text-center mb-5">${article.article.nom_article}</h2>
						</div>
						<div style="padding-left: 10%">
							<c:if test="${checkDateFin}">
								<c:if test="${!empty article.userEncher}">
									<c:if test="${article.userEncher.pseudo == sessionScope.myUser.pseudo}">
										<div class="row">
											<h3>Vous avez remporté la vente !</h3>
										</div>
									</c:if>
									<c:if test="${article.userEncher.pseudo != sessionScope.myUser.pseudo}">
										<div class="row">
											<h3>Article acheté par ${article.userEncher.pseudo}</h3>
										</div>
									</c:if>
								</c:if>
								<c:if test="${empty article.userEncher}">
									<h3>Article non vendu</h3>
								</c:if>
							</c:if>
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
									<p>${empty article.meilleurEnchere ? '0' : article.meilleurEnchere.montant_enchere} points ${!empty article.userEncher ? article.userEncher.pseudo : ''}</p>
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
													<button class="btn me-2" id="btn-vert"  name="idNo_article" value="${sno_article}" type="submit">Enchérir</button>
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
												<button class="btn me-2" id="btn-vert"  name="idNo_article" value="${sno_article}" type="submit">Modifier Vente</button>
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
		</div>
	</main>

<%@ include file="/WEB-INF/fragments/footer.jsp" %>