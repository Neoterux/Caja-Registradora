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
    public void update(Order o){
        new OrderDaoImpl().update(o);
    }
    
    public void delete(Order o){
        new OrderDaoImpl().delete(o);
    }
    
    public List<Order> list(){
        return new OrderDaoImpl().get();
    }
    
    public void register(Order o){
        new OrderDaoImpl().register(o);
    }
    
    public List<OrderModel> searchByDate(Date first, Date end){
        return new OrderDaoImpl().searchByDate(first, end);
    }
    
    public List<OrderModel> searchByIDorCID(String IDorCID){
        return new OrderDaoImpl().searchByIdOrCID(IDorCID);
    }
}
