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
        <!-- Bootstrap -->
        <jsp:include page="/includes/resources.jsp"/>
        <!-- Estilos personalizados -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" />
    </head>
    <body>
        <main>
            <div class="login-card">
                <h4 class="text-center mb-4">Inicio de Sesión</h4>
                <form method="post" action="LoginServlet">
                    <div class="mb-3">
                        <label for="User" class="form-label">Documento de Identificación</label>
                        <input type="text" class="form-control" id="User" name="user" placeholder="DPI o Pasaporte" required>
                    </div>
                    <div class="mb-3">
                        <label for="Password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="Password" name="password" placeholder="Ingrese su contraseña" required>
                    </div>
                    <button type="submit" class="btn btn-login w-100">Ingresar</button>
                </form>
            </div>
        </main>
    </body>
</html>

