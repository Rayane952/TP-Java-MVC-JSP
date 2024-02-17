package fr.devavance.metier.controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contrôleur gérant les séances
 *
 * @author B. LEMAIRE
 * @version 2023
 */
public interface IController {

    // Actions gérées par le contrôleur
    final public static String LOGIN_ACTION = "/login.do";
    final public static String LOGOUT_ACTION = "/logout.do";
    final public static String COURSE_ACTION = "/course.do";
    final public static String HOME_ACTION = "/home.do";

    // KEYS
    final public static String KEY_USERNAME = "user_name";
    final public static String KEY_PASSWORD = "password";
    final public static String KEY_COURSE_TYPE = "type";
    final public String KEY_USER = "user";


    // Messages
    public static final String EMPTY_PASSWORD = "Mot de passe vide";
    public String INCORRECTS_IDS = "indentifiants incorrects";
    public String DICONNECTED_MESSAGE = "Vous êtes déconnecté";

    /**
     * Demarrage du contrôleur 
     * Procède aux initilialisations nécessaires aux contrôleurs
     *
     * Méhode qui appartient au cycle de vie d'une servlet
     * 
     * @param c
     * @throws ServletException
     */
    public void init(ServletConfig c) throws ServletException;

    
     
    /**
     * Traite l'URL courante
     *
     * Correspond aux traitements réalisés par le contrôleur
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;

    /**
     * Exécute les actions demandées
     *
     * @param request
     * @param response
     * @return (String) : URL de la vue à activer après l'exécution de l'action
     * @throws ServletException
     */
    String processAction(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;

    /**
     * Active la vue demandée
     *
     * @param view (String) :  URL de la vue à activer
     * @param request
     * @param response
     * @throws ServletException
     */
    void processView(String view, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;

    /**
     * Traite l'action de login
     *
     * @param request
     * @return (String) : URL de la vue à activer après l'exécution de 
     *                  l'action
     */
    String doLogin(HttpServletRequest request);

    /**
     * Traite l'action de logout
     *
     * @param request
     * @return (String) : URL de la vue à activer après l'exécution de 
     *                  l'action
     * 
    String doLogout(HttpServletRequest request);

    /**
     * Traite l'action de home
     *
     * @param request
     * @return  (String) : URL de la vue à activer après l'exécution de 
     *                  l'action
     */
    public String doHome(HttpServletRequest request);

    /**
     * Intérception de la requête avant son traitement par le controleur Permet
     * de faire un (éventuel) pré-traitement avant le traitement par le
     * contrôleur
     *
     * @param request
     * @return (String) : URL de la vue à activer après l'exécution de 
     *                    l'action
     */
    public String beforeAllProcessing(HttpServletRequest request);

  
    /**
     * Interception de la requête lors d'une erreur
     *
     * @param request
     * @param response
     * @param e
     * @return (String) : URL de la vue à activer après l'interception 
     *                    de l'erreur (vue qui affichera un message)
     */
    public String error(HttpServletRequest request,
            HttpServletResponse response, Throwable e);

}
