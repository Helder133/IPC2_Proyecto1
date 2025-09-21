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
        <jsp:include page="/includes/headerAdminSistema.jsp"/>

        <div class="container py-4">
            <div class="d-flex justify-content-center mb-4">
                <a href="${pageContext.servletContext.contextPath}/login/Administrador Sistema/agregarUsuario.jsp" 
                   class="btn btn-success btn-lg">
                    <i class="bi bi-file-earmark-plus me-2"></i>
                    Crear Usuario
                </a>
            </div>

            <div class="row justify-content-center mb-4">
                <div class="col-lg-10">
                    <form method="get" action="${pageContext.servletContext.contextPath}/ControllerUsuario" class="row g-2">
                        <div class="col-md-10 col-sm-8">
                            <input type="text" id="id" name="id" class="form-control bg-secondary text-light border-dark" 
                                   placeholder="Buscar usuario por DPI o Pasaporte y nombre" required>
                        </div>
                        <div class="col-md-2 col-sm-4">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search me-2"></i>Buscar
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${empty usuarios}">
                <div class="row justify-content-center">
                    <div class="col-lg-10">
                        <div class="alert alert-warning">
                            No hay usuarios para mostrar.
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach items="${usuarios}" var="usuario">
                    <div class="col">
                        <div class="card h-100 bg-secondary text-light border-light user-card">
                            <div class="card-header fw-bold">
                                ${usuario.DPI_o_Pasaporte}
                            </div>
                            <div class="card-body d-flex flex-column">
                                <img src="${pageContext.request.contextPath}/${empty usuario.foto?"fotos/perfil.jpeg":usuario.foto}" alt="Foto de ${usuario.nombre}" 
                                     class="img-fluid rounded-circle mb-3" 
                                     style="max-width:80px;">
                                <h5 class="card-title">${usuario.nombre}</h5>
                                <p class="card-text flex-grow-1">
                                    <span class="fw-bold">Rol:</span> ${usuario.rol} <br/>
                                    <span class="fw-bold">Organización:</span> ${usuario.organizacion} <br/>
                                    <span class="fw-bold">Estado:</span> ${usuario.estado == true ? "Habilitado" : "Deshabilitado"}
                                </p>
                                <a href="${pageContext.servletContext.contextPath}/ControllerUsuarioActualizadoAdminS?id=${usuario.DPI_o_Pasaporte}" 
                                   class="btn btn-warning mt-auto">
                                    <i class="bi bi-pencil-square me-2"></i>Editar usuario
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>