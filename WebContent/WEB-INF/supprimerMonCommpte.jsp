<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<div class="container pt-4">
		<div class="row">
			<div class="col-md-1"></div>
				<div class="row text-center">

					<div class="row text-center">
						<h1>Voulez vous vraiment supprimer votre compte ?</h1>
					</div>
		
					<form action="./SuppressionCompte" method="post">
						<div class="row mt-4">
							<div class="col-3 offset-3">
								<button class="btn btn-secondary" type="submit" name="sfinaldelete" value="no">Annuler</button>
							</div>
							<div class="col-3">
								<button class="btn btn-primary me-2" type="submit" name="sfinaldelete" value="ok">Valider</button> 
							</div>
						</div>
					</form>
				
				</div>
			</div>
		</div>
			
	</body>
</html>