package quanlysieuthimini.DAO.DAOInterface;

import java.util.ArrayList;

public interface ChiTietInterface<T> {
    public boolean insert(ArrayList<T> t);
    public boolean delete(int id);
    public boolean update(ArrayList<T> t, int pk);
    public ArrayList<T> getAll(int id);
}
