
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.RecpModel;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author dragomirkolev
 */


@WebServlet(urlPatterns = {
    "*/Recp",
    "/Recp/*",
    "/SearchedRecp"
   
    
})

@MultipartConfig
public class Recp extends HttpServlet {
         Cluster cluster=null;
         String tag;
         private final HashMap CommandsMap = new HashMap();
          java.util.UUID savedImage;
          
         public Recp(){
             
         super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Recp", 1);
        CommandsMap.put("SearchedRecp", 2);
      
      
        }
         public void init(ServletConfig config) throws ServletException {
        
        cluster = CassandraHosts.getCluster();
    }
   
    
    private void DisplayAllRecp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RecpModel tm = new RecpModel();
        tm.setCluster(cluster);
        java.util.LinkedList<uk.ac.dundee.computing.aec.instagrim.stores.Recp> Recp = tm.getAllRecpies();
        RequestDispatcher rd = request.getRequestDispatcher("/ALLRECP.jsp");
        request.setAttribute("Recp", Recp);
        rd.forward(request, response);
        
    }
    
    private void DisplayTagRecp(String tag, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RecpModel tm = new RecpModel();
        tm.setCluster(cluster);
        tag = request.getParameter("SearchRecp");
        java.util.LinkedList<uk.ac.dundee.computing.aec.instagrim.stores.Recp> Recp = tm.getSearchedRecp(tag);
        RequestDispatcher rd = request.getRequestDispatcher("/ALLRECP.jsp");
        request.setAttribute("Recp", Recp);
        rd.forward(request, response);
        
    }
    
  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String args[] = Convertors.SplitRequestPath(request);
        int command;
        try {
           
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator", response);
            return;}
        
           
        switch (command) {
            case 1:
                DisplayAllRecp(request, response);
                break;
            case 2:
                DisplayTagRecp(tag, request, response);
                break;

            default:
                error("Bad Operator", response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void error(String bad_Operator, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
