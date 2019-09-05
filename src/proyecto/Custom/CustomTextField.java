/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.Custom;

import javafx.scene.control.TextField;

/**
 *
 * @author labfe
 */
public class CustomTextField extends TextField{
    
    public boolean isNullOrEmpty(){
        return getText().trim().equals("") || getText().trim().isEmpty();
    }
    
    public boolean notIsNullOrEmpty(){
        return !getText().trim().equals("") || !getText().trim().isEmpty();
    }
    
}
