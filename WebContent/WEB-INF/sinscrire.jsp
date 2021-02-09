<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	<title>S'inscrire</title>
</head>
<body>
	<h1>S'INSCRIRE</h1>
	<div class="container-fluid">
		<div class="row">
			<h2>Mon profil</h2>
			<div>
				<form class="row position-absolute top-50 start-50 translate-middle" action="<%=request.getContextPath() %>/inscription" method="post">
					<div class="col-md-6">
						<div>
							Pseudo : <input type="text" name="spseudo" value="<%=request.getParameter("spseudo") %>" > 
						</div>
						<div>
							Prénom : <input type="text" name="sprenom" value="<%=request.getParameter("sprenom") %>" > 
						</div>
						<div>
							Téléphone : <input type="tel" name="stelephone" value="<%=request.getParameter("stelephone") %>" > 
						</div>
						<div>
							Code postal : <input type="number" name="spostal" value="<%=request.getParameter("spostal") %>" > 
						</div>
						<div>
							Mot de passe : <input type="text" name="smdp" value="<%=request.getParameter("smdp") %>" > 
						</div>
					</div>
					<div class="col-md-6">
						<div>
							Nom : <input type="text" name="snom" value="<%=request.getParameter("snom") %>" > 
						</div>
						<div>
							Email : <input type="email" name="semail" value="<%=request.getParameter("semail") %>" > 
						</div>
						<div>
							Rue : <input type="text" name="srue" value="<%=request.getParameter("srue") %>" > 
						</div>
						<div>
							Ville : <input type="text" name="sville" value="<%=request.getParameter("sville") %>" > 
						</div>
						<div>
							Confirmation : <input type="text" name="sconfirmation" value="<%=request.getParameter("sconfirmation") %>" > 
						</div>
					</div>
					<div class="row p-3"></div>
					<div class="row">
					<div class="col-md-4">
					</div>
					<div class="col-md-4 ">
						<input type="submit" value="Creer">
						<a href="<%=request.getContextPath()%>">
							<input type="button" value="Annuler">
						</a>
					</div>
					<div class="col-md-4">
					</div>
				</div>
					<div>
						
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>