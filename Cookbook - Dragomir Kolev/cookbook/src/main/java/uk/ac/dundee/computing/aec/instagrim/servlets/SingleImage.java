package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.RecpModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author dragomirkolev
 */
//@WebServlet(name = "Login", urlPatterns = {"/Login"})
@WebServlet(name = "SingleImage", urlPatterns = ("/SingleImage/*"))
  
    //"/SingleImage/*"
   // "/SingleImageComments"
    //
//})
@MultipartConfig

public class SingleImage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();
    java.util.UUID savedImage;
    String user;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleImage() {
         
        // TODO Auto-generated constructor stub
        CommandsMap.put("SingleImage", 1);
        
      
        
       

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
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
            return;
        }
        switch (command) {
            case 1:
                
                DisplaySingleImage(java.util.UUID.fromString(args[2]),request,response);
                DisplayRecp(java.util.UUID.fromString(args[2]),request,response);
  
                break;
         
            default:
            error("Bad Operator", response);
        
                
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String comment=request.getParameter("comment");
            System.out.println(comment);
          //  user.getUser
            PicModel pic = new PicModel();
            pic.setCluster(cluster);
            System.out.println(savedImage);
            response.sendRedirect("/Instagrim/SingleImage/"+savedImage);
 
    }
    
    private void DisplayRecp(java.util.UUID picid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        RecpModel tm = new RecpModel();
        System.out.println("NESHTASNIK6");
        tm.setCluster(cluster); 
        System.out.println("NESHTASNIK7");
        LinkedList<uk.ac.dundee.computing.aec.instagrim.stores.Recp> Recp = tm.GetClickPicRecp(picid);
        System.out.println("NESHTASNIK8");
        RequestDispatcher rd = request.getRequestDispatcher("/SingleImage.jsp");
        System.out.println("NESHTASNIK9");
        request.setAttribute("Recp", Recp);
        System.out.println("NESHTASNIK0");
        rd.forward(request, response);
        System.out.println("NESHTASNIK11");
        
    }
     private void DisplaySingleImage(java.util.UUID picid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        savedImage = picid;
        savedImage.toString();
        java.util.LinkedList<Pic> lsPics = tm.GetClickedPickture(picid);
        RequestDispatcher rd = request.getRequestDispatcher("/SingleImage.jsp");
        request.setAttribute("Pics", lsPics);
        rd.forward(request, response);
        
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
        private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have a na error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }
        
       
}


