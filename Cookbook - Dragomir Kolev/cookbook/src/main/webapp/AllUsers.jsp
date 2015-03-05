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
        
        <h1>  </h1>
        <h2> </h2>
        
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
       
            
              
            <form method="GET" enctype="multipart/form-data" action="SearchTag">
            <h5><input type="text" name="tagSearch" value="Search Tag"
              onfocus="(this.value === 'Search Tag') && (this.value = '')"
                onblur="(this.value === '') && (this.value = 'Search Tag')" /></h5>
              <input type="submit" value="tag Search"/>
            </form>
             
              
   
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) { 
               
               Pic p = (Pic) iterator.next();
               System.out.println("faggot " + p.getSUUID());

        %>
        <div class='img'>   <a href="/Instagrim/SingleImage/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/></div><%

            }
            }
        %>
        
        
        
    
    </body>
</html>
