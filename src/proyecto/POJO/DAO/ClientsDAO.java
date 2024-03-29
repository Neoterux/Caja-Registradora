package proyecto.POJO.DAO;

import java.util.List;
import proyecto.POJO.Clients;

/**
 * Interfaz para la conexion con la base de datos
 * @author Neoterux
 */
public interface ClientsDAO {
    
    public boolean register(Clients client);
    public List<Clients> get();
    public boolean update(Clients client);
    public boolean delete(Clients client);
    
    public Clients getFromCedula(String cedula);
    
    public List<Clients> search(String identifier);
    
    
}
