

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;




//Name of class and setting global variables
public class User {
    Cluster cluster;
    //constructor
    public User(){
        
    }
    
    //Method that takes makes a connection to the database and inserts all the data inputed by the user in it.
    public boolean RegisterUser(String username, String Password, String first_name, String last_name, String gender, String email){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("insert into userprof (login, password, email , first_name, gender, last_name) Values(?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,email, first_name,gender,last_name));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
      public void sendFriendRequest(String login,String Me){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
         LoggedIn lg= new LoggedIn();
       String user=  "test";
        Session session = cluster.connect("cookbook");
        Convertors convertor = new Convertors();
        java.util.UUID id = convertor.getTimeUUID();
        PreparedStatement ps = session.prepare("insert into friend_requests (id,user, friend_name, friend_username , friend_request, accepted) Values(?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        id,login,Me, Me,1,0));
        //We are assuming this always works.  Also a transaction would be good here !
        
       // return true;
    }
            public void acceptFriend(String login,String Me){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
         LoggedIn lg= new LoggedIn();
       String user=  "test";
        Session session = cluster.connect("cookbook");
        Convertors convertor = new Convertors();
        java.util.UUID id = convertor.getTimeUUID();
        PreparedStatement ps = session.prepare("insert into my_friends (id,user, friend_name, friend_username ) Values(?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        id,login,Me, Me));
        //We accept friend request here and add it to our friends table;
          PreparedStatement ps2 = session.prepare("insert into my_friends (id,user, friend_name, friend_username ) Values(?,?,?,?)");
       
        BoundStatement boundStatement2 = new BoundStatement(ps2);
        session.execute( // this is where the query is executed
                boundStatement2.bind( // here you are binding the 'boundStatement'
                        id,Me,login, login));
        // We do the same for the user who has sent the request
        
        
        
        
       // return true;
    }
      public void addToFavourites(){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
         LoggedIn lg= new LoggedIn();
       String user=  "test";
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("insert into add_to_favourites (id,user, recipe_name, recipe_id ,to_favourites,  accepted) Values(?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        8,user,"asdasd", 4,1,0));
        //We are assuming this always works.  Also a transaction would be good here !
        
       // return true;
    }
      
    //Searches in the database if the username and password add up and if the user trying to log in is a user in the databse.
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("select password from userprof where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("wrong password or username");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
    
    
    public String getFirstname(String username)
    {
    	System.out.println("getting first name");
    	String fname = null;
    	Session session = cluster.connect("instagrim");
    	PreparedStatement ps = session.prepare("select first_name from userprofiles where login =?");
    	ResultSet rs = null;
    	BoundStatement boundStatement = new BoundStatement(ps);
    	rs = session.execute(boundStatement.bind(username));
    	if(rs.isExhausted())
    	{
    	
    	}
    		else{
        		for (Row row : rs)
        		{
        			fname = row.getString("first_name");
        		}
        	}
    	
    	System.out.println("fname");
    	
    	return fname;
    }
    
    public String getLastname(String username)
    {
    	System.out.println("getting last name");
    	String lname = null;
    	Session session = cluster.connect("instagrim");
    	PreparedStatement ps = session.prepare("select last_name from userprofiles where login =?");
    	ResultSet rs = null;
    	BoundStatement boundStatement = new BoundStatement(ps);
    	rs = session.execute(boundStatement.bind(username));
    	
    	
    	if(rs.isExhausted())
    	{
    		
    	}
    	else{
    		for (Row row : rs)
    		{
    			lname = row.getString("last_name");
    		}
    	}
    	System.out.println(lname);
    	return lname;
    }
    
    public String getEmail(String username)
    {
    	System.out.println("getting email");
    	String email = null;
    	Session session = cluster.connect("instagrim");
    	PreparedStatement ps = session.prepare("SELECT email from userprofiles where login =?");
    	ResultSet rs = null;
    	BoundStatement boundStatement = new BoundStatement(ps);
    	rs = session.execute(boundStatement.bind(username));
    	
    	
    	
    	if(rs.isExhausted())
    	{
    		
    	}
    	else{
    		for (Row row : rs)
    		{
    			email = row.getString("email");
    		}
    	}
    	System.out.println(email);
    	return email;
    }
    
    public String getGender(String username)
    {
    	System.out.println("getting first name");
    	String gender = null;
    	Session session = cluster.connect("instagrim");
    	PreparedStatement ps = session.prepare("select gender from userprofiles where login =?");
    	ResultSet rs = null;
    	BoundStatement boundStatement = new BoundStatement(ps);
    	rs = session.execute(boundStatement.bind(username));
    	
    	
    	if(rs.isExhausted())
    	{
    	
    	}
    	else{
    		for (Row row : rs)
    		{
    			gender = row.getString("gender");
    		}
    	}
    	System.out.println(gender);
    	return gender;
    }
    
    
    public boolean EditUser(String username, String currentPass, String newPass, String firstName, String lastName, String email, String sex)
    {
    	Session session = cluster.connect("instagrim");
    	boolean test = IsValidUser(username, currentPass);
    	if(test == true)
    	{
    		if(currentPass != null)
    		{
    			
    		  AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
    	        String EncodedPassword=null;
    	        try {
    	            EncodedPassword= sha1handler.SHA1(newPass);
    	        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
    	            System.out.println("Can't check your password");
    	            return false;
    	        }
    	        PreparedStatement psPass = session.prepare("UPDATE userprofiles set password =? where login =?");
    	        BoundStatement bsPass = new BoundStatement(psPass);
    	        session.execute(bsPass.bind(newPass, username));
    	        
    	        
    		}
    	
    	       
    	        
    	     
    	        PreparedStatement psFirst = session.prepare("UPDATE userprofiles set first_name =? where login =?");
    	        PreparedStatement psLast = session.prepare("UPDATE userprofiles set last_name =? where login =?");
    	        PreparedStatement psEmail = session.prepare("UPDATE userprofiles set email =? where login =?");
    	        PreparedStatement psSex = session.prepare("UPDATE userprofiles set sex =? where login =?");
    	        
    	        
    	        
    	       
    	        
    	        BoundStatement bsFirst = new BoundStatement(psFirst);
    	        BoundStatement bsLast = new BoundStatement(psLast);
    	        BoundStatement bsEmail = new BoundStatement(psEmail);
    	        BoundStatement bsSex = new BoundStatement(psSex);
    	        
    	       
    	        session.execute(bsFirst.bind(firstName, username));
    	        session.execute(bsLast.bind(lastName, username));
    	        session.execute(bsEmail.bind(email, username));
    	        session.execute(bsSex.bind(sex,username));
       
    		
    	}
    	
    	
    	
    	
    	return true;
    	
    }
    
       //setting up a cluster
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
