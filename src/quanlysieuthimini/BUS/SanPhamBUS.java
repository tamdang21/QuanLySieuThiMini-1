package quanlysieuthimini.BUS;

import quanlysieuthimini.DAO.SanPhamDAO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Dialog.SanPhamDialog;
import quanlysieuthimini.GUI.Panel.NhanVien;
import quanlysieuthimini.GUI.Panel.SanPham;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

public class SanPhamBUS{

    public SanPham sp;
    private JTextField textField;
    public final SanPhamDAO spDAO = SanPhamDAO.getInstance();
    private ArrayList<SanPhamDTO> listSP = SanPhamDAO.getInstance().getAll();

    public SanPhamBUS() {

    }

    public SanPhamBUS(SanPham sp) {
        this.sp = sp;
    }

    public SanPhamBUS(JTextField textField, SanPham sp) {
        this.textField = textField;
        this.sp = sp;
    }

    public ArrayList<SanPhamDTO> getAll() {
        return this.listSP;
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listSP.get(index);
    }
    
    public SanPhamDTO getById(int masp) {
        return SanPhamDAO.getInstance().getById(masp);
    }

    public int getIndexByMaSP(int masanpham) {
        int i = 0;
        int vitri = -1;
        while (i < this.listSP.size() && vitri == -1) {
            if (listSP.get(i).getMaSP() == masanpham) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(SanPhamDTO lh) {
        boolean check = spDAO.insert(lh);
        if (check) {
            this.listSP.add(lh);
        }
        return check;
    }

    public Boolean delete(SanPhamDTO lh) {
        boolean check = spDAO.delete(lh.getMaSP());
        if (check) {
            this.listSP.remove(lh);
        }
        return check;
    }

    public Boolean update(SanPhamDTO lh) {
        boolean check = spDAO.update(lh);
        if (check) {
            this.listSP.set(getIndexByMaSP(lh.getMaSP()), lh);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.listSP) {
            if (Integer.toString(i.getMaSP()).toLowerCase().contains(text) || i.getTenSP().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public int getQuantity() {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        int n = 0;
        for(SanPhamDTO i : this.listSP) {
            if (i.getSoLuong()!= 0) {
                n += i.getSoLuong();
            }
        }
        return n;
    }
    
    public boolean checkMaVachExists(String MaVach) {
        for(SanPhamDTO i : this.listSP) {
            if (i.getMaVach().equals(MaVach)) {
                return true;
            }
        }
        return false;
    }
    
    public SanPhamDTO getByMaVach(String mavach) {
        for(SanPhamDTO sp : listSP) {
            if(sp.getMaVach().equals(mavach))
                return sp;
        }
        
        return null;
    }
    
    public boolean updateQuantity(int masp, int soluong) {
        return spDAO.updateQuantity(masp, soluong);
    }
}
