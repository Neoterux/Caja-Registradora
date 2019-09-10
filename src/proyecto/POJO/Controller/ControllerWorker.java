package proyecto.POJO.Controller;


import proyecto.POJO.DAO.WorkerDaoImpl;
import proyecto.POJO.Worker;

import java.util.List;
/**
 * 
 * @author Neoterux
 */

public class ControllerWorker {

    private final WorkerDaoImpl dao = new WorkerDaoImpl();
    /**
     *
     * @param worker
     */
    public boolean register(Worker worker){
        return dao.reg(worker);
    }

    /**
     *
     * @param worker
     */
    public boolean update(Worker worker){
        return dao.update(worker);
    }

    /**
     *
     * @param worker
     */
    public boolean delete(Worker worker){
        return dao.delete(worker);
    }

    /**
     *
     * @return
     */
    public List<Worker> getList(){
        return dao.get();
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Worker> search(String name){
        return dao.search(name);
    }

    /**
     *
     * @param worker
     * @return
     */
    public boolean isAdmin(Worker worker){
        return dao.isAdmin(worker);
    }
    
    /**
     *
     * @param worker
     * @return
     */
    public Worker getSpecific(Worker worker){
        
        return dao.specific(worker);
    }
}
