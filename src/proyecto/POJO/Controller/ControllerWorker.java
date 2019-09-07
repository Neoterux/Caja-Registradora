package proyecto.POJO.Controller;


import proyecto.POJO.DAO.WorkerDaoImpl;
import proyecto.POJO.Worker;

import java.util.List;
/**
 * 
 * @author Neoterux
 */

public class ControllerWorker {

    public void register(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.reg(worker);
    }

    public void update(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.update(worker);
    }

    public void delete(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.delete(worker);
    }

    public List<Worker> getList(){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        return dao.get();
    }

    public List<Worker> search(String name){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        return dao.search(name);
    }
    public boolean isAdmin(Worker worker){
        return new WorkerDaoImpl().isAdmin(worker);
    }
    
    public Worker getSpecific(Worker worker){
        
        return new WorkerDaoImpl().specific(worker);
    }
}
