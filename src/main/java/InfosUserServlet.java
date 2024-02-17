
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author bouchaib.lemaire
 */
@WebServlet(urlPatterns = {"/infos"})
public class InfosUserServlet extends HttpServlet {

     
    public final static String KEY_USER = "user";
    public final static String KEY_USERS = "users";
        public final static String KEY_USER_NAME = "user_name";
    public final static String KEY_PROFIL = "profil";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_AUTH = "auth";
    

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
      
    
    }

  


}
