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
    <main class="d-flex align-items-center justify-content-center min-vh-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="alert alert-danger shadow-lg p-4" role="alert">
                        <div class="d-flex align-items-center mb-3">
                            <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2 fs-4"></i>
                            <div class="fw-bold">
                                ${error} !!!
                            </div>
                        </div>
                        <div class="text-center">
                            <a href="${pageContext.request.contextPath}/index.jsp"
                               class="btn btn-warning w-100">
                                <i class="bi bi-arrow-left-circle"></i> Regresar
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</html>
