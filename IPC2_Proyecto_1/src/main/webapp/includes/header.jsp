<%-- 
    Document   : header
    Created on : Aug 27, 2025, 4:44:14 PM
    Author     : jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container"> 
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom"> 
        <ul class="nav nav-pills"> 
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/login/Administrador Sistema/homeAdminSistema.jsp" class="nav-link active" aria-current="page">Home</a>
            </li> 
            <li class="nav-item"><a href="#" class="nav-link">Usuarios</a></li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/eventos/eventos-servlet" class="nav-link"></a>Instituciones</li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/eventos/ajax/listado-ajax.jsp" class="nav-link">Ajustes del sistema</a></li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/files/upload-file.jsp" class="nav-link">Reporte</a></li>
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/files/upload-file.jsp" class="nav-link">Perfil</a></li>
            <li class="nav-item"><a href="#" class="nav-link">About</a></li>
        </ul> 
    </header>
</div>

