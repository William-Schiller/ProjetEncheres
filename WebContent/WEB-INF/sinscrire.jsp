<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
		<link rel="stylesheet" type="text/css" href="/public/css/style.css" media="screen">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
		<style>body{font-family: 'Roboto', sans-serif}h1{font-weight: bold}h2{text-decoration: underline}</style>
		<title>S'inscrire</title>
	</head>
	<body>
        <div class="container">
        	<div class="mb-5"></div>
            <div class="row text-center">
                <h1>S'inscrire</h1>
                <br>
                <h2>Mon profil</h2>
                <c:forEach var="item" items="${ListeErreurs}">
                	<p class="my-1" style="color:red">${item}</p>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <form class="row" action="<%= request.getContextPath() %>/inscription" method="post">
                        <div class="col-md-6 p-3 mb-4">
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="pseudo" placeholder="Pseudo" aria-label="Pseudo" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="prenom" placeholder="Prénom" aria-label="Prenom" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="telephone" placeholder="Telephone" aria-label="Telephone" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="number" class="form-control" name="postal" placeholder="Code postal" aria-label="Code postal" aria-describedby="basic-addon1">
                            </div>
                            
                            <div class="input-group mb-3">
                            <input type="password" class="form-control" name="mdp" placeholder="Mot de passe" aria-label="Mot de passe" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-md-6 p-3 mb-4">
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="nom" placeholder="Nom" aria-label="Nom" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="email" class="form-control" name="email" placeholder="Email" aria-label="Email" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="rue" placeholder="Rue" aria-label="Rue" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="text" class="form-control" name="ville" placeholder="Ville" aria-label="Ville" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                            <input type="password" class="form-control" name="confirmation" placeholder="Confirmation" aria-label="Confirmation" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="text-center">
                            <div>
                                <input class="btn btn-success me-2" type="submit" value="Creer">
                                <a href="<%=request.getContextPath()%>">
                                    <input class="btn btn-danger ms-2" type="button" value="Annuler">
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
	</body>
</html>