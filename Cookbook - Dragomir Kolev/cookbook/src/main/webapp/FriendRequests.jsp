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
          
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
         <h3><form action="${pageContext.request.contextPath}/logout" method="post">
             <input type="submit" value="Logout" />
                </form></h3>
    <nav id="cssmenu">
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/EvryImage">All Users Images</a></li>
                               <li class="nav"><a href="/Instagrim/UserProfile.jsp">Edit Profile</a></li>

                
                
            </ul>
        </nav>
       
          
           <form method="GET" enctype="multipart/form-data" action="FriendRequests">
             
              
              
              <input type="submit" value="Friend Requests"/>
            </form>
             
             
        <%
            java.util.LinkedList<ShowRequests> Recp = (java.util.LinkedList<ShowRequests>) request.getAttribute("Recp");
            System.out.println("fag1");
            if (Recp == null) {
                System.out.println("WHYYYYYYYYYYYYYYY");
        %>
       
        <%
        } else {
            Iterator<ShowRequests> iterator;
            iterator = Recp.iterator();
            System.out.println("faaaaaaaaaaaag");
            while (iterator.hasNext()) {
                ShowRequests p = (ShowRequests) iterator.next();

        %>
        <form method="post" action="Accept" >
            <li><input type=text  name="accept"  value= "<%=p.getUsername()%>" readonly ></li>
               
               
                 <input type="submit" value="Accept" >
        </form>
          <form method="post" action="Decline" >
               
               
               
                 <input type="submit" value="Decline" >
        </form>       
               
        <%
                

            }
            }
        %>
         <%
               
               %>
                    <%
                            }else{
                                %>
         <li class="nav"><a href="/Instagrim">Home</a></li>
        <%
                                        
                        }       
                    }%>
                    
        
           
        </article>
    </div>
    </body>
</html>

