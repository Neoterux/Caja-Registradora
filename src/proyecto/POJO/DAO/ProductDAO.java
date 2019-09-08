/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.DAO;

import java.util.List;
import proyecto.POJO.ProductModel;
import proyecto.POJO.Producto;

/**
 * Interfaz para la transision de datos con la base de datos
 * @author Neoterux
 */
public interface ProductDAO {
    
    /**
     *
     * @param product
     * @return
     */
    public boolean register(Producto product);
    
    /**
     *
     * @param product
     * @return
     */
    public boolean delete (Producto product);
    
    /**
     *
     * @param product
     * @return
     */
    public boolean update (Producto product);
    
    /**
     *
     * @return
     */
    public List<Producto> get();
    
    /**
     *
     * @param id
     * @return
     */
    public Producto getFromId(String id);
    
    /**
     *
     * @param IDorName
     * @return
     */
    public List<ProductModel> searchByIDorName(String IDorName);
    
}
