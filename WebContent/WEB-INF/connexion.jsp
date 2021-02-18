<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<main>
		<div class="container-fluid" id="bg" style="padding: 10% 20%;">
                <div class="row" id="box-form">
                    <div class="p-4 justify-content" id="form">
                        <div class="row mb-4">
                            <h1 class="text-center">Connexion</h1>
                            <div class="container pt-4">
                                <div class="row" id="connexion-comp">
                                    <div class="col-md-7">
                                        <div class="text-danger row"></div>
                                        <div class="row justify-mobile">
                                            <form class="row" action="<%= request.getContextPath() %>/Connexion" method="post">
                                                <div class="col-md-7">
                                                    <div class="row" style="margin: 2%;">
                                                        <input class="form-control" style="margin-bottom: 10px;" type="text" id="idIdentifiant" name="spseudo" placeholder="Pseudo">
                                                        <input class="form-control" type="password" id="idPassword" name="spassword" placeholder="Mot de passe" >
                                                    </div>
                                                </div>
                                                <div class="col-md-5 textcenter-mobile">
                                                    <div style="margin-bottom: 5%;">
                                                        <input class="form-check-input" id="idSSDM" type="checkbox" name="sSeSouvenirDeMoi" value="true">
                                                        <label class="form-check-label label-connexion" for="idSSDM">
                                                            Se souvenir de moi 
                                                        </label>
                                                        <br>
                                                        <a href="#">
                                                            Mot de passe oublié
                                                        </a>
                                                    </div>
                                                    <div class="mb-3">
                                                        <button class="btn btn-success" id="btn-vert" type="submit">Connexion</button>
                                                    </div>
                                                </div>   
                                            </form>
                                        </div>
                                    </div>
                                    <div style="width: 1px;padding: 0;background-color: #fff;"></div>
                                    <div class="col-md-4 text-center" style="margin: auto 0;">
                                        <p>Ou alors</p>
                                        <form action="./inscription" method="get">
                                            <button class="btn btn-primary" id="btn-vert" type="submit">Créer un compte</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	</main>
	
	
<%@ include file="/WEB-INF/fragments/footer.jsp" %>