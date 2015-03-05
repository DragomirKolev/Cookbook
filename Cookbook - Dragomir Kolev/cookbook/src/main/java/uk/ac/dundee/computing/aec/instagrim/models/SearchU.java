package uk.ac.dundee.computing.aec.instagrim.models;

/*
Importing all the libraries and files that are used in this file.
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.SearchUsers;

//class
public class SearchU {

    Cluster cluster;
    //constructor
    public void SearchU() {

    }
    //setting up the cluster
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    //Method that inserts picture into database
   
      public java.util.LinkedList<SearchUsers> FindUser(String snm) {
         
        java.util.LinkedList<SearchUsers> Recp = new java.util.LinkedList<>();
        
        Session session = cluster.connect("cookbook");
        
        PreparedStatement ps = session.prepare("select login from userprof where login =?");
        
        
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        snm));
        
        if (rs.isExhausted()) {
            System.out.println("No users found");
            return null;
        } else {
            for (Row row : rs) {
                
                SearchUsers su = new SearchUsers();
                String searchU = row.getString("login");
                System.out.println(searchU);
                su.setName(searchU);
                Recp.add(su);

            }
        }
        return Recp;
    }

}
