/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.DAO;

import java.util.Date;
import java.util.List;
import proyecto.POJO.Order;
import proyecto.POJO.OrderModel;


/**
 *Interfaz para transision de datos con la base de datos
 * @author Neoterux
 */
public interface OrderDAO {
    
    /**
     *
     * @param order
     * @return
     */
    public boolean register(Order order);
    
    /**
     *
     * @param order
     * @return
     */
    public boolean delete (Order order);
    
    /**
     *
     * @param order
     * @return
     */
    public boolean update (Order order);
    
    /**
     *
     * @return
     */
    public List<Order> get();
    
    /**
     *
     * @param IDorCID
     * @return
     */
    public List<OrderModel> searchByIdOrCID(String IDorCID);
    
    /**
     *
     * @param first
     * @param end
     * @return
     */
    public List<OrderModel> searchByDate(Date first, Date end);
    
}
