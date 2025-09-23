<%-- 
    Document   : agregarCongreso
    Created on : 23/09/2025, 05:01:12
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Usuario</title>
        <jsp:include page="/includes/resources.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registro.css" />
    </head>
    <body class="bg-dark text-light">
        <div class="centered-form">
            <div class="register-card">
                <h4 class="text-center mb-4 text-white">Registro de Congreso</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/ControllerCongreso" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="El uso de IA xD" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción del Congreso</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="El uso de ia esta prohibido" required>
                    </div>

                    <div class="mb-3">
                        <label for="number" class="form-label">Precio</label>
                        <input type="number" step="0.01" class="form-control" id="precio" name="precio" placeholder="35.00" value="35.00" required>
                    </div>

                    <div class="mb-3">
                        <label for="fecha inicio" class="form-label">Fecha Inicio</label>
                        <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" placeholder="yyyy-mm-dd" required>
                    </div>

                    <div class="mb-3">
                        <label for="fecha fin" class="form-label">Fecha Fin</label>
                        <input type="date" class="form-control" id="fechaFin" name="fechaFin" placeholder="yyyy-mm-dd" required>
                    </div>

                    <div class="select-row mb-3">

                        <label for="convocatoria" class="form-label">Convocatoria para trabajos</label>
                        <select id="convocatoria" name="convocatoria" class="form-control">
                            <option value="Habilitado">Habilitado</option>
                            <option value="Deshabilitado">Deshabilitado</option>
                        </select>
                    </div>
                    
                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Registrarse</button>
                </form>
                <div class="text-center mt-3 small">
                    <a href="${pageContext.request.contextPath}/ControllerCongreso" class="text-info text-decoration-none">Regresar</a>
                </div>
            </div>
        </div>
    </body>
</html>