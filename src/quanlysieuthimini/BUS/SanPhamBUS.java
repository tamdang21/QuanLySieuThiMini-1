package quanlysieuthimini.BUS;

import quanlysieuthimini.DAO.SanPhamDAO;
import quanlysieuthimini.DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {

    public final SanPhamDAO spDAO = new SanPhamDAO();
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    public SanPhamBUS() {
        listSP = spDAO.getAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        
        return this.listSP;
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listSP.get(index);
    }

    public SanPhamDTO getByMaSP(int masp) {
        int vitri = -1;
        int i = 0;
        while (i <= this.listSP.size() && vitri == -1) {
            if (this.listSP.get(i).getMaSP() == masp) {
                vitri = i;
            } else {
                i++;
            }
        }
        return this.listSP.get(vitri);
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
}
