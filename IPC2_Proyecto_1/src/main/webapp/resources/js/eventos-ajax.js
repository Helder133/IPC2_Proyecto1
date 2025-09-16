/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function cargarAjaxData(context) {

    var requestAjax = new XMLHttpRequest();

    requestAjax.onreadystatechange = function () {
        if (requestAjax.readyState === XMLHttpRequest.DONE) {
            if (requestAjax.status === 200) {
                document.getElementById('listado').innerHTML = requestAjax.responseText;
            } else if (requestAjax.status === 400) {
                alert('error 400');
            } else {
                alert('Error desde el servidor');
            }
        }
    }

    requestAjax.open('GET', context + '/mvc/eventos/ajax/eventos-servlet', true);
    requestAjax.send();
}