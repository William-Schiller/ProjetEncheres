<%@ include file="/WEB-INF/fragments/header.jsp" %>
	
	
	<h1>Details vente</h1>
	
	<div class="input-group mb-3">
    	<input type="text" class="form-control" name="no_enchere" placeholder="no_enchere" aria-label="no_enchere">
    </div>
    <input class="btn btn-success me-2" type="submit" value="Enchérir">
    <c:forEach var="item" items="${listeDesErreurs}">
        <p class="my-3" style="color:red">${item}</p>
    </c:forEach>
	
	
	
  </body>
</html>