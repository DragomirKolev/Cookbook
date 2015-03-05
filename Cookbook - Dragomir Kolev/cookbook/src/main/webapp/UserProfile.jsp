<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.models.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />

<title>Insert title here</title>
</head>
<body>
 <article>
 <% 
 	
 	
    
                        
                       
                            	%>
            
                    
      
            
            
         
              
                <%
               
                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    
                    
                    String UserName = lg.getUsername();
                    String firstName = lg.getFirstname();
                    String lastName = lg.getLastname();
                    String Email = lg.getEmail();
                    String gender = lg.getGender();
                  	
              
             	}%>
             	<form method="POST"  action="EditProfile">
             	
             		
                <li>User name: <input type="text" name="username" value=<%=lg.getUsername()%>></li>
                <li>First Name: <input type="text" name ="firstName" value =<%=lg.getFirstname()%>></li>
                <li>Last Name: <input type = "text" name = "lastName" value=<%=lg.getLastname()%>></li>
                <li>Email Address: <input type="email" ><%=lg.getEmail()%></li>
                <li>Gender: <input type = "text" name = "email" value=<%=lg.getGender()%>></li>
                    
                </form>
                 
                 
               
                
                 
                <input type="submit" value="Submit Changes"> 
                
                <nav id="cssmenu">
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/EvryImage">All Users Images</a></li>
                <li class="nav"><a href="/Instagrim">Home</a></li>
                <li class="nav"><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
               <li class="nav"><a href="/Instagrim/EvryImage">Gallery</a></li>
               <li class="nav"><a href="/Instagrim/TaggedImages.jsp">Search Gallery</a></li>
               <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
               <li class="nav"><a href="/Instagrim/UserProfile.jsp">Edit Profile</a></li>
               
            </ul>
        </nav>
           
            </article>
</body>
</html>

