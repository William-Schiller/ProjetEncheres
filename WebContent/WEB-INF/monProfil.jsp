<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="public/css/style.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> <!-- biblio icone ?? -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
		<title>${ title }</title>
	</head>
<body>
	<header>
		<div class="row m-3">
			<div class="col-md-3">
				<h1>Titre Appli</h1>
			</div>
			<div class="col-md-3">
				<h1>**************</h1>
			</div>
			<div class="col-md-2">
				<p>${ sessionScope.myUser.pseudo }</p>
			</div>
			<div class="col-md-2">
				<p>${ empty sessionScope.myUser ? '' : '<a href="./MonProfil">Mon profil</a>' }</p>
			</div>
			<div class="col-md-2">
				<p>${ empty sessionScope.myUser ? '<a href="./Connexion">S\'inscrire - Se Connecter</a>' : '<a href="./Deconnexion">Se Déconnecter</a>' } </p>
			</div>
		</div>
	</header>
	
	<h1>Mon Profil</h1>
	
		<form action="<%= request.getContextPath() %>/MonProfil" method="post">
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idPseudo">Pseudo : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idPseudo" name="spseudo" value="${ myUser.pseudo }">
				</div>
				<div class="col-2 offset-1">
					<label for="idNom">Nom : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idNom" name="snom" value="${ myUser.nom }" >
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idPrenom">Prenom : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idPrenom" name="sprenom" value="${ myUser.prenom }">
				</div>
				<div class="col-2 offset-1">
					<label for="idemail">Email : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idemail" name="semail" value="${ myUser.email }" >
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idtelephone">Telephone : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idtelephone" name="stelephone" value="${ myUser.telephone }">
				</div>
				<div class="col-2 offset-1">
					<label for="idrue">Rue : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idrue" name="srue" value="${ myUser.rue }" >
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idcodepostal">Code Postal : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idcodepostal" name="scodePostal" value="${ myUser.code_postal }">
				</div>
				<div class="col-2 offset-1">
					<label for="idville">Ville : </label>
				</div>
				<div class="col-2">
					<input type="text" id="idville" name="sville" value="${ myUser.ville }" >
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idmdpact">Mot de passe actuel : </label>
				</div>
				<div class="col-2">
					<input type="password" id="idmdpact" name="spasswordact" >
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<label for="idmdpnouv">Nouveau mot de passe : </label>
				</div>
				<div class="col-2">
					<input type="password" id="idmdpnouv" name="spasswordnew" >
				</div>
				<div class="col-2 offset-1">
					<label for="idmdpconf">Confirmation : </label>
				</div>
				<div class="col-2">
					<input type="password" id="idmdpconf" name="spasswordconf">
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 offset-2">
					<p>Crédit</p>
				</div>
				<div class="col-2">
					<p>${ myUser.credit }</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-3 offset-3">
					<button type="submit" name="supdate" value="ok">Enregistrer</button>
				</div>
				<div class="col-3">
					<button type="submit" name="sdelete" value="ok">Supprimer mon compte</button> 
				</div>
			</div>
		</form>

</body>
</html>