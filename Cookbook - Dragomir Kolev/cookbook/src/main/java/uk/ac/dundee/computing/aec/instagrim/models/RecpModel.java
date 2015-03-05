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
import uk.ac.dundee.computing.aec.instagrim.stores.Recp;
import uk.ac.dundee.computing.aec.instagrim.stores.SearchUsers;

//class
public class RecpModel {

    Cluster cluster;
    //constructor
    public void RecpModel() {

    }
    //setting up the cluster
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    //Method that inserts picture into database
   
      public java.util.LinkedList<Recp> getAllRecpies() {
         
        java.util.LinkedList<Recp> Recp = new java.util.LinkedList<>();
       
        Session session = cluster.connect("cookbook");
        
        PreparedStatement ps = session.prepare("select recp from recp");
      
        
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        if (rs.isExhausted()) {
            System.out.println("No users found");
            return null;
        } else {
            for (Row row : rs) {
                
                Recp rp = new Recp();
                String recp = row.getString("recp");
                System.out.println(recp);
                rp.setRecp(recp);
                Recp.add(rp);
                
            }
        }
        return Recp;
    }
      
      public java.util.LinkedList<Recp> getSearchedRecp(String tag) {
         
        java.util.LinkedList<Recp> Recp = new java.util.LinkedList<>();
        System.out.println("FAG1");
        Session session = cluster.connect("cookbook");
        System.out.println("FAG2");
        PreparedStatement ps = session.prepare("select recp from tag where tag =?");
        System.out.println("FAG3");
        
        ResultSet rs = null;
        System.out.println("FAG4");
        BoundStatement boundStatement = new BoundStatement(ps);
        System.out.println("FAG5");
        rs = session.execute( // this is where the query is executed
                
                boundStatement.bind( // here you are binding the 'boundStatement'
                        tag));
        System.out.println("FAG6");
        
        if (rs.isExhausted()) {
            System.out.println("No shema found");
            return null;
        } else {
            for (Row row : rs) {
               
                Recp rp = new Recp();
                String recp = row.getString("recp");
                System.out.println(recp);
                rp.setRecp(recp);
                Recp.add(rp);
                
            }
        }
        return Recp;
    }
      
       public java.util.LinkedList<Pic> GetClickedPickture(java.util.UUID picid) {
           
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("select picid from recp where picid =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
         
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picid));
         
        if (rs.isExhausted()) {
           
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                System.out.println("UUID is" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
        }
       
        return Pics;
    }
       
       
      public java.util.LinkedList<Recp> GetClickPicRecp(java.util.UUID picid) {
           
        java.util.LinkedList<Recp> Recp = new java.util.LinkedList<>();
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("select recp from recp where picid =?");
        ResultSet rs = null;
         
        BoundStatement boundStatement = new BoundStatement(ps);
         
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picid));
         
        if (rs.isExhausted()) {
      
            return null;
        } else {
            for (Row row : rs) {
                
                
                Recp rp = new Recp();
                String recp = row.getString("recp");
                System.out.println(recp);
                System.out.println("NESHTASNIK");
                rp.setRecp(recp);
                System.out.println("NESHTASNIK1");
                Recp.add(rp);
                System.out.println("NESHTASNIK2");

            }
        }
       System.out.println("NESHTASNIK3");
        return Recp;
    }

}
