/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.Controller;

import java.util.List;
import proyecto.POJO.DAO.ProductDaoImpl;
import proyecto.POJO.ProductModel;
import proyecto.POJO.Producto;

/**
 *
 * @author labfe
 */
public class ControllerProduct {

    /**
     *
     * @param p
     */
    public void update(Producto p){
        new ProductDaoImpl().update(p);
    }
    
    /**
     *
     * @param p
     */
    public void delete(Producto p){
        new ProductDaoImpl().delete(p);
    }
    
    /**
     *
     * @return
     */
    public List<Producto> list(){
        return new ProductDaoImpl().get();
    }
    
    /**
     *
     * @param p
     */
    public void register(Producto p){
        new ProductDaoImpl().register(p);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Producto getFromID(String id){
        return new ProductDaoImpl().getFromId(id);
    }
    
    /**
     *
     * @param IDorName
     * @return
     */
    public List<ProductModel> searchByIDorName(String IDorName){
        return new ProductDaoImpl().searchByIDorName(IDorName);
    }
}
