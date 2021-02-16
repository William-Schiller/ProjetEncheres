<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
		<link rel="stylesheet" type="text/css" href="public/css/style.css" media="screen">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link rel="shortcut icon" type="image/x-icon" href="public/images/logo-onglet.png">
		<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
		<title>${ title }</title>
	</head>
	<body>
		<header class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-1">
						</div>
						<div class="col-md-3">
							<a href="./Accueil">
								<img alt="logo" src='<c:url value="public/images/logo-EniEncheres-long.png"></c:url>'>
							</a>
						</div>
						<div class="col-md-2">
						</div>
						<div class="col-md-6 pe-5">
							<div class="row m-3 text-white">
								<div class="col-md-2 my-5">
									<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="#">Encheres</a>' }</p>
								</div>
								<div class="col-md-3 my-5">
									<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./VendreArticle">Vendre un article</a>' }</p>
								</div>
								<div class="col-md-3 my-5">
									<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./MonProfil">Mon profil</a>' }</p>
								</div>
								<div class="col-md-4 my-5">
									<p>${ empty sessionScope.myUser ? '<a class="nav-link active" href="./Connexion">S\'inscrire -<br> Se Connecter</a>' : '<a class="nav-link active"  href="./Deconnexion">Se Déconnecter</a>' }</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
		
		
