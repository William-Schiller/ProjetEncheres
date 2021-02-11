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

			<div class="row text-center">
				<h1>Voulez vous vraiment supprimer votre compte ?</h1>
			</div>

		<form action="./SuppressionCompte" method="post">
			<div class="row">
				<div class="col-3 offset-3">
					<button type="submit" name="sfinaldelete" value="no">Annuler</button>
				</div>
				<div class="col-3">
					<button type="submit" name="sfinaldelete" value="ok">Valider</button> 
				</div>
			</div>
		</form>
			
</body>
</html>