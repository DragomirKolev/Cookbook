<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
  <h1>  </h1>
        <h2> </h2>
        </header>
        
        
        <nav id="cssmenu">
            <ul>

            
                <li><a href="/Instagrim">Home</a></li>
                
                
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    
               <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
               <li class="nav"><a href="/Instagrim/EvryImage">Gallery</a></li>
               <li class="nav"><a href="/Instagrim/TaggedImages.jsp">Search Gallery</a></li>
               <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
               <li class="nav"><a href="/Instagrim/searchUsers.jsp">Search for Chefs</a></li>
                              <li class="nav"><a href="/Instagrim/UserProfile.jsp">Edit Profile</a></li>
                              <li class="nav"><a href="/Instagrim/Recp">Search for Recipes</a></li>

               <%
               
               %>
                    <%}
                            }else{
                                %>
                <li><a href="register.jsp">Register</a></li>               
                <li><a href="login.jsp">Login</a></li>
                <%
                                        
                            
                    }%>
            </ul>
        </nav>
              <%
                        
                        
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                            <form action="${pageContext.request.contextPath}/logout" method="post">
             <input type="submit" value="Logout" />
                </form>
                    
                 
               <%
                                
                                
                                %>
                                
                                <%
                                
                            }else{
                                %>
                             <form action="${pageContext.request.contextPath}/logout" method="post">                  
             <input type="submit" value="Logout" disabled="disabled"/>
                </form>
                                <%
                                
                            }  }%>
                            
       
    </body>
</html>
