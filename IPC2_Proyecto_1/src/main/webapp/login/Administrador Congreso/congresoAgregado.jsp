<%-- 
    Document   : congresoAgregado
    Created on : 23/09/2025, 06:32:09
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="backend.modelos.Congreso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <title>Congreso Registrado</title>
    </head>
    <body class="bg-dark text-light">
        <main class="d-flex align-items-center justify-content-center min-vh-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-lg-6">

                        <c:if test="${congreso != null}">
                            <div class="card shadow-lg border-0 success-card text-light">
                                <div class="card-header text-center fw-bold bg-dark text-warning py-3">
                                    <i class="bi bi-check-circle-fill me-2"></i>Congreso registrado.
                                </div>
                                <div class="card-body text-center p-4">
                                    <h5 class="card-title mb-3">Registro exitoso</h5>
                                    <p class="card-text mb-4">
                                        Congreso con nombre:
                                        <span class="fw-bold text-info d-block mt-2 fs-5">${congreso.nombre}</span>
                                        Con Descripción:
                                        <span class="fw-bold text-info d-block mt-2 fs-5">${congreso.descripcion}</span>
                                        registrado exitosamente
                                    </p>
                                    <a href="${pageContext.request.contextPath}/ControllerCongreso" class="btn btn-warning w-100 py-2">
                                        <i class="bi bi-box-arrow-in-right me-2"></i> Regresar
                                    </a>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${congreso == null}">
                            <div class="alert error-alert text-light p-4" role="alert">
                                <div class="d-flex align-items-center mb-3">
                                    <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2 fs-4 text-danger"></i>
                                    <div class="fw-bold fs-5">
                                        ${error} !!!
                                    </div>
                                </div>
                                <div class="text-center mt-3">
                                    <a href="${pageContext.request.contextPath}/login/Administrador Congreso/agregarCongreso.jsp"
                                       class="btn btn-warning w-100 py-2">
                                        <i class="bi bi-arrow-left-circle me-2"></i> Regresar
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