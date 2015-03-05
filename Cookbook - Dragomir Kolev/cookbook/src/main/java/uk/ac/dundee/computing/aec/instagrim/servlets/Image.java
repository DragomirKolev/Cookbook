package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import uk.ac.dundee.computing.aec.instagrim.stores.SearchUsers;
import uk.ac.dundee.computing.aec.instagrim.stores.ShowRequests;
import uk.ac.dundee.computing.aec.instagrim.models.SearchU;
import uk.ac.dundee.computing.aec.instagrim.models.FriendRequests;

/**
 * Servlets implementation class Image
 */
@WebServlet(urlPatterns = {
    "/Image",
    "/Image/*",
    "/Thumb/*",
    "/Images",
    "/Images/*",
    "/EvryImage/*",
    "/SearchTag/*",
    "/SearchUser/*",
    "/FriendRequests/*"
    
})

@MultipartConfig

//Class definition
public class Image extends HttpServlet {
    //global variables
    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private final HashMap CommandsMap = new HashMap();
    private String snm;
    
    
    

    //Constructor and mapping of commands.
    public Image() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Image", 1);
        CommandsMap.put("Images", 2);
        CommandsMap.put("Thumb", 3);
        CommandsMap.put("EvryImage", 4);
        CommandsMap.put("SearchTag", 5);
        CommandsMap.put("SearchUser", 6);
        CommandsMap.put("FriendRequests", 7);

    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    //Method that uses switch cases to determend what method in the java file should be ran.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //splits the URL into arguments.
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
                DisplayImage(Convertors.DISPLAY_PROCESSED,args[2], response);
                break;
            case 2:
                DisplayImageList(args[2], request, response);
                break;
            case 3:
                DisplayImage(Convertors.DISPLAY_THUMB,args[2],  response);
                break;
            case 4:
                DisplayUsersListPic(request, response);
                break;
            case 5:
                DisplaySearchTags(args[1], request, response);
                break;
            case 6:
                DisplayAllrecpies(snm, request, response);
                break;
            case 7:
                ShowReq(snm, request, response);
                break;
            
            default:
                error("Bad Operator", response);
        }
    }
    
    
    //Method that displays a list of images for the user that is logged in.
    private void DisplayImageList(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //makes a instance of the picmodel class so it's methods can be accessed.
        PicModel tm = new PicModel();
        //setting up a cluster
        tm.setCluster(cluster);
        //makes a instance of a linked list and makes it equal the linked list from the method that is being executed in the picmodel class.
        java.util.LinkedList<Pic> lsPics = tm.getPicsForUser(User);
        // Returns a RequestDispatcher object that acts as a wrapper for the resource located at the given path.
        RequestDispatcher rd = request.getRequestDispatcher("/UsersPics.jsp");
        // Sets attribute for the request
        request.setAttribute("Pics", lsPics);
        // Forwards a request from a servlet to another resource (servlet, JSP file) on the server.
        rd.forward(request, response);

    }
    
    
    //Method that displays the gallery of all users 
    private void DisplayUsersListPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        java.util.LinkedList<Pic> lsPics = tm.getPicsForUsers();
        RequestDispatcher rd = request.getRequestDispatcher("/AllUsers.jsp");
        request.setAttribute("Pics", lsPics);
        rd.forward(request, response);
        
    }
    //Method that displays the pictures with tag being searched
    private void DisplaySearchTags(String Tag, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        Tag = request.getParameter("tagSearch");
        java.util.LinkedList<Pic> lsPics = tm.GetSearchTagPics(Tag);
        RequestDispatcher rd = request.getRequestDispatcher("/AllUsers.jsp");
        request.setAttribute("Pics", lsPics);
        rd.forward(request, response);
        
    }
    
     
    
      private void DisplayAllrecpies(String snm, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SearchU su = new SearchU();
        su.setCluster(cluster);
        snm = request.getParameter("SearchUser");
        java.util.LinkedList<SearchUsers> lsPics = su.FindUser(snm);
        RequestDispatcher rd = request.getRequestDispatcher("/searchUsers.jsp");
        request.setAttribute("Recp", lsPics);
        rd.forward(request, response);
        
    }
            private void ShowReq(String snm, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        FriendRequests su = new FriendRequests();
        su.setCluster(cluster);
        HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
      String me=lg.getUsername();
        snm = "test";
                //request.getParameter("SearchUser");
        java.util.LinkedList<ShowRequests> lsPics = su.ShowRequests(me);
        RequestDispatcher rd = request.getRequestDispatcher("/FriendRequests.jsp");
        request.setAttribute("Recp", lsPics);
        rd.forward(request, response);
        
    }
    //Method that displays a single image
    private void DisplayImage(int type,String Image, HttpServletResponse response) throws ServletException, IOException {
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
  
        
        Pic p = tm.getPic(type,java.util.UUID.fromString(Image));
        
        OutputStream out = response.getOutputStream();

        response.setContentType(p.getType());
        response.setContentLength(p.getLength());
        //out.write(Image);
        InputStream is = new ByteArrayInputStream(p.getBytes());
        BufferedInputStream input = new BufferedInputStream(is);
        byte[] buffer = new byte[8192];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            out.write(buffer, 0, length);
        }
        out.close();
    }
    // Post method that inserts a pic into the database. ( connects to picmodel to use insertPic() )
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Part part : request.getParts()) {
            System.out.println("Part Name " + part.getName());
            
     
            String type = part.getContentType();
            String filename = part.getSubmittedFileName();
            String tag = request.getParameter("tag");
           // String comment = request.getParameter("comment");
            
            String recp = request.getParameter("recp");
                       System.out.println(recp);

            
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            String username="raga";
            if (lg.getlogedin()){
                username=lg.getUsername();
            }
            if (i > 0) {
                byte[] b = new byte[i + 1];
                is.read(b);
                System.out.println("123Length : " + b.length);
                PicModel tm = new PicModel();
                tm.setCluster(cluster);
                tm.insertPic(b, type, filename, username, tag, recp);
           System.out.println(recp);

                is.close();
            }
            
           
                        

        
            //response.sendRedirect("/Instagrim");
        
            
            
            RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
             rd.forward(request, response);
        }

    }

    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have a na error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }
}
