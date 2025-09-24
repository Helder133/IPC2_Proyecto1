<%-- 
    Document   : agregarSalon
    Created on : 23/09/2025, 19:38:51
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
                <h4 class="text-center mb-4 text-white">Registro de Salon</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/ControllerSalon" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Salon 1..." required>
                    </div>

                    <div class="mb-3">
                        <label for="ubicacion" class="form-label">Ubicación</label>
                        <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Segundo piso, lado izquierdo...">
                    </div>

                    <div class="mb-3">
                        <label for="capacidad" class="form-label">Capacidad</label>
                        <input type="number" class="form-control" id="capacidad" name="capacidad" placeholder="35" required>
                    </div>

                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Registrarse</button>
                </form>
                <div class="text-center mt-3 small">
                    <a href="${pageContext.request.contextPath}/ControllerSalon" class="text-info text-decoration-none">Regresar</a>
                </div>
            </div>
        </div>
    </body>
</html>