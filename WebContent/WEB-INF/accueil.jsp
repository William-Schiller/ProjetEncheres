<%@ include file="/WEB-INF/fragments/header.jsp" %>
	
	
	<h1>ACCUEIL</h1>
	
	<a href="./VendreArticle">Vendre un article !!!</a>
	
	
	<h2>${ sessionScope.myUser.pseudo } </h2>
	<h2>${ sessionScope.myUser.nom } </h2>
	<h2>${ sessionScope.myUser.prenom } </h2>
	

</body>
</html>