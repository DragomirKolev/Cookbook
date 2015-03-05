<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
        
        <h1> </h1>
        <h2></h2>
        
        </header>
        
         <h3><form action="${pageContext.request.contextPath}/logout" method="post">
             <input type="submit" value="Logout" />
                </form></h3>
             
    <nav id="cssmenu">
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/EvryImage">All Users Images</a></li>
                <li class="nav"><a href="/Instagrim">Home</a></li>
                
            </ul>
        </nav>
       
             
              <form method="GET" enctype="multipart/form-data" action="SearchedRecp">
              <h5><input type="text" name="SearchRecp" value="SearchRecp"
              onfocus="(this.value == 'Search Recp') && (this.value = '')"
                onblur="(this.value == '') && (this.value = 'Search Recp')" /></h5>
              
              <input type="submit" value="Search Recp"/>
              
            </form>
             
            
             <form method="GET" enctype="multipart/form-data" action="Recp">
                   <input type="submit" value="whatever"/>
                 
             </form>
              
   
        <%
            
            java.util.LinkedList<Recp> rcp = (java.util.LinkedList<Recp>) request.getAttribute("Recp");
            
            if (rcp == null) {
                System.out.println("WHYYYYYYYYYYYYYYY");
        %>
        <p>Nothing found</p>
        <%  
        } else {
            Iterator<Recp> iterator;
            iterator = rcp.iterator();
            while (iterator.hasNext()) { 
                System.out.println("FAG2");
               Recp p = (Recp) iterator.next();

        %>
                <textarea name="recp" rows="4" cols="50" readonly><%=p.getRecp()%> </textarea>
               
            <%

            }
            }
            
            
        %>
        
    
    </body>
</html>
