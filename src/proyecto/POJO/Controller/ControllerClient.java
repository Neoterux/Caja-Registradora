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
 * @author user
 */
public class ControllerClient {
    
    public void update(Clients c){
        new ClientDaoImpl().update(c);
    }
    
    public void delete(Clients c){
        new ClientDaoImpl().delete(c);
    }
    
    public List<Clients> list(){
        return new ClientDaoImpl().get();
    }
    
    public void register(Clients c){
        new ClientDaoImpl().register(c);
    }
    
    public Clients getFromCedula(String cedula){
        return new ClientDaoImpl().getFromCedula(cedula);
    }
    
    public List<Clients> search(String nameOrCed){
        return new ClientDaoImpl().search(nameOrCed);
    }
    
}
