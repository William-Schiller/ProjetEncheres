<%@ include file="/WEB-INF/fragments/header.jsp" %>
	
	<div class="container pt-4">
		<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-8 p-4 border">
				<div class="row text-center">
				
				

					<h1>Vendre un article</h1>
					<br>
					<h2>Mon Article</h2>
						<c:forEach var="item" items="${listError}">
							<p class="my-3" style="color:red">${item}</p>
						</c:forEach>
				</div>
				<div class="row mb-4" style="justify-content: center">
					<form class="row" action="<%= request.getContextPath() %>/VendreArticle" method="post" enctype="multipart/form-data" >
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="idarticle">Article </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="idarticle"
								name="sarticle" value="${empty sarticle? '':sarticle}">
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="iddescription">Description </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<textarea class="form-control" id="iddescription" rows="3" 
								name="sdecscription">${empty sdecscription? '':sdecscription}</textarea>
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="idcategorie">Catégorie </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<select class="selectpicker" id="idcategorie" name="scategorie">
									<option value="idCategorie !!">Coup-de-Coeurs</option>
									<option value="test" ${scategorie=='test'?'selected':''}>recuperer liste</option>
									<option >categorie avec </option>
									<option >id</option>
								</select>
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="idphoto">Photo de l'article </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<input type="file" class="form-control" id="idphoto" placeholder="a-tranformer-en-boutton-upload"
								name="sphoto" accept="image/png, image/jpeg">
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="idprix">Mise à prix </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<input type="number" min="0" class="form-control" id="idprix"
								name="sprix" value="${empty sprix? '':sprix}">
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="iddebutenchere">Début de l'enchere </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<input type="date" class="form-control" id="iddebutenchere"
								name="sdate_debut" value="${empty sdate_debut? '':sdate_debut}">
								<input type="time" class="form-control" id="iddebutenchere"
								name="sheure_debut" value="${empty sheure_debut? '':sheure_debut}">
							</div>
						</div>
						
						<div class="col-md-3 p-1 mb-1">
							<div class="input-group mb-3">
								<label for="idfinenchere">Fin de l'enchere </label>
							</div>
						</div>
						<div class="col-md-7 p-1 mb-1">
							<div class="input-group mb-3">
								<input type="date" class="form-control" id="idfinenchere"
								name="sdate_fin" value="${empty sdate_fin? '':sdate_fin}">
								<input type="time" class="form-control" id="idfinenchere"
								name="sheure_fin" value="${empty sheure_fin? '':sheure_fin}">
							</div>
						</div>
						
						<div class="col-md-12 p-4 border">
							<div class="row text-center">
								<h3>Adresse retrait</h3>
								<div class="col-md-3 p-1 mb-1">
									<div class="input-group mb-3">
										<label for="idrue">Rue </label>
									</div>
								</div>
								<div class="col-md-7 p-1 mb-1">
									<div class="input-group mb-3">
										<c:if test="${empty srue}">
											<input type="text" class="form-control" id="idrue"
											name="srue" value="${sessionScope.myUser.rue}">
										</c:if>
										<c:if test="${!empty srue}">
											<input type="text" class="form-control" id="idrue"
											name="srue" value="${empty srue? '':srue}">
										</c:if>
									</div>
								</div>
								
								<div class="col-md-3 p-1 mb-1">
									<div class="input-group mb-3">
										<label for="idcodepostal">Code Postal </label>
									</div>
								</div>
								<div class="col-md-7 p-1 mb-1">
									<div class="input-group mb-3">
										<c:if test="${empty scode_postal}">
											<input type="number" class="form-control" id="idcodepostal"
											name="scode_postal" value="${sessionScope.myUser.code_postal}">
										</c:if>
										<c:if test="${!empty scode_postal}">
											<input type="number" class="form-control" id="idcodepostal"
											name="scode_postal" value="${empty scode_postal? '':scode_postal}">
										</c:if>
									</div>
								</div>
								
								<div class="col-md-3 p-1 mb-1">
									<div class="input-group mb-3">
										<label for="idville">Ville </label>
									</div>
								</div>
								<div class="col-md-7 p-1 mb-1">
									<div class="input-group mb-3">
										<c:if test="${empty sville}">
											<input type="text" class="form-control" id="idville"
											name="sville" value="${sessionScope.myUser.ville}">
										</c:if>
										<c:if test="${!empty sville}">
											<input type="text" class="form-control" id="idville"
											name="sville" value="${empty sville? '':sville}">
										</c:if>
									</div>
								</div>
								
							</div>
						</div>
				
							
						<div class="col-md-12 p-4 mb-2">
							<div class="row text-center">	
								<input class="btn btn-success me-2" type="submit" value="Creer">
							</div>
						</div>
						<div class="col-md-12 p-4 mb-2">
							<div class="row text-center">	
								<a href="<%=request.getContextPath()%>">
									<input class="btn btn-danger ms-2" type="button" value="Annuler">
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>