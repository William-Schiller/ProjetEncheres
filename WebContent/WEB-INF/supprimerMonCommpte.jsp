<%@ include file="/WEB-INF/fragments/header.jsp" %>

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