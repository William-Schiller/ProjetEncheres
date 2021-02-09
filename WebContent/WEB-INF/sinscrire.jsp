<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
		<link rel="stylesheet" type="text/css" href="/public/css/style.css" media="screen">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<style>.form-row , .form-group {width:50%}</style>
		<title>S'inscrire</title>
	</head>
	<body>
		
		<form method="POST">
			<div class="form-row">
				<div class="form-group">
					<div class="form-input">
						<label class="control-label col-sm-2">Pseudo : </label>
						<input class="form-control" type="text" name="spseudo" value="<%=request.getParameter("spseudo") %>" >
					</div>
					<div class="form-input"></div>
				</div>
			</div>
		</form>
		
		
		
		
		
		
		
		
		
		<h1 class="p-3 text-center">S'INSCRIRE</h1>
		<h2 class="text-center">Mon profil</h2>
		<div class="container-fluid">
		
			<div class="row">
				<div class="col-md-2"></div>
				<form role="form" class="form-horizontal row col-md-8 pt-4" action="<%=request.getContextPath() %>/inscription" method="post">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-2">Pseudo : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="spseudo" value="<%=request.getParameter("spseudo") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Prénom : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="sprenom" value="<%=request.getParameter("sprenom") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Téléphone : </label>
							<div class="col-sm-7">
								<input class="form-control" type="tel" name="stelephone" value="<%=request.getParameter("stelephone") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Code postal : </label>
							<div class="col-sm-7">
								<input class="form-control" type="number" name="spostal" value="<%=request.getParameter("spostal") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Mot de passe : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="smdp" value="<%=request.getParameter("smdp") %>" >
							</div>
						</div> 
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-2">Nom : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="snom" value="<%=request.getParameter("snom") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Email : </label>
							<div class="col-sm-7">
								<input class="form-control" type="email" name="semail" value="<%=request.getParameter("semail") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Rue : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="srue" value="<%=request.getParameter("srue") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Ville : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="sville" value="<%=request.getParameter("sville") %>" > 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Confirmation : </label>
							<div class="col-sm-7">
								<input class="form-control" type="text" name="sconfirmation" value="<%=request.getParameter("sconfirmation") %>" > 
							</div>
						</div>
					</div>
					<div class="row p-3"></div>
					<div class="text-center">
						<div>
							<input type="submit" value="Creer" class="btn btn-success">
							<a href="<%=request.getContextPath()%>">
								<input type="button" value="Annuler" class="btn btn-danger">
							</a>
						</div>
					</div>
				</form>
				<div class="col-md-2"></div>
			</div>
		</div>
	</body>
</html>