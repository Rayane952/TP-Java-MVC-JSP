/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.metier.beans;

import fr.devavance.metier.TypeSeance;

/**
 *
 * @author marmotton
 */

   public class Seance {
       
        private TypeSeance type;
        private String intitule;
        private String contenu;

        public Seance() {
        }

        public Seance(TypeSeance type, String intitule, String contenu) {
            this.type = type;
            this.intitule = intitule;
            this.contenu = contenu;
        }

        public TypeSeance getType() {
            return type;
        }

        public void setType(TypeSeance type) {
            this.type = type;
        }

        public String getIntitule() {
            return intitule;
        }

        public void setIntitule(String intitule) {
            this.intitule = intitule;
        }

        public String getContenu() {
            return contenu;
        }

        public void setContenu(String contenu) {
            this.contenu = contenu;
        }  
    }