<%-- 
    Document   : homeAdminSistema
    Created on : 18/09/2025, 10:57:04
    Author     : helder
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>Administrador Sistema</title>
    </head>
    <body class="bg-dark text-light">
        <main>
            <jsp:include page="/includes/headerAdminSistema.jsp"/>

            <div class="container mt-4">
                <div class="card shadow-lg bg-secondary text-light">
                    <div class="card-body">
                        <h4 class="card-title text-center mb-4"> 
                            <%
                            String nombre = (String) session.getAttribute("nombre");
                            %> 
                            <strong><%= nombre %></strong> Bienvenido al sistema de Administrador </h4>
                        <p class="mb-4">
                            Desde aquí puede realizar las siguientes gestiones:
                        </p>

                        <ol class="list-group list-group-numbered">
                            <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold">Agregar Institución</div>
                                    Administre las instituciones que organizan los congresos y sirven como sede para llevarlos a cabo.
                                </div>
                            </li>
                            <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold">Agregar Usuario</div>
                                    Registre usuarios en el sistema. Cada usuario tiene un rol, y según su rol podrá visualizar y realizar distintas acciones.
                                </div>
                            </li>
                            <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold">Ajustes del sistema</div>
                                    Modifique los parámetros del sistema, como la comisión generada por cada congreso.
                                </div>
                            </li>
                            <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold">Reporte</div>
                                    Visualice los reportes de ganancias generadas y filtre por distintos criterios para un mejor análisis.
                                </div>
                            </li>
                            <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold">Perfil</div>
                                    Actualice los datos de su perfil de acuerdo con sus preferencias.
                                </div>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>