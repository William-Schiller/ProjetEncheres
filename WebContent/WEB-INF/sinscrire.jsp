
<%@ include file="/WEB-INF/fragments/header.jsp" %>

	<main>
        <div class="container-fluid" id="bg">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 p-4" id="form">
                    <div class="row text-center">
                        <h1>Cr�er un compte</h1>
                        <br>
                        <h2>Mon profil</h2>
                        <c:forEach var="item" items="${listeDesErreurs}">
                            <p class="my-3" style="color:red">${item}</p>
                        </c:forEach>
                    </div>
                    <div class="row mb-4" id="form-sinscrire">
                        <form class="row" action="<%= request.getContextPath() %>/inscription" method="post">
                            <div class="col-md-6 p-3 mb-4">
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="pseudo" placeholder="Pseudo" value="${ empty pseudoForm ? '' : pseudoForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="prenom" placeholder="Pr�nom" 
                                	aria-label="Prenom" aria-describedby="basic-addon1" value="${ empty prenomForm? '' : prenomForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="telephone" placeholder="T�l�phone" 
                                	aria-label="Telephone" aria-describedby="basic-addon1" value="${ empty telephoneForm? '' : telephoneForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="number" class="form-control" name="postal" placeholder="Code postal" 
                                	aria-label="Code postal" aria-describedby="basic-addon1" value="${ empty postalForm? '' : postalForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="password" class="form-control" name="mdp" placeholder="Mot de passe" 
                                	aria-label="Mot de passe" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="col-md-6 p-3 mb-4">
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="nom" placeholder="Nom" aria-label="Nom" 
                                	aria-describedby="basic-addon1" value="${ empty nomForm? '' : nomForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="email" class="form-control" name="email" placeholder="Email" 
                                	aria-label="Email" aria-describedby="basic-addon1" value="${ empty emailForm? '' : emailForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="rue" placeholder="Rue" 
                                	aria-label="Rue" aria-describedby="basic-addon1" value="${ empty rueForm? '' : rueForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="ville" placeholder="Ville" 
                                	aria-label="Ville" aria-describedby="basic-addon1" value="${ empty villeForm? '' : villeForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="password" class="form-control" name="confirmation" 
                                	placeholder="Confirmation" aria-label="Confirmation" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="text-center">
                                <div>
                                    <input class="btn btn-success me-2" type="submit" value="Creer">
                                    <a href="<%=request.getContextPath()%>">
                                        <input class="btn btn-danger ms-2" type="button" value="Annuler">
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </main>

<%@ include file="/WEB-INF/fragments/footer.jsp" %>
