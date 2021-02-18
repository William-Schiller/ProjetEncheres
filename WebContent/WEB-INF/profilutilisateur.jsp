
    
<%@ include file="/WEB-INF/fragments/header.jsp" %>    

	<main>
		<div class="container-fluid" id="bg" style="padding: 6% 12%">
			<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6 p-4 mb-4 mt-5" id="form">
	   			<h1 class="mb-5 text-center">Profil de ${ utilisateur.pseudo } </h1>
	 				<div class="row justify-content-center">
	 					<div class="col-3 ">
							 <label class="m-2" for="prenom"><b>Prénom :</b></label> 
							 <br>
							 <label class="m-2" for="nom"><b>Nom :</b></label> 
							 <br>
							 <label class="m-2" for="telephone"><b>Téléphone :</b></label> 
							 <br>
							 <label class="m-2" for="codePostal"><b>Code postal:</b></label> 
							 <br>	
				             <label class="m-2" for="email"><b>Email :</b></label> 
				             <br>
				 			<label class="m-2" for="rue"><b>Rue :</b></label> 
				 			<br>
							<label  class="m-2" for="ville"><b>Ville : </b></label>
						</div>
						<div class="col-5 text-center">
							 <label class="m-2" for="prenom"> ${ utilisateur.prenom }  </label>
							 <br>
							 <label class="m-2" for="nom">${ utilisateur.nom } </label> 
							 <br>
							 <label class="m-2" for="telephone">${ utilisateur.telephone } </label> 
							 <br>
							 <label class="m-2" for="codePostal">${ utilisateur.code_postal } </label> 
							 <br>
				             <label class="m-2" for="email">${ utilisateur.email } </label> 
				             <br>
				 			<label class="m-2" for="rue">${ utilisateur.rue } </label> 
				 			<br>
							<label  class="m-2" for="ville">${ utilisateur.ville } </label>
						</div>
					</div>
			 	</div>								
			</div>
		</div>	  
	</main>
	
<%@ include file="/WEB-INF/fragments/footer.jsp" %>
