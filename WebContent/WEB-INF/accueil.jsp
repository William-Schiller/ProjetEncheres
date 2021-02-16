<%@ include file="/WEB-INF/fragments/header.jsp" %>


		<h1>ACCUEIL</h1>
		
		<form action="./Accueil" method="post">
			<div class="inner-addon left-addon">
				<i class="glyphicon glyphicon-search"></i>
				<input type="text" name="skeyword" placeholder="Le nom de l'article contient">
			</div>
			<label>Catégorie : </label>
			
			<select name="scategorie">
				<c:forEach var="cat" items="${listeCategorie}">
					<option value="${cat.no_categorie}">${cat.libelle}</option>
				</c:forEach>
			</select>
			<button type="submit">Rechercher</button>
		</form>
		<h1>${keyword} </h1>
		<h1>${no_categorie} </h1>
		
		
		
		<c:if test="${!empty listeArticle}">
			<c:forEach var="art" items="${listeArticle}">
			    
				<p>${art.nom_article}</p>
				<p>${art.date_fin_encheres}</p>
				<p>${art.prix_initial}</p>
				<p>${art.no_utilisateur}</p>
				<form action="./DetailEnchere" method="post"> 
					<button type="submit" name="sno_article" value="${ art.no_article}">Afficher</button>
				</form>
			</c:forEach>
		</c:if>


	</body>
</html>