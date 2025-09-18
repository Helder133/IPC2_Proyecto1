<%-- 
    Document   : index
    Created on : 15/09/2025, 23:19:01
    Author     : helder
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta charset="UTF-8">
        <title>Inicio de Sesión</title>
        <jsp:include page="/includes/resources.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" />
    </head>
    <body>
        <main>
            <div class="login-card">
                <h4 class="text-center mb-4">Inicio de Sesión</h4>
                <form method="POST" action="${pageContext.servletContext.contextPath}/ControllerLogin" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="User" class="form-label">Documento de Identificación</label>
                        <input type="text" class="form-control" id="Usuario" name="Usuario" placeholder="DPI o Pasaporte" required>
                    </div>
                    <div class="mb-3">
                        <label for="Password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="Contraseña" name="Contraseña" placeholder="Ingrese su contraseña" required>
                    </div>
                    <button type="submit" class="btn btn-login w-100">Ingresar</button>

                    <div class="text-center mt-3 small text-muted">
                        ¿No estas registrado? <a href="${pageContext.request.contextPath}/nuevoUsuario/nuevoUsuario.jsp" class="link-light">Registrate</a>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>

