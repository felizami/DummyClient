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
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jszip.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jszip-utils.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">

            <button onclick="syncContents(4)">User4 Sync data</button>
            <button onclick="clickMe()" class="btn btn-default">Click Me</button>


            <button onclick="checkUpdateStatus(1)" class="btn btn-primary">User 1</button>
            <button onclick="checkUpdateStatus(4)" class="btn btn-primary">User 4</button>
            <button onclick="checkUpdateStatus(5)" class="btn btn-primary">User 5</button>


            <div id="display"></div>


        </div>



        <script>

            function checkUpdateStatus(userId) {
                $.get("http://localhost:8080/DummyAPI/api/v1/users/content_status/" + userId, function (data, status) {
                    if (data.status) {
                        $("#display").append("<br>" + "User" + userId + " is up to date");
                    } else {
                        $("#display").append("<br>" + "User" + userId + " has new updates");
                    }
                });
            }

            function clickMe() {

                $.get("http://localhost:8080/DummyAPI/click_me", function (data, status) {
                    $("#display").append("<br>" + data.clickMe);
                });
            }

            function syncContents(id) {
                $.get("http://localhost:8080/DummyAPI/api/v1/users/contents_file/"+id,function(data){
                    
                    $.ajax({
                            url: 'http://localhost:8080/DummyClient/contents/unzip',
                            type: 'post',
                            contentType: 'application/json',
                            data: JSON.stringify({"files":data})
                          
                        });
//                   console.log(data); 
                });
            }

//                JSZipUtils.getBinaryContent("http://localhost:8080/DummyAPI/api/v1/users/contents_file/" + 4, function (err, data) {
//                    if (err) {
//                        throw err; // or handle err
//                    }
//                    if (data.byteLength === 0) {
//                        alert("No Updates");
//                        return;
//                    }
//                    
//                    console.log(data);
//
//                    JSZip.loadAsync(data).then(function (contents) {
//                         
//                        $.ajax({
//                            url: 'http://localhost:8080/DummyClient/contents/unzip',
//                            type: 'post',
//                            contentType: 'application/json',
//                            data: JSON.stringify(contents)
//                          
//                        });
//
//
//                        //                        
////                        $.ajax({
////                            type: "POST",
////                            url: "http://localhost:8080/DummyClient/contents/unzip",
////                            data: dataset,
////                            dataType: "application/json"
////                        });
////                        
//
////                        
//
////                        console.log(contents);
////                        console.log(contents.files);
////                        Object.keys(contents.files).forEach(function (filename) {
////                            contents.files[filename].async('string').then(function (fileData) {
////                                
////                                
////                                console.log(fileData); // These are your file contents      
////                            });
////                        });
//
//
//                    });
//                });
//            }

        </script>
    </body>

</html>
