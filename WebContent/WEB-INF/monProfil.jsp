<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<div class="container pt-4">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 p-4 border mb-4">
				<div class="row text-center">
	
				<h1>Mon Profil</h1>
				
					<div class="text-danger row text-center">
						<c:forEach var="item" items="${ listError }">
							<p>${ item }</p>	
						</c:forEach>
					</div>
				
					<form action="<%= request.getContextPath() %>/MonProfil" method="post">
						<div class="row my-1">
							<div class="col-2">
								<label for="idPseudo">Pseudo : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idPseudo" name="spseudo" value="${ myUser.pseudo }">
							</div>
							<div class="col-2">
								<label for="idNom">Nom : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idNom" name="snom" value="${ myUser.nom }" >
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<label for="idPrenom">Prenom : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idPrenom" name="sprenom" value="${ myUser.prenom }">
							</div>
							<div class="col-2">
								<label for="idemail">Email : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idemail" name="semail" value="${ myUser.email }" >
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<label for="idtelephone">Telephone : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idtelephone" name="stelephone" value="${ myUser.telephone }">
							</div>
							<div class="col-2">
								<label for="idrue">Rue : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idrue" name="srue" value="${ myUser.rue }" >
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<label for="idcodepostal">Code Postal : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idcodepostal" name="scodePostal" value="${ myUser.code_postal }">
							</div>
							<div class="col-2">
								<label for="idville">Ville : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="text" id="idville" name="sville" value="${ myUser.ville }" >
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<label for="idmdpact">Mot de passe actuel : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="password" id="idmdpact" name="spasswordact" >
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<label for="idmdpnouv">Nouveau mot de passe : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="password" id="idmdpnouv" name="spasswordnew" >
							</div>
							<div class="col-2">
								<label for="idmdpconf">Confirmation : </label>
							</div>
							<div class="col-4">
								<input class="form-control" type="password" id="idmdpconf" name="spasswordconf">
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-2">
								<p>Crédit</p>
							</div>
							<div class="col-4">
								<p>${ myUser.credit }</p>
							</div>
						</div>
						
						<div class="row my-1">
							<div class="col-3 offset-3">
								<button class="btn btn-success me-2" type="submit" name="supdate" value="ok">Enregistrer</button>
							</div>
							<div class="col-3">
								<button class="btn btn-danger ms-2" type="submit" name="sdelete" value="ok">Supprimer mon compte</button> 
							</div>
						</div>
					</form>
					
					</div>
				</div>	
			</div>
		</div>
	</body>
</html>