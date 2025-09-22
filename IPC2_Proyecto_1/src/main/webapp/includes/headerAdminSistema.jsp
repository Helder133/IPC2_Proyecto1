<%-- 
    Document   : headerAdminSistema
    Created on : 18/09/2025, 10:56:29
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-light" href="${pageContext.servletContext.contextPath}/login/Administrador Sistema/homeAdminSistema.jsp">
            Admin Sistema
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/ControllerUsuario" class="nav-link">Usuarios</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/ControllerInstitucion" class="nav-link">Instituciones</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/controllerSistema" class="nav-link">Ajustes del sistema</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/files/upload-file.jsp" class="nav-link">Reporte</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/files/upload-file.jsp" class="nav-link">Perfil</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/ControllerLogin?action=logout" class="nav-link">Cerrar sesión</a></li>
            </ul>
        </div>
    </div>
</nav>

            