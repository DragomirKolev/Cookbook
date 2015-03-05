/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.CommentBean;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author dragomirkolev
 */
public class CommentModel {
    Cluster cluster;
    
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
     public java.util.LinkedList<CommentBean> GetAllComments(java.util.UUID picid) {
           
        java.util.LinkedList<CommentBean> comment = new java.util.LinkedList<>();
        Session session = cluster.connect("cookbook");
        PreparedStatement ps = session.prepare("select * from comment where picid =?");
        ResultSet rs = null;
        
        BoundStatement boundStatement = new BoundStatement(ps);
         
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picid));
         
        if (rs.isExhausted()) {
           
            return null;
        } else {
            for (Row row : rs) {
                
                CommentBean comment2 = new CommentBean();
                String comment1 = row.getString("comment");            
                System.out.println(comment1);
                comment2.setComment(comment1);
                comment.add(comment2);
   

            }
        }
       
        return comment;
    }
     
     public void InsertComment(String comment, String user, java.util.UUID picid) {
        
            

            //connection to the database
            Session session = cluster.connect("cookbook");
            
            //preparing statement to execute
            PreparedStatement psInsertComments = session.prepare("insert into comments (picid, comment, user) values(?,?,?)");
          
            //binding statement so that it can be executed
            BoundStatement bsInsertComments = new BoundStatement(psInsertComments);

            //executing statement
            session.execute(bsInsertComments.bind(picid, comment, user));
            //closing session
            session.close();

     
     
     
     }

    public void InsertComment(UUID savedImage, String comment, String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
