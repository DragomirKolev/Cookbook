<%-- 
    Document   : SingleImage
    Created on : 23-Oct-2014, 12:16:13
    Author     : dragomirkolev
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
        
        
        <nav id="cssmenu">
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/EvryImage">All Users Images</a></li>
                <li class="nav"><a href="/Instagrim">Home</a></li>
                               <li class="nav"><a href="/Instagrim/UserProfile.jsp">Edit Profile</a></li>

                
            </ul>
        </nav>
        
        
          
        
       
           <%
          
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            
            if (lsPics == null) {
                System.out.println("MAIKA TI DA EBA");
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();
                System.out.println("DAFUQ BRO PEDAL");
                

        %>
        
        
          <div class='img'>  <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/></div>
          
      
          
          <%
            }
            %>
 
            <%
            }
        %>
          
       
        
          <%
              java.util.LinkedList<Recp> rcp = (java.util.LinkedList<Recp>) request.getAttribute("Recp");
              
            if (rcp == null) {
                System.out.println("DAFUQ BRO");
        %>
        <p>Nothing found</p>
        <%  
        } else {
            Iterator<Recp> iterator1;
            iterator1 = rcp.iterator();
            while (iterator1.hasNext()) { 
               Recp g = (Recp) iterator1.next();
              

        %>
                <textarea name="recp" rows="4" cols="50" readonly><%=g.getRecp()%> </textarea>
               
            <%

            }
            }
            
            
        %>
                
        
              <form method="POST" action="SingleImage">
                Comment<input type="text" name="comment">
                <input type="submit" value="Comment">
                
            </form>
            
    </body>
</html>
