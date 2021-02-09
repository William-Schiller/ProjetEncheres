<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="public/css/style.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> <!-- biblio icone ?? -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<form action="<%= request.getContextPath() %>/Connexion" method="post">
			<div class="row">
				<label for="idIdentifiant">Identifiant : </label>
				<input type="text" id="idIdentifiant" name="spseudo">
			</div>
			<div class="row">
				<label for="idPassword">Mot de passe : </label>
				<input type="password" id="idPassword" name="spassword">
			</div>
			<div class="row">
				<button type="submit">Connexion</button>
				<input id="idSSDM" type="checkbox" name="sSeSouvenirDeMoi" value="true">
				<label for="idSSDM">Se souvenir de moi : </label>
				<br>
				<a href="#">Mot de passe oublié</a>
			</div>
		</form>
		
		<form action="#" method="get">
			<button type="submit">Créer un compte</button>
		</form>
	
	</body>
</html>