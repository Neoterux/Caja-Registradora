/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.POJO.Controller;

import java.util.Date;
import java.util.List;
import proyecto.POJO.DAO.OrderDaoImpl;
import proyecto.POJO.Order;
import proyecto.POJO.OrderModel;

/**
 *
 * @author Neoterux
 */
public class ControllerOrder {

    private final OrderDaoImpl odi;

    public ControllerOrder() {
        odi = new OrderDaoImpl();
    }
    /**
     * Update order
     * @param o order to update
     * @return true-> if run correctly
     *         false-> if has error in execution
     */
    public boolean update(Order o){
        return odi.update(o);
    }
    
    /**
     * Delete order
     * @param o order to delete
     * @return true-> if run correctly
     *         false-> if has error in execution
     */
    public boolean delete(Order o){
        return odi.delete(o);
    }
    
    /**
     * Get all rows of orders in database
     * @return all data from database
     */
    public List<Order> list(){
        return new OrderDaoImpl().get();
    }
    
    /**
     * Register a new order in database
     * @param o order to register
     * @return true-> if run correctly
     *         false-> if has error in execution
     */
    public boolean register(Order o){
       return odi.register(o);
    }
    
    /**
     * Search by date in order table
     * @param first start date in search range
     * @param end end date in search range
     * @return the list of orders that are in these dates
     */
    public List<OrderModel> searchByDate(Date first, Date end){
        return odi.searchByDate(first, end);
    }
    
    /**
     * Search by ID or CI
     * @param IDorCID ID or CI
     * @return the list of order that match the search
     */
    public List<OrderModel> searchByIDorCID(String IDorCID){
        return odi.searchByIdOrCID(IDorCID);
    }
}
