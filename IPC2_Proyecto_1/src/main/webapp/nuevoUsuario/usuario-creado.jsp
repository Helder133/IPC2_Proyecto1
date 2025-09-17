<%-- 
    Document   : usuario-creado
    Created on : 17/09/2025, 00:01:48
    Author     : helder
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="backend.modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>Usuario Registrado</title>
    </head>
    <body>
        <main>
            <div class="container">

                <div class="row">

                    <c:if test="${usuario != null}">
                        <div class="card">
                            <div class="card-header">
                                ${usuario.codigo}
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${usuario.nombre}</h5>
                                <p class="card-text"> Su registro se hizo exitosamente. Su Usuario para entrar en el sistemas es: ${usuario.DPI_o_Pasaporte}
                                </p>
                                <a href="#" class="btn btn-primary">Iniciar Sesion</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${usuario == null}">
                        <div class="alert alert-danger d-flex align-items-center">
                            <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2"></i>
                            <div>
                                ${error}!!!
                            </div> 
                        </div>
                    </c:if>

                </div>


            </div>
            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
