/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.metier;

import fr.devavance.metier.beans.Course;
import fr.devavance.metier.beans.User;

/**
 *
 * @author bouchaib.lemaire
 */
public class Authentication {
    
    final static String USERNAME = "user";
    final static String PASSWORD = "password";
    
        public void authenticate(User user) {
        if (user.getUserName().equals(Authentication.USERNAME)
                && user.getPassword().equals(Authentication.PASSWORD)) {

            user.setAuth(true);
        }

    }
}
