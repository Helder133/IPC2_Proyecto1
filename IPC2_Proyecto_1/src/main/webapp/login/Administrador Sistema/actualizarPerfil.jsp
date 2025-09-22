<%-- 
    Document   : actualizarPerfil
    Created on : 22/09/2025, 12:29:26
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Perfil</title>
        <jsp:include page="/includes/resources.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registro.css" />
    </head>
    <body class="bg-dark text-light">
        <jsp:include page="/includes/headerAdminSistema.jsp"/>
        <div class="centered-form">
            <div class="register-card">
                <h4 class="text-center mb-4 text-white">Editar Usuario</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/controllerPerfil" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="dpi" class="form-label">DPI o Pasaporte</label>
                        <label class="form-label">${usuario.DPI_o_Pasaporte}</label>
                        <input type="hidden" id="dpi" name="dpi" value="${usuario.DPI_o_Pasaporte}">
                    </div>

                    <div class="mb-3">
                        <label for="foto" class="form-label">Foto de perfil</label>
                        <c:if test="${not empty usuario.foto}">
                            <div class="mb-2 text-center">
                                <img src="${pageContext.request.contextPath}/${usuario.foto}" 
                                     alt="Foto actual" 
                                     class="rounded-circle mb-2" 
                                     style="width: 80px; height: 80px; object-fit: cover;">
                                <p class="small text-muted">Foto actual</p>
                            </div>
                        </c:if>
                        <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                        <div class="form-text">Seleccione una nueva imagen solo si desea cambiarla</div>
                    </div>

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre completo</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" placeholder="Juan Pérez" required>
                    </div>

                    <!-- Teléfono con código de país -->
                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <div class="phone-container">
                            <div>
                                <label for="codigo" class="form-label">Código País</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" placeholder="+502" value="${usuario.codigo}" required>
                            </div>
                            <div>
                                <label for="telefono" class="form-label">Número</label>
                                <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="5555-5555" value="${usuario.numero}" required>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="organizacion" class="form-label">Organización</label>
                        <input type="text" class="form-control" id="organizacion" name="organizacion" value="${usuario.organizacion}" placeholder="Empresa/Universidad">
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Correo electrónico</label>
                        <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" placeholder="example@gmail.com" required>
                    </div>

                    <div class="mb-3">
                        <label for="contraseña" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Dejar en blanco para mantener la actual">
                        <div class="form-text text-light">Solo complete si desea cambiar la contraseña</div>
                    </div>

                    <div class="select-row mb-3">
                        <div>
                            <label for="estado" class="form-label">Estado</label>
                            <label class="form-control">${usuario.estado}</label>
                            <input type="hidden" id="estado" name="estado" value="${usuario.estado == true ? "Habilitado" : "Deshabilitado"}">
                        </div>
                        <div>
                            <label for="rol" class="form-label">Rol</label>
                            <label class="form-control">${usuario.rol}</label>
                            <input type="hidden" id="rol" name="rol" value="${usuario.rol}">
                        </div>
                    </div>

                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Actualizar Usuario</button>
                </form>

                <div class="text-center mt-3 small">
                    <a href="${pageContext.request.contextPath}/controllerPerfil" class="text-info text-decoration-none">
                        <i class="bi bi-arrow-left me-1"></i> Volver al listado de usuarios
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>