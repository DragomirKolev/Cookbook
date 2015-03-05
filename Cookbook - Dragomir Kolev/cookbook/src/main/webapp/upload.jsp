<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ALmost-Insta-Gram</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
  <h1>  </h1>
        <h2> </h2>
           <h3><form action="${pageContext.request.contextPath}/logout" method="post">
             <input type="submit" value="Logout" />
                </form></h3>
             
       <nav id="cssmenu">
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/EvryImage">All Users Images</a></li>
                <li class="nav"><a href="/Instagrim">Home</a></li>
                <li class="nav"><a href="/Instagrim/Recp">Search for Recipes</a></li>
                
            </ul>
        </nav>
        <article>
            <h4>
            <form method="POST" enctype="multipart/form-data" action="Image">
               
                <input type="file" name="upfile" value="Click to Upload"><br/>
                <br>
                 Tag: <input type="text" name="tag">
                 </br>
                 <br>
                 <textarea name="recp" rows="4" cols="50">Recp</textarea>
                </br>
                <input type="submit" value="Upload">
            </form>
            </h4>

           
            
        </article>
     
    </body>
</html>
