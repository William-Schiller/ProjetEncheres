<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
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
				<p></p>
			</div>
			<div class="col-md-2">
				<p>${ empty sessionScope.myUser ? '<a href="./Connexion">Se Connecter</a>' : '<a href="./Deconnexion">Se Déconnecter</a>' }</p>
			</div>
		</div>
	</header>
	
	<h1>ACCUEIL</h1>
	
	<h2>${ sessionScope.myUser.pseudo }</h2>
	<h2>${ sessionScope.myUser.nom }</h2>
	<h2>${ sessionScope.myUser.prenom }</h2>

</body>
</html>