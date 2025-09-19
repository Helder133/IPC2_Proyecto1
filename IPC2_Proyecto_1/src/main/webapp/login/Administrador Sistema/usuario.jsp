<%-- 
    Document   : usuario
    Created on : 18/09/2025, 18:06:17
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Usuarios</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-dark text-light">
        <div class="container">
            <main>
                <jsp:include page="/includes/headerAdminSistema.jsp"/>

                <div class="container text-center mt-4 mb-4">
                    <a href="${pageContext.servletContext.contextPath}/nuevoUsuario/nuevoUsuario.jsp" 
                       class="btn btn-success shadow-lg">
                        <i class="bi bi-file-earmark-plus"></i>
                        Crear Usuario
                    </a>
                </div>

                <div class="container mb-4">
                    <form method="get" action="${pageContext.servletContext.contextPath}/ControllerUsuario" class="row g-3">
                        <div class="col-md-10">
                            <input type="text" id="id" name="id" class="form-control bg-dark text-light border-secondary" 
                                   placeholder="Buscar usuario por DPI o Pasaporte" required>
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search"></i> Buscar
                            </button>
                        </div>
                    </form>
                </div>
                <c:if test="${not empty mensajeInfo}">
                    <div class="container mt-3">
                        <div class="alert alert-info">${mensajeInfo}</div>
                    </div>
                </c:if>

                <c:if test="${empty usuarios}">
                    <div class="container">
                        <div class="alert alert-warning">No hay usuarios para mostrar.</div>
                    </div>
                </c:if>
                        
                <div class="container">
                    <c:forEach items="${usuarios}" var="usuario">
                        <div class="card bg-secondary text-light shadow-lg mb-4">
                            <div class="card-header fw-bold">
                                ${usuario.DPI_o_Pasaporte}
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${usuario.nombre}</h5>
                                <p class="card-text">
                                    <span class="fw-bold">Rol:</span> ${usuario.rol} <br/>
                                    <span class="fw-bold">Organización:</span> ${usuario.organizacion}
                                </p>
                                <a href="${pageContext.servletContext.contextPath}/ControllerUsuario?id=${usuario.DPI_o_Pasaporte}" 
                                   class="btn btn-warning w-100">
                                    <i class="bi bi-pencil-square"></i> Editar usuario
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </main>
        </div>
    </body>
</html>