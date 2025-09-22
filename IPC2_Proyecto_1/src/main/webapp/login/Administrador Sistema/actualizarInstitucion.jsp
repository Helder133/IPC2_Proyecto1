<%-- 
    Document   : actualizarInstitucion
    Created on : 21/09/2025, 18:48:37
    Author     : helder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Institucion</title>
        <jsp:include page="/includes/resources.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registro.css" />
    </head>
    <body class="bg-dark text-light">
        <div class="centered-form">
            <div class="register-card">
                <h4 class="text-center mb-4 text-white">Actualizacion de institución</h4>
                <form method="post" action="${pageContext.servletContext.contextPath}/ControllerInstitucionActualizarYEliminar" enctype="multipart/form-data">

                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="id" name="id" value="${institucion.id}">
                        <label for="nombre" class="form-label">Nombre de la Institución</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="CUNOC" value="${institucion.nombre}" required>
                        
                    </div>

                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="ej: zona 1-9 calle 9" value="${institucion.direccion}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <div class="phone-container">
                            <div>
                                <label for="codigo" class="form-label">Código País</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" placeholder="+502" value="${institucion.codigo}" required>
                            </div>
                            <div>
                                <label for="telefono" class="form-label">Número</label>
                                <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="55555555" value="${institucion.numero}" required>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Correo electrónico</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" value="${institucion.email}" required>
                    </div>

                    <!-- Botón -->
                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold mt-3">Actualizar Institucion</button>
                </form>
                <div class="text-center mt-3 small">
                    <a href="${pageContext.request.contextPath}/ControllerInstitucion" class="text-info text-decoration-none">Regresar</a>
                </div>
            </div>
        </div>
    </body>
</html>