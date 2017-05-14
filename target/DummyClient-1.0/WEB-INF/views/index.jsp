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

            <!--<button onclick="syncContents(4)">User4 Sync data</button>-->
            <button onclick="clickMe()" class="btn btn-default">Click Me</button>


            <button onclick="checkUpdateStatus(1)" class="btn btn-primary">User 1</button>
            <button onclick="checkUpdateStatus(4)" class="btn btn-primary">User 4</button>
            <button onclick="checkUpdateStatus(5)" class="btn btn-primary">User 5</button>


            
            <img src="${pageContext.request.contextPath}/assets/img/hachiko1.jpg" alt=""/>
            
            <div id="htmlcontent">
                
            </div>
               
            
            <div id="display"></div>
            
            
            <img src="">

        </div>



        <script>
            htmlContent(4);
            
            function htmlContent(clientId){
                $.get("http://localhost:8080/DummyAPI/api/v1/users/" + clientId+"/latest", function (data, status) {
                    console.log(data);
                    console.log(status);
                    if (data.status) {
                        $("#htmlcontent").append(data);
                    } 
                });
            }

            function checkUpdateStatus(clientId) {
                $.get("http://localhost:8080/DummyAPI/api/v1/users/content_status/" + clientId, function (data, status) {
                    console.log(data);
                    console.log(status);
                    if (data.status) {
                        $("#display").append("<br>" + "User" + clientId + " has new updates");
                    } else {

                        $("#display").append("<br>" + "User" + clientId + " is up to date");
                    }
                });
            }

            function clickMe() {

                $.get("http://localhost:8080/DummyAPI/click_me", function (data, status) {
                    $("#display").append("<br>" + data.clickMe);
                });
            }

//            function syncContents(id) {
//                JSZipUtils.getBinaryContent("http://localhost:8080/DummyAPI/api/v1/users/contents_file/" + id, function (err, data) {
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
//                    
//                    JSZip.loadAsync(data).then(function (zipFile) {
//                        var re = /(.jpg|.png|.gif|.ps|.jpeg)$/;
//                        var promises = Object.keys(zipFile.files).filter(function (fileName) {
//                            // don't consider non image files
//                            console.log(fileName);
////                            console.log(re.test(fileName.toLowerCase()));
//                            return re.test(fileName.toLowerCase());
//                        }).map(function (fileName) {
//                            
//                            var file = zipFile.files[fileName];
//                            return file.async("string").then(function (blob) {
//                                return [
//                                    fileName, // keep the link between the file name and the content
//                                    blob // create an url. img.src = URL.createObjectURL(...) will work
//                                ];
//                            });
//                        });
//                        
//                        // `promises` is an array of promises, `Promise.all` transforms it
//                        // into a promise of arrays
//                        console.log("promises");
//                        console.log(promises);
//                        
//                        return Promise.all(promises);
//                    }).then(function (result) {
//                        console.log("Check");
//                            
////                        $.post("http://localhost:8080/DummyClient/contents/unzip",{"files":result},function(){
////                            
////                        });
//                        // we have here an array of [fileName, url]
//                        // if you want the same result as imageSrc:
////                        return result.reduce(function (acc, val) {
////                            acc[val[0]] = val[1];
////                            return acc;
////                        }, {});
//                    }).catch(function (e) {
//                        console.error(e);
//                    });
//                    
//
//
//
//
//                });
//            }

        </script>
    </body>

</html>
