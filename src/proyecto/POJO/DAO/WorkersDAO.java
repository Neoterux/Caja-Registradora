package proyecto.POJO.DAO;

import proyecto.POJO.Worker;

import java.util.List;

public interface WorkersDAO {

    public boolean reg(Worker worker);
    public List<Worker> get();
    public boolean update(Worker worker);
    public boolean delete(Worker worker);
    public boolean isAdmin(Worker worker);
    public Worker specific(Worker worker);
}
