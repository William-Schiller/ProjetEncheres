<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Profil</title>
</head>
<body>
<div class="row">
		<div class="col-md-4">
		</div>
	
		<div class="col-md-4 position-absolute top-50 start-50 translate-middle text-center border">
		     
		     
		 
		   <h1 class="mb-5">Profil</h1>
		    
		    
			<label class="m-2" for="pseudo"><b>Pseudo : </b>${ utilisateur.pseudo } </label> <br>
	         
			<label  class="m-2" for="nom"><b>Nom :</b> ${ utilisateur.nom }</label> <br>
						 					
			<label  class="m-2" for="prenom"><b>prenom :</b> ${ utilisateur.prenom }  </label> <br>
											
			<label  class="m-2" for="email"><b>email :</b>  ${ utilisateur.email }  </label> <br>
											
			<label  class="m-2" for="telephone"><b>telephone :</b>${ utilisateur.telephone } </label> <br>
													
			<label  class="m-2" for="rue"><b>Rue :</b>  ${ utilisateur.rue } </label> <br>
			
			<label  class="m-2" for="codePostal"><b>Code Postal:</b> ${ utilisateur.code_postal } </label> <br>
									
			<label  class="m-2" for="ville"><b>Ville :</b> ${ utilisateur.ville } </label> <br>
		
		    
		    </div>
		
		    
		
		
	
	
		<div class="col-md-4">
		</div>
	</div>


<br>

     <div class=>
     
			
	
		
	</div>



</body>
</html>