package proyecto.POJO.Controller;


import proyecto.POJO.DAO.WorkerDaoImpl;
import proyecto.POJO.Worker;

import java.util.List;
/**
 * 
 * @author Neoterux
 */

public class ControllerWorker {

    /**
     *
     * @param worker
     */
    public void register(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.reg(worker);
    }

    /**
     *
     * @param worker
     */
    public void update(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.update(worker);
    }

    /**
     *
     * @param worker
     */
    public void delete(Worker worker){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        dao.delete(worker);
    }

    /**
     *
     * @return
     */
    public List<Worker> getList(){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        return dao.get();
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Worker> search(String name){
        WorkerDaoImpl dao = new WorkerDaoImpl();
        return dao.search(name);
    }

    /**
     *
     * @param worker
     * @return
     */
    public boolean isAdmin(Worker worker){
        return new WorkerDaoImpl().isAdmin(worker);
    }
    
    /**
     *
     * @param worker
     * @return
     */
    public Worker getSpecific(Worker worker){
        
        return new WorkerDaoImpl().specific(worker);
    }
}
