<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<main>
		<div class="container-fluid" id="bg" style="padding: 12% 18%">
			<div class="row text-center p-5" id="form">
				<h1 style="font-size: 2em">Voulez vous vraiment supprimer votre compte ?</h1>
				<form action="./SuppressionCompte" method="post">
					<div class="row mt-4 justify-content-center">
						<div class="col-2 me-2">
							<button class="btn btn-secondary" id="btn-rouge" type="submit" name="sfinaldelete" value="no">Annuler</button>
						</div>
						<div class="col-2 ms-2">
							<button class="btn btn-primary me-2" id="btn-vert" type="submit" name="sfinaldelete" value="ok">Valider</button> 
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>

<%@ include file="/WEB-INF/fragments/footer.jsp" %>