<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<div class="container pt-4">
		<div class="row text-center">
			<div class="col-md-1"></div>
			<div class="col-md-10 p-4">
				<h1>Liste des Enchères</h1>
				
				
				<form action="./Accueil" method="post">
					<div class="row mt-5">
						<p>Filtres :</p>
					</div>
					<div class="row text-center">
						<div class="row col-6">
							<div class="inner-addon left-addon">
								<i class="glyphicon glyphicon-search"></i>
								<input class="form-control" type="text" name="skeyword" placeholder="Le nom de l'article contient">
							</div>
							<div>
								<label>Catégorie : </label>
								<select class="selectpicker" name="scategorie">
									<option value="0">Toutes</option>
									<c:forEach var="cat" items="${listeCategorie}">
										<option value="${cat.no_categorie}">${cat.libelle}</option>
									</c:forEach>
								</select>
							</div>
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
		
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 p-4">

					<c:if test="${!empty listeArticle}">
						<c:forEach var="art" items="${listeArticle}">
							<form action="${empty sessionScope.myUser ? '' : './DetailEnchere' }" method="post"> 
								<div class="row col-md-6 m-3">
									<button type="submit" name="sno_article" value="${ art.no_article}">
										<div class="row col-md-12 m-3">
											<div class="col-md-6">
												<img width="200" height="200" src="<c:url value="public/imageArticle/${empty art.image ? 'logoPROMO.png' : art.image}"></c:url>" alt="image de l'article" class="img-thumbnail">
											</div>
											<div class="col-md-6">
												<p>${art.nom_article}</p>
												<p>${art.date_fin_encheres}</p>
												<p>${art.prix_initial}</p>
												<p>${art.no_utilisateur}</p>
											</div>
										</div>
									</button>
								</div>
							</form>
						</c:forEach>
						
					</c:if>
					
			</div>
		</div>
	</div>


	</body>
</html>