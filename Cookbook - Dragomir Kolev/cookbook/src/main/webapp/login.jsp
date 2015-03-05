<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        
        <header>
  <h1>  </h1>
        <h2> </h2>
        </header>
          
       <nav id="cssmenu">
            <ul>
               
                <li class="nav"><a href="/Instagrim">Home</a></li>
               
            </ul>
        </nav>
       
        <article>
            
            <form method="POST"  action="Login">
                <ul>
                    <li><input type="text" name="username" value="Username" onfocus="(this.value === 'Username') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Username')"></li>
                    <li><input type="password" name="password" value="Password" onfocus="(this.value === 'Password') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Password')"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>
          

        </article>
        <footer>
         
        </footer>
    </body>
</html>
