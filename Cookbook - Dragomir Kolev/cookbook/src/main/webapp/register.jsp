<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
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
        <div class="input">
            <h5>
        <article>
            <h4>REGISTER AS USER</h4>
            <form method="POST"  action="Register">
                <ul>
                    <li> <input type="text" name="firstname" value ="First Name" onfocus="(this.value === 'First Name') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'First Name')" required></li>
                    <li> <input type="text" name="lastname" value ="Last Name" onfocus="(this.value === 'Last Name') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Last Name')" required></li>
                    <li> <input type="email" name="email" value ="e-Mail@something.something" onfocus="(this.value === 'e-Mail@something.something') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'e-Mail@something.something')" required></li>
                    <li> <input type="text" name="username" value ="Username" onfocus="(this.value === 'Username') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Username')" required></li>
                    <li> <input type="password" name="password" value ="Password" onfocus="(this.value === 'Password') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Password')" required></li>
                    
                    <li> <select name="gender" required>
                                <option value="male" >male</option>
                                <option value="female">female</option></select></li>
                    

                </ul>
                <br/>
               
                <input type="submit" value="Register"> 
                   </form>
                  </article>
         
            
            </h5>
            
            
</div>
                   
      
        
    </body>
</html>
