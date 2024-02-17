package fr.devavance.metier.beans;

import fr.devavance.metier.TypeSeance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author bouchaib.lemaire
 */

@Data
public class Course {
    private Map<TypeSeance, ArrayList<Seance>> seances = new HashMap();
    
    
    public Seance getCourseFromType(TypeSeance type){
        // On considère qu'il y a qu'une seule seance par type de seance
        return this.seances.get(type).get(0); 
    }
}
