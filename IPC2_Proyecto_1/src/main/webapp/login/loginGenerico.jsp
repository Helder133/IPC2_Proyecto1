<%-- 
    Document   : loginGenerico
    Created on : 17/09/2025, 22:49:56
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="backend.modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <title>Usuario Registrado</title>
    </head>
    <body class="bg-dark text-light">
        <main class="d-flex align-items-center justify-content-center min-vh-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6">

                        <c:if test="${usuario != null}">
                            <div class="card shadow-lg border-0 rounded-3 bg-secondary text-light">
                                <div class="card-header text-center fw-bold bg-dark text-warning">
                                    Usuario registrado: ${usuario.DPI_o_Pasaporte}
                                </div>
                                <div class="card-body text-center">
                                    <h5 class="card-title">${usuario.nombre}</h5>
                                    <p class="card-text">
                                        Inicio de sesion exitosamente:
                                        <span class="fw-bold text-info">${usuario.rol}</span>
                                    </p>
                                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-warning w-100">
                                        <i class="bi bi-box-arrow-in-right"></i> Iniciar Sesión
                                    </a>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${usuario == null}">
                            <div class="alert alert-danger shadow-lg p-4" role="alert">
                                <div class="d-flex align-items-center mb-3">
                                    <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2 fs-4"></i>
                                    <div class="fw-bold">
                                        ${error} !!!
                                    </div>
                                </div>
                                <div class="text-center">
                                    <a href="${pageContext.request.contextPath}/index.jsp"
                                       class="btn btn-warning w-100">
                                        <i class="bi bi-arrow-left-circle"></i> Regresar
                                    </a>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
