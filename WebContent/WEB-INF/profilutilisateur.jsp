
    
<%@ include file="/WEB-INF/fragments/header.jsp" %>    

		
	<div class="container pt-4">
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6 p-4 border mb-4 mt-5">
		 
	   			<h1 class="mb-2 text-center">Profil de ${ utilisateur.pseudo } </h1>
	      		
	 				<div class="row ps-5 ms-5">
	 				
	 					<div class="col-4 ">
	 	      
							 <label class="m-2" for="prenom"><b>prenom :</b></label> <br>
							
							 <label class="m-2" for="telephone"><b>telephone :</b></label> <br>
								
							 <label class="m-2" for="codePostal"><b>Code Postal:</b></label> <br>						
												
							 <label class="m-2" for="nom"><b>Nom :</b></label> <br>
								
				             <label class="m-2" for="email"><b>email :</b></label> <br>
						
				 			<label class="m-2" for="rue"><b>Rue :</b></label> <br>
								
							<label  class="m-2" for="ville"><b>Ville : </b></label> <br>
						
						</div>
						
						<div class="col-8">

							 <label class="m-2" for="prenom"> ${ utilisateur.prenom }  </label> <br>
							
							 <label class="m-2" for="telephone">${ utilisateur.telephone } </label> <br>
								
							 <label class="m-2" for="codePostal">${ utilisateur.code_postal } </label> <br>						
												
							 <label class="m-2" for="nom">${ utilisateur.nom } </label> <br>
								
				             <label class="m-2" for="email">${ utilisateur.email } </label> <br>
						
				 			<label class="m-2" for="rue">${ utilisateur.rue } </label> <br>
								
							<label  class="m-2" for="ville">${ utilisateur.ville } </label> <br>
						
						</div>
				               
					</div>
		 	
		 	</div>								
		</div>
	</div>	  
		
</body>
</html>