<%-- 
    Document   : header
    Created on : Aug 27, 2025, 4:44:14 PM
    Author     : jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container"> 
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom"> 
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"> 
            <i class="bi bi-bootstrap" style="font-size: 2rem;"></i>
            <span class="fs-4">Simple header</span> 
        </a>
        <ul class="nav nav-pills"> 
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/index.jsp?valor=55&opcion=1&num1=7&num2=3" class="nav-link active" aria-current="page">Home</a>
            </li> 
            <li class="nav-item"><a href="#" class="nav-link">Usuarios</a></li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/eventos/eventos-servlet" class="nav-link">Eventos</a></li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/eventos/ajax/listado-ajax.jsp" class="nav-link">Eventos Ajax</a></li> 
            <li class="nav-item"><a href="${pageContext.servletContext.contextPath}/mvc/files/upload-file.jsp" class="nav-link">Files</a></li>
            <li class="nav-item"><a href="#" class="nav-link">About</a></li>
        </ul> 
    </header>
</div>

