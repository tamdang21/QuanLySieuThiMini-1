package quanlysieuthimini.DAO.DAOInterface;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public boolean insert(T t);
    
    public boolean update(T t);
    
    public boolean delete(int id);
    
    public ArrayList<T> getAll();
    
    public T getById(int id);
    
    public int getAutoIncrement();
}
