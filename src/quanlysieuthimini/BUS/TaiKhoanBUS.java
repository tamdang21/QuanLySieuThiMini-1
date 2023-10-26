
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.QuyenDAO;
import quanlysieuthimini.DAO.TaiKhoanDAO;
import quanlysieuthimini.DTO.QuyenDTO;
import quanlysieuthimini.DTO.TaiKhoanDTO;

public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private ArrayList<QuyenDTO> listQuyen;
    private QuyenDAO quyenDAO = QuyenDAO.getInstance();
    
    public TaiKhoanBUS(){
        this.listTaiKhoan  = TaiKhoanDAO.getInstance().getAll();
        this.listQuyen = QuyenDAO.getInstance().getAll();
    }
    
    public ArrayList<TaiKhoanDTO> getTaiKhoanAll(){
        return listTaiKhoan;
    }
    
    public TaiKhoanDTO getTaiKhoan(int index){
        return listTaiKhoan.get(index);
    }
    
    public int getTaiKhoanByMaNV(int manv){
        int i = 0;
        int vitri = -1;
        while (i < this.listTaiKhoan.size() && vitri == -1) {
            if (listTaiKhoan.get(i).getMaNV()== manv) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public QuyenDTO getQuyenDTO(int manhom){
        return quyenDAO.getById(manhom);
    }
    
    public void addAcc(TaiKhoanDTO tk){
        listTaiKhoan.add(tk);
    }
    
    public void updateAcc(int index, TaiKhoanDTO tk){
        listTaiKhoan.set(index, tk);
    }
    
    public void deleteAcc(int manv){
        
    }
    public ArrayList<TaiKhoanDTO> search(String txt, String type) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (Integer.toString(i.getMaNV()).contains(txt) || i.getTenTK().contains(txt) ) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhân viên" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (Integer.toString(i.getMaNV()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Username" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (i.getTenTK().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

}
