package proyecto.POJO.DAO;

import proyecto.POJO.Worker;

import java.util.List;

/**
 * Interfaz de transicion de datos
 * @author Neoterux
 */
public interface WorkersDAO {

    /**
     *
     * @param worker
     * @return
     */
    public boolean reg(Worker worker);

    /**
     *
     * @return
     */
    public List<Worker> get();

    /**
     *
     * @param worker
     * @return
     */
    public boolean update(Worker worker);

    /**
     *
     * @param worker
     * @return
     */
    public boolean delete(Worker worker);

    /**
     *
     * @param worker
     * @return
     */
    public boolean isAdmin(Worker worker);

    /**
     *
     * @param worker
     * @return
     */
    public Worker specific(Worker worker);
}
