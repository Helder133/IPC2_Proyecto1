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
            <jsp:include page="/includes/header.jsp"/>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6">
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
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
