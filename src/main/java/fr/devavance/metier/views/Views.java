/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.metier.views;

import fr.devavance.metier.beans.Course;
import fr.devavance.metier.beans.Seance;
import fr.devavance.metier.beans.User;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 * Les vues de l'application
 * @author B. LEMAIRE
 */
public class Views {
    
    
    public final static String URL_LOGIN_FORM = "/views/loginForm.jsp";
    public final static String URL_HOME = "/views/home.jsp";
    public final static String URL_MESSAGE = "/views/message.jsp";
    public final static String URL_COURSE = "/views/course.jsp";

    public String loginForm(HttpServletRequest request, User user, String message) {
        request.setAttribute("user", user);
        request.setAttribute("message", message);
        return (URL_LOGIN_FORM);
    }

    public String home(HttpServletRequest request, User user) {
        request.setAttribute("user", user);
        return (URL_HOME);
    }
    
    public String course(HttpServletRequest request, User user, Seance seance) {
        request.setAttribute("user", user);
        request.setAttribute("seance", seance);
        return (URL_COURSE);
    }
      
    
   public String message(HttpServletRequest request, String message){
       
        request.setAttribute("message", message);
        return (URL_MESSAGE);
   }

}