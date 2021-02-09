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
	<h1>ACCUEIL</h1>
	
	<h2>${ sessionScope.myUser.pseudo }</h2>
	<h2>${ sessionScope.myUser.nom }</h2>
	<h2>${ sessionScope.myUser.prenom }</h2>

</body>
</html>