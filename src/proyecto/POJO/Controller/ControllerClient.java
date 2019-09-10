/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.Controller;

import java.util.List;
import proyecto.POJO.Clients;
import proyecto.POJO.DAO.ClientDaoImpl;

/**
 *
 * @author Neoterux
 */
public class ControllerClient {
    
    
    private final ClientDaoImpl cdi = new ClientDaoImpl();
    /**
     *
     * @param c
     * @return 
     */
    public boolean update(Clients c){
        return cdi.update(c);
    }
    
    /**
     *
     * @param c
     */
    public boolean delete(Clients c){
        return cdi.delete(c);
    }
    
    /**
     *
     * @return
     */
    public List<Clients> list(){
        return cdi.get();
    }
    
    /**
     *
     * @param c
     */
    public boolean register(Clients c){
        return cdi.register(c);
    }
    
    /**
     *
     * @param cedula
     * @return
     */
    public Clients getFromCedula(String cedula){
        return cdi.getFromCedula(cedula);
    }
    
    /**
     *
     * @param nameOrCed
     * @return
     */
    public List<Clients> search(String nameOrCed){
        return cdi.search(nameOrCed);
    }
    
}
