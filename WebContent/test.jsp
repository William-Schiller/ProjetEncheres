<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
 <%@ page import="fr.eni.projetEncheres.bll.UtilisateurManager" %>
 <%@ page import="fr.eni.projetEncheres.bean.Utilisateur"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<%

  Utilisateur u = new Utilisateur(); // creer un nouvel utilisateur java 

 UtilisateurManager um = UtilisateurManager.getInstance();  // recuperer une instance de UtilisateurManager
 //si tu veux tester tes methode DAL tu pourras creer une nouvelle instance de JDBCImpl, exemple
 // categorieDAOJDBCImpl categorieDAO = new CategorieDAOJDBCImpl();
 //categorieDAO.NOMDEMAMETHODE
  u = um.connexionUser("robertu ","motdepasse"); //recuperer l'utilisateur en BDD grace a la methode BLL
  


%>


    <h1><% u.getNom()%></h1>


</body>
</html>