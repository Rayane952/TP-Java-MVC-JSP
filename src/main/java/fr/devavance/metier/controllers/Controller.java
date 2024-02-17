package fr.devavance.metier.controllers;

import fr.devavance.metier.Authentication;
import fr.devavance.metier.beans.Course;
import fr.devavance.metier.beans.Seance;
import fr.devavance.metier.TypeSeance;
import fr.devavance.metier.beans.User;

import fr.devavance.metier.views.Views;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Contrôleur gérant les séances
 *
 * @author B. LEMAIRE
 * @version 2023
 */
@WebServlet(name = "Controller", urlPatterns = {"/frontend"})

public class Controller extends HttpServlet implements IController {

    //private Business business = null;
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    private Authentication authentication;
    private Course course;
    private Views views;

    /**
     * Demarrage du contrôleur Procède aux initilialisations nécessaires aux
     * contrôleur
     *
     * @param c
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig c) throws ServletException {
        LOG.log(Level.INFO, "controller : init()");

        this.authentication = new Authentication();
        this.course = new Course();
        this.views = new Views();

        ArrayList<Seance> cours = new ArrayList<>();
        cours.add(new Seance(TypeSeance.COURS,
                "Cours : Les JSPs",
                "Les servlets sont des composants logiciels qui permettent de"
                + " développer des applications Web Java"));
        
          ArrayList<Seance> tds = new ArrayList<>();
        tds.add( new Seance(TypeSeance.TD,
                "Travail dirigé : les JSPs",
                "Ceci est le sujet de travail dirigé consacré aux JSPs"));
        
          ArrayList<Seance> tps = new ArrayList<>();
        tps.add(new Seance(TypeSeance.TP,
                "Travail pratique : les JSPs",
                "Ceci est le sujet de travail pratique consacré aux JSPs"));
        
        
        this.course.getSeances().put(TypeSeance.COURS, cours );

        this.course.getSeances().put(TypeSeance.TD, tds);

        this.course.getSeances().put(TypeSeance.TP, tps);
    }

    public String processAction(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        if (action.equals(LOGIN_ACTION)) {
            return doLogin(request);
        } else if (action.equals(LOGOUT_ACTION)) {
            return doLogout(request);
        } else if (action.equals(COURSE_ACTION)) {
            return doCourse(request);
        } else if (action.equals(HOME_ACTION)) {
            return doHome(request);
        }

        //autres actions
        throw new ServletException("action " + action + " inconnue.");
    }

    public void processView(String view, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(view).forward(request, response);

    }

    public String doLogin(HttpServletRequest request) {

        LOG.log(Level.INFO, "controller : action dologin");

        User user = getUser(request);

        // si l'utilisateur est connu
        if (user.getAuth()) {
            return views.home(request, user);
        }

        /**
         * On procède à l'authentification
         */
        // récupérer les données du formulaire
        user.setUserName(request.getParameter(KEY_USERNAME));
        user.setPassword(request.getParameter(KEY_PASSWORD));

        // si aucune données retour au formulaire
        if (user.getUserName() == null || user.getPassword() == null) {
            return views.loginForm(request, user, null);
        }

        // traiter les données
        if (user.getPassword().length() == 0) {
            return views.loginForm(request, user, EMPTY_PASSWORD);
        }

        authentication.authenticate(user);

        if (user.getAuth()) {
            return views.home(request, user);
        }

        // l'authentification a échoué
        return views.loginForm(request, user, INCORRECTS_IDS);
    }

    public String doLogout(HttpServletRequest request) {
        User user = getUser(request);
        user.setAuth(false);
        user.setUserName("");
        user.setPassword("");
        return views.message(request, DICONNECTED_MESSAGE);
    }

    public String doCourse(HttpServletRequest request) throws ServletException{

        String courseType = request.getParameter(KEY_COURSE_TYPE);
        User user = getUser(request);

        if (courseType.equals("COURS"))
             return views.course(
                     request, 
                     user, this.course.getCourseFromType(TypeSeance.COURS)
             );
        else  if (courseType.equals("TD"))
            return views.course(
                     request, 
                     user, this.course.getCourseFromType(TypeSeance.TD)
             );
        else if (courseType.equals("TP"))
             return views.course(
                     request, 
                     user, this.course.getCourseFromType(TypeSeance.TP)
             );
        
        throw new ServletException("Erreur cours");
    }

    public String doHome(HttpServletRequest request) {

        User user = getUser(request);

        return views.home(request, user);
    }

    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        synchronized (session) {
            Object o = session.getAttribute(KEY_USER);
            if (o instanceof User) {
                return (User) o;
            }

            User newUser = new User();
            session.setAttribute(KEY_USER, newUser);

            return (newUser);
        }
    }

    public String beforeAllProcessing(HttpServletRequest request) {

        LOG.log(Level.INFO, "controller : beforeAllProcessing");

        // vérifier l'authentification
        if (!request.getServletPath().equals(LOGIN_ACTION)) {
            User user = getUser(request);
            if (user.getAuth() == false) {
                String msg = "Vous devez vous authentifier";
                return views.loginForm(request, user, msg);
            }
        }
        return null;
    }

    // Interception d'une erreur
    public String error(HttpServletRequest request,
            HttpServletResponse response, Throwable e) {
        String msg = "erreur interne : " + e.getMessage();
        return views.message(request, msg);
    }

    /**
     * Traite l'URL
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("UTF-8");
            /**
             * Interception de la requête avant traitement par le contrôleur *
             * vérification de l'authentification retourne * null : si
             * authentification réussi * sino URL de la page de login
             */
            String view = beforeAllProcessing(request);

            // Exécution de l'action demandée
            if (view == null) {
                view = processAction(request, response);
            }

            // Activation de la vue
            if (view != null) {
                processView(view, request, response);
            }

        } catch (Throwable t) {
            String view = error(request, response, t);
            if (view != null)
            try {
                processView(view, request, response);
            } catch (Throwable e) {
                throw new ServletException(e);
            }
        }
    }

    /**
     * Arrêt du contrôleur Permet de libérer les ressources nécessaires au
     * contrôleur
     */
    @Override
    public void destroy() {
        //business.close();
    }
}
