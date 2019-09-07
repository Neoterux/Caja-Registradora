/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.Utils;

import java.util.UUID;

/**
 *
 * @author Neoterux
 */
public class RandomUtils {
    
    /**
     * Genera un UUID random 
     * @return String del UUID randomS
     */
    public static String randID(){
        String x = "";
        UUID id = UUID.randomUUID();
        
        //x =id.toString().replaceAll("-", "");
        x = id.toString();
        System.out.println("[RANDUTILS RUID]\n"
                +"[ORIGINAL]: " + id.toString()
                + "\n[REPLACED]: " + x);
        return x;
    }
    
}
