package proyecto.POJO.DAO;

import java.util.List;
import proyecto.POJO.Clients;

/**
 * Interfaz para la conexion con la base de datos
 * @author Neoterux
 */
public interface ClientsDAO {
    
    /**
     *
     * @param client
     * @return
     */
    public boolean register(Clients client);

    /**
     *
     * @return
     */
    public List<Clients> get();

    /**
     *
     * @param client
     * @return
     */
    public boolean update(Clients client);

    /**
     *
     * @param client
     * @return
     */
    public boolean delete(Clients client);
    
    /**
     *
     * @param cedula
     * @return
     */
    public Clients getFromCedula(String cedula);
    
    /**
     *
     * @param identifier
     * @return
     */
    public List<Clients> search(String identifier);
    
    
}
