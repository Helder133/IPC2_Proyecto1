<%-- 
    Document   : actualizarConfig
    Created on : 22/09/2025, 04:18:36
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Configuración</title>
        <jsp:include page="/includes/resources.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registro.css" />
    </head>
    <body class="bg-dark text-light">
        <div class="centered-form">
            <div class="register-card">
                <h4 class="text-center mb-4 text-white">Registro de institución</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/controllerSistema" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="number" class="form-label">Porcentaje de comicion: </label>
                        <input type="hidden" class="form-control" id="id" name="id" value="${configuracion.id}">
                        <input type="number" step="0.01" class="form-control" id="porcentaje" name="porcentaje" placeholder="15.00" value="${configuracion.porcentajeComicion * 100}" required>
                    </div>

                    <div class="mb-3">
                        <label for="number" class="form-label">Precio minimo por crengreso</label>
                        <input type="number" step="0.01" class="form-control" id="minimo" name="minimo" placeholder="35.00" value="${configuracion.precioMinimo}" required>
                    </div>

                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Actualizar Configuración</button>
                </form>
                <div class="text-center mt-3 small">
                    <a href="${pageContext.request.contextPath}/controllerSistema" class="text-info text-decoration-none">Regresar</a>
                </div>
            </div>
        </div>
    </body>
</html>