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
        <title>Administrador Congreso</title>
    </head>
    <body class="bg-dark text-light">
        <jsp:include page="/includes/headerAdminCongreso.jsp"/>

        <div class="container mt-4 mb-5">
            <div class="card shadow-lg welcome-card text-light">
                <div class="card-body p-4">
                    <h4 class="card-title text-center mb-4"> 
                        <%
                        String nombre = (String) session.getAttribute("nombre");
                        %> 
                        <strong class="text-info"><%= nombre %></strong> Bienvenido al sistema de Administrador 
                    </h4>
                    <p class="mb-4 text-center">
                        Desde aquí puede realizar las siguientes gestiones:
                    </p>

                    <ol class="list-group list-group-numbered">
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Salon</div>
                                Registrara salones donde se desarrollaran las diferentes actividades que va haber en los congresos, así 
                                también podra actualizar y eliminar los salones.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Usuario</div>
                                Registre usuarios en el sistema. Cada usuario tiene un rol, y según su rol podrá visualizar y realizar distintas acciones.
                                Podra modificarlos y deshabilitarlos si lo desea.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Agregar Comite Cientifico</div>
                                Registra a los diferentes usuarios que conforman el comite cientifico del cogreso. 
                                El comite cientifico es el encargado de aceptar lss diferentes trabajos propuestos por los usuarios.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Congreso</div>
                                Registra todos los congresos que vallan haber, asi como tambien puede modificarlos.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Agregar actividades</div>
                                Aqui podra agregar las diferentes actividades que se va a desarrollar durante el congreso.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Trabajos aceptados</div>
                                Aqui se le mostraran todos los trabajos que el comite cientifico han aprobado para poder ser convertidos 
                                en actividades.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Reporte</div>
                                Visualice los reportes de ganancias generadas y filtre por distintos criterios para un mejor análisis.
                            </div>
                        </li>
                        <li class="list-group-item bg-dark text-light d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold text-warning">Perfil</div>
                                Actualice los datos de su perfil de acuerdo con sus preferencias.
                            </div>
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </body>
</html>