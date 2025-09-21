<%-- 
    Document   : nuevoUsuario
    Created on : 16/09/2025, 11:17:04
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
                <h4 class="text-center mb-4 text-white">Registro de Usuario</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/ControllerUsuario" enctype="multipart/form-data">

                    <!-- DPI / Pasaporte -->
                    <div class="mb-3">
                        <label for="dpi" class="form-label">DPI o Pasaporte</label>
                        <input type="text" class="form-control" id="dpi" name="dpi" placeholder="Ej: 1234567890101" required>
                    </div>

                    <!-- Foto -->
                    <div class="mb-3">
                        <label for="foto" class="form-label">Foto de perfil</label>
                        <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                    </div>

                    <!-- Nombre completo -->
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre completo</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Juan Pérez" required>
                    </div>

                    <!-- Teléfono con código de país -->
                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <div class="phone-container">
                            <div>
                                <label for="codigo" class="form-label">Código País</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" placeholder="+502" required>
                            </div>
                            <div>
                                <label for="telefono" class="form-label">Número</label>
                                <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="5555-5555" required>
                            </div>
                        </div>
                    </div>

                    <!-- Organización -->
                    <div class="mb-3">
                        <label for="organizacion" class="form-label">Organización</label>
                        <input type="text" class="form-control" id="organizacion" name="organizacion" placeholder="Empresa/Universidad" required>
                    </div>

                    <!-- Email -->
                    <div class="mb-3">
                        <label for="email" class="form-label">Correo electrónico</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" required>
                    </div>

                    <!-- Contraseña -->
                    <div class="mb-3">
                        <label for="contraseña" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Mínimo 8 caracteres" required>
                    </div>

                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Registrarse</button>
                </form>

                <div class="text-center mt-3 small text-light">
                    ¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/index.jsp" class="text-info text-decoration-none">Inicia sesión</a>
                </div>
            </div>
        </div>
    </body>
</html>