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
       
          
           <form method="GET" enctype="multipart/form-data" action="SearchUser">
              <h5><input type="text" name="SearchUser" value="SearchUser"
              onfocus="(this.value == 'Search Tag') && (this.value = '')"
                onblur="(this.value == '') && (this.value = 'Search Tag')" /></h5>
              
              <input type="submit" value="searchUser"/>
            </form>
             
             
        <%
            java.util.LinkedList<SearchUsers> Recp = (java.util.LinkedList<SearchUsers>) request.getAttribute("Recp");
            System.out.println("fag1");
            if (Recp == null) {
                System.out.println("WHYYYYYYYYYYYYYYY");
        %>
       
        <%
        } else {
            Iterator<SearchUsers> iterator;
            iterator = Recp.iterator();
            System.out.println("faaaaaaaaaaaag");
            while (iterator.hasNext()) {
                SearchUsers p = (SearchUsers) iterator.next();

        %>
        <form method="post" action="addChef" >
             <li>  <input type=text  name="addChef"  value= "<%=p.getName()%>" readonly >  </li>
                 <input type="submit" value="Add Friend" >
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
