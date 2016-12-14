<%-- 
    Document   : index
    Created on : Dec 14, 2016, 6:04:36 PM
    Author     : anuz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">


            <button onclick="clickMe()" class="btn btn-default">Click Me</button>
            <div id="display"></div>
        </div>



        <script>

            function clickMe() {
                $.get("http://localhost:8080/DummyAPI/click_me", function (data, status) {
                    $("#display").append("<br>"+ data.clickMe);
                });

            }
        </script>
    </body>

</html>
