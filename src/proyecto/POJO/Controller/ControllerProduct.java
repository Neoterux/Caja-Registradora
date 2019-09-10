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

    
    private final ProductDaoImpl pdao = new ProductDaoImpl();
    /**
     *
     * @param p
     */
    public boolean update(Producto p){
        return pdao.update(p);
    }
    
    /**
     *
     * @param p
     */
    public boolean delete(Producto p){
        return pdao.delete(p);
    }
    
    /**
     *
     * @return
     */
    public List<Producto> list(){
        return pdao.get();
    }
    
    /**
     *
     * @param p
     */
    public boolean register(Producto p){
        return pdao.register(p);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Producto getFromID(String id){
        return pdao.getFromId(id);
    }
    
    /**
     *
     * @param IDorName
     * @return
     */
    public List<ProductModel> searchByIDorName(String IDorName){
        return pdao.searchByIDorName(IDorName);
    }
}
