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
		<title>${ title }</title>
	</head>
	
	<body>
		<div class="text-danger row text-center">
			<c:forEach var="item" items="${ listError }">
				<p>${ item }</p>	
			</c:forEach>
		</div>
		
		<form action="<%= request.getContextPath() %>/Connexion" method="post">
			<div class="row">
				<div class="col-3 offset-3">
					<label for="idIdentifiant">Identifiant : </label>
				</div>
				<div class="row col-3">
					<input type="text" id="idIdentifiant" name="spseudo" value="${ empty pseudo? (empty pseudoError? '' : pseudoError ) : pseudo }">
				</div>
			</div>
			<div class="row">
				<div class="col-3 offset-3">
					<label for="idPassword">Mot de passe : </label>
				</div>
				<div class="row col-3">
					<input type="password" id="idPassword" name="spassword" placeholder="${ empty passwordError? '' : passwordError }" >
				</div>
			</div>
			<div class="row">
				<div class="col-3 offset-3">
					<button type="submit">Connexion</button>
				</div>
				<div class="col-3">
					<input id="idSSDM" type="checkbox" name="sSeSouvenirDeMoi" value="true">
					<label for="idSSDM">Se souvenir de moi : </label>
					<br>
					<a href="#">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		
		<div class="row col-6 offset-3">
			<form action="./inscription" method="get">
				<button type="submit">Créer un compte</button>
			</form>
		</div>
	</body>
</html>