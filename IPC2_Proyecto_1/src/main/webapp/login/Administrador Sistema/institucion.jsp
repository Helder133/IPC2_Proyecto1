<%-- 
    Document   : institucion
    Created on : 18/09/2025, 18:06:41
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Intituciones</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-dark text-light">
        <jsp:include page="/includes/headerAdminSistema.jsp"/>

        <div class="container py-4">
            <div class="d-flex justify-content-center mb-4">
                <a href="${pageContext.servletContext.contextPath}/login/Administrador Sistema/agregarInstitucion.jsp" 
                   class="btn btn-success btn-lg">
                    <i class="bi bi-file-earmark-plus me-2"></i>
                    Crear Institucion
                </a>
            </div>

            <div class="row justify-content-center mb-4">
                <div class="col-lg-10">
                    <form method="get" action="${pageContext.servletContext.contextPath}/ControllerInstitucion" class="row g-2">
                        <div class="col-md-10 col-sm-8">
                            <input type="text" id="nombre" name="nombre" class="form-control bg-secondary text-light border-dark" 
                                   placeholder="Buscar nombre (sensible a mayusculas y minisculas)" required>
                        </div>
                        <div class="col-md-2 col-sm-4">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search me-2"></i>Buscar
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${empty instituciones}">
                <div class="row justify-content-center">
                    <div class="col-lg-10">
                        <div class="alert alert-warning">
                            No hay instituciones para mostrar.
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach items="${instituciones}" var="institucion">
                    <div class="col">
                        <div class="card h-100 bg-secondary text-light border-light user-card">
                            <div class="card-header fw-bold">
                                ${institucion.id}
                            </div>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${institucion.nombre}</h5>
                                <p class="card-text flex-grow-1">
                                    <span class="fw-bold">Direccion:</span> ${institucion.direccion} <br/>
                                    <span class="fw-bold">Telefono:</span> ${institucion.telefono} <br/>
                                    <span class="fw-bold">Email:</span> ${institucion.email}
                                </p>
                                <a href="${pageContext.servletContext.contextPath}/ControllerInstitucionActualizarYEliminar?id=${institucion.id}" 
                                   class="btn btn-outline-warning mt-auto">
                                    <i class="bi bi-pencil-square me-2"></i>Editar Institucion
                                </a>
                                   <!-- Para eliminar -->
                                <form method="post" action="${pageContext.servletContext.contextPath}/ControllerInstitucionActualizarYEliminar" enctype="multipart/form-data">
                                    <input type="hidden" name="id" value="${institucion.id}" />
                                    <input type="hidden" name="opcion" value="eliminar" />
                                    <button type="submit" class="btn btn-outline-danger mt-auto">
                                        <i class="bi bi-exclamation-triangle-fill"></i>Eliminar Institución
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>