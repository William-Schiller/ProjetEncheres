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
				<p>${ empty sessionScope.myUser ? '<a href="./Connexion">S\'inscrire - Se Connecter</a>' : '<a href="./Deconnexion">Se Déconnecter</a>' }</p>
			</div>
		</div>
	</header>
	<h1>ACCUEIL</h1>
	
	<h2>${ sessionScope.myUser.pseudo } </h2>
	<h2>${ sessionScope.myUser.nom } </h2>
	<h2>${ sessionScope.myUser.prenom } </h2>
	

</body>
</html>