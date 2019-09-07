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
    
    public boolean register(Producto product);
    
    public boolean delete (Producto product);
    
    public boolean update (Producto product);
    
    public List<Producto> get();
    
    public Producto getFromId(String id);
    
    public List<ProductModel> searchByIDorName(String IDorName);
    
}
