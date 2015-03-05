/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.CommentModel;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.stores.CommentBean;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author dragomirkolev
 */
@WebServlet(name = "Comment", urlPatterns = {"/Comment"})
public class Comment extends HttpServlet {
    
        Cluster cluster=null;
        java.util.UUID harry=null;
    
  
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
   
    
        private void DisplayComments(java.util.UUID picid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CommentModel tm = new CommentModel();
        tm.setCluster(cluster);
        java.util.LinkedList<CommentBean> lsPics = tm.GetAllComments(picid);
        RequestDispatcher rd = request.getRequestDispatcher("/SingleImages.jsp");
        request.setAttribute("comment", lsPics);
        rd.forward(request, response);

    }
        
        
 
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        DisplayComments(harry, request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
            
            String comment = request.getParameter("comment");
            String recp = request.getParameter("recp");

            
            HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            
            
            String username="raga";
            if (lg.getlogedin()){
                username=lg.getUsername();
            }
      
                CommentModel tm = new CommentModel();
                tm.setCluster(cluster);
                
                //tm.InsertComment(picid, comment, recp, username);
           
    
              
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
