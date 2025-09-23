<%-- 
    Document   : headerAdminCongreso
    Created on : 22/09/2025, 20:14:18
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-light" href="${pageContext.servletContext.contextPath}/login/Administrador Congreso/homeAdminCongreso.jsp">
            Admin Congreso
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/controllerUsuarioAC" class="nav-link">Usuarios</a></li>
                <li class="nav-item"><a class="nav-link">Salones</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/ControllerCongreso" class="nav-link">Congresos</a></li>
                <li class="nav-item"><a class="nav-link">Comite Cientifico</a></li>
                <li class="nav-item"><a class="nav-link">Actividades</a></li>
                <li class="nav-item"><a class="nav-link">Trabajos aprobados</a></li>
                <li class="nav-item"><a  class="nav-link">Reporte</a></li>
                <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/controllerPerfil" class="nav-link">Perfil</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/ControllerLogin?action=logout" class="nav-link">Cerrar sesión</a></li>
            </ul>
        </div>
    </div>
</nav>

