<%-- 
    Document   : ajustesSistema
    Created on : 18/09/2025, 18:03:34
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajustes Sistema</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-dark text-light">
        <jsp:include page="/includes/headerAdminSistema.jsp"/>

        <div class="container py-4">
            <c:if test="${empty configuraciones}">
                <div class="row justify-content-center">
                    <div class="col-lg-10">
                        <div class="alert alert-warning">
                            No hay configuracion para mostrar.
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach items="${configuraciones}" var="configuracion">
                    <div class="col">
                        <div class="card h-100 bg-secondary text-light border-light user-card">
                            <div class="card-header fw-bold">
                                ${configuracion.id}
                            </div>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">Configuracion del sistema</h5>
                                <p class="card-text flex-grow-1">
                                    <span class="fw-bold">Porcentaje de comision: </span> ${configuracion.porcentajeComicion * 100}<br/>
                                    <span class="fw-bold">Precio minimo por congreso: </span> ${configuracion.precioMinimo}
                                </p>
                                <a href="${pageContext.servletContext.contextPath}/controllerSistema?id=${configuracion.id}" 
                                   class="btn btn-outline-warning mt-auto">
                                    <i class="bi bi-pencil-square me-2"></i>Editar Institucion
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>