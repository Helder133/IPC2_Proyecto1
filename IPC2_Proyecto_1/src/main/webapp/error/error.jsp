<%-- 
    Document   : error
    Created on : 18/09/2025, 11:13:52
    Author     : helder
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="backend.modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <title>Error Login</title>
    </head>
    <body class="bg-dark text-light">
        <main class="d-flex align-items-center justify-content-center min-vh-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-lg-6">
                        <div class="error-container p-4">
                            <div class="d-flex align-items-center mb-3">
                                <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2 fs-1 text-danger"></i>
                                <div class="fw-bold fs-4">
                                    Error
                                </div>
                            </div>

                            <div class="mb-4">
                                <p class="mb-2 fs-5">${error}!!!</p>
                            </div>
                            <div class="text-center">
                                <a href="${pageContext.request.contextPath}/index.jsp"
                                   class="btn btn-warning w-100 py-2">
                                    <i class="bi bi-arrow-left-circle me-2"></i> Regresar
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>