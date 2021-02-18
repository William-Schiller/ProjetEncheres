<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<div class="container pt-4">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 p-4">
				<h1>Liste des Ench�res</h1>
				
				
				<form action="./Accueil" method="post">
					<div class="row mt-5">
						<p>Filtres :</p>
					</div>
					<div class="row">
						<div class="row col-6">
							<div class="inner-addon left-addon">
								<i class="glyphicon glyphicon-search"></i>
								<input class="form-control" type="text" name="skeyword" placeholder="Le nom de l'article contient">
							</div>
							<div>
								<label>Cat�gorie : </label>
								<select class="selectpicker" name="scategorie">
									<option value="0">Toutes</option>
									<c:forEach var="cat" items="${listeCategorie}">
										<option value="${cat.no_categorie}">${cat.libelle}</option>
									</c:forEach>
								</select>
							</div>
							
							<c:if test="${!empty sessionScope.myUser}">
								<div class="col-6 mt-3">
									<input class="form-check-input" type="radio" name="sachatsVentes" value="achat" id="radioAchat">
									<label for="radioAchat">Achats</label> <br>
									<div class="ms-3">
										<input class="form-check-input" type="radio" name="sachat" value="" id="checkAchat1">
										<label for="checkAchat1">Ench�res ouvertes</label> <br>
										<input class="form-check-input" type="radio" name="sachat" value="" id="checkAchat2">
										<label for="checkAchat2">Mes ench�res</label> <br>
										<input class="form-check-input" type="radio" name="sachat" value="" id="checkAchat3">
										<label for="checkAchat3">Mes ench�res remport�es</label> <br>
									</div>
								</div>
								<div class="col-6 mt-3">
									<input class="form-check-input" type="radio" name="sachatsVentes" value="vente" id="radioVente">
									<label for="radioVente">Mes Ventes</label>
									<div class="ms-3">
										<input class="form-check-input borderCheckBox" type="radio" name="svente" value="" id="checkVente1">
										<label for="checkVente1">Ench�res ouvertes</label> <br>
										<input class="form-check-input borderCheckBox" type="radio" name="svente" value="" id="checkVente2">
										<label for="checkVente2">Mes ench�res</label> <br>
										<input class="form-check-input borderCheckBox" type="radio" name="svente" value="" id="checkVente3">
										<label for="checkVente3">Mes ench�res remport�es</label> <br>
									</div>
								</div>
							</c:if>
							
							
						</div>
						<div class="row col-6">
							<button class="btn btn-success me-2" type="submit">Rechercher</button>
						</div>
					</div>
				</form>	
		
		<h1>${keyword} </h1>
		
					
			</div>
		</div>
	</div>
		
	<div class="displayFlexRow justifyContentCenter">

			<c:if test="${!empty listeArticle}">
				<div class="row justifyContentCenter">
					<c:forEach var="art" items="${listeArticle}">
						<form class="col-md-5 m-3" action="${empty sessionScope.myUser ? '' : './DetailEnchere' }" method="post"> 
							<button class="btn btn-outline-success" type="submit" name="sno_article" value="${ art.article.no_article}">
								<div class="row m-3">
									<div class="col-md-6">
										<img width="200" height="200" src="<c:url value="public/imageArticle/${empty art.article.image ? 'image-not-found.png' : art.article.image}"></c:url>" alt="image de l'article" class="img-thumbnail">
									</div>
									<div class="col-md-6">
										<p>${art.article.nom_article}</p>
										<p>Prix : ${empty art.meilleurEnchere ? art.article.prix_initial : art.meilleurEnchere.montant_enchere} points</p>
										<p>Fin de l'ench�re :  ${art.date_fin}</p>
										<p>Vendeur : ${art.user.pseudo}</p>
									</div>
								</div>
							</button>
						</form>
					</c:forEach>
				</div>
			</c:if>	
			

	</div>


	</body>
</html>