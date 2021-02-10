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
		<style>#inscription {display:flex}</style>
		<title>S'inscrire</title>
	</head>
	<body>
		<div class="row text-center">
			<h1>S'inscrire</h1>
			<h2>Mon profil</h2>
		</div>
		<form method="POST">
			<div class="m-4"></div>
			<div class="form-row row">
                <div class="col-md-3"></div>
                <div class="col-md-6" id="inscription">
                    <div class="form-group col-md-6">
                        <div class="form-input">
                            <label class="required" for="pseudo">Pseudo : </label>
                            <input class="error" type="text" name="speudo" aria-invalid="true" value="<%=request.getParameter("pseudo") %>">
                        </div>
                        <div class="form-input">
                            <label class="required" for="prenom">Prénom : </label>
                            <input class="error" type="text" name="prenom" aria-invalid="true" value="<%=request.getParameter("prenom") %>" > 
                        </div>
                        <div class="form-input">
                            <label class="required" for="telephone">Téléphone : </label>
                            <input class="error" type="text" name="telephone" aria-invalid="true" value="<%=request.getParameter("telephone") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="postal">Code Postal : </label>
                            <input class="error" type="text" name="postal" aria-invalid="true" value="<%=request.getParameter("postal") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="mdp">Mot de Passe : </label>
                            <input class="error" type="text" name="mdp" aria-invalid="true" value="<%=request.getParameter("mdp") %>" >
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="form-input">
                            <label class="required" for="nom">Nom : </label>
                            <input class="error" type="text" name="nom" aria-invalid="true" value="<%=request.getParameter("nom") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="email">Email : </label>
                            <input class="error" type="text" name="email" aria-invalid="true" value="<%=request.getParameter("email") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="rue">Rue : </label>
                            <input class="error" type="text" name="rue" aria-invalid="true" value="<%=request.getParameter("rue") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="ville">Ville : </label>
                            <input class="error" type="text" name="ville" aria-invalid="true" value="<%=request.getParameter("ville") %>" >
                        </div>
                        <div class="form-input">
                            <label class="required" for="confirmation">Confirmation : </label>
                            <input class="error" type="text" name="confirmation" aria-invalid="true" value="<%=request.getParameter("confirmation") %>" >
                        </div>
                    </div>
                </div>
				<div class="col-md-3"></div>
			</div>
			<div class="m-4"></div>
			<div class="text-center">
				<div>
					<input type="submit" value="Creer" class="btn btn-success">
					<a href="<%=request.getContextPath()%>">
						<input type="button" value="Annuler" class="btn btn-danger">
					</a>
				</div>
			</div>
		</form>

	</body>
</html>