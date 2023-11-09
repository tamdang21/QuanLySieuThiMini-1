/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.PhieuChiDAO;
import quanlysieuthimini.DTO.PhieuChiDTO;

/**
 *
 * @author domin
 */
public class PhieuChiBUS {
    public final PhieuChiDAO pcDAO = PhieuChiDAO.getInstance();
    private ArrayList<PhieuChiDTO> listPC = PhieuChiDAO.getInstance().getAll();

    public PhieuChiBUS() {
        this.listPC = pcDAO.getAll();
    }

    public ArrayList<PhieuChiDTO> getAll() {
        return this.listPC;
    }

    public PhieuChiDTO getByIndex(int index) {
        return this.listPC.get(index);
    }

    public PhieuChiDTO getByMaPC(int mapc) {
        int vitri = -1;
        int i = 0;
        while (i <= this.listPC.size() && vitri == -1) {
            if (this.listPC.get(i).getMaPC() == mapc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return this.listPC.get(vitri);
    }
    
    public PhieuChiDTO getByMaPN(int mapn) {
//        int vitri = -1;
//        int i = 0;
//        while (i < this.listPC.size() && vitri == -1) {
//            if (this.listPC.get(i).getMaPN() == mapn) {
//                vitri = i;
//            } else {
//                i++;
//            }
//        }
//        
//        return this.listPC.get(vitri);
        
        for(PhieuChiDTO pc : listPC) {
            if(pc.getMaPN() == mapn) {
                return pc;
            }
        }
        return null;
    }

    public int getIndexByMaPC(int masanpham) {
        int i = 0;
        int vitri = -1;
        while (i < this.listPC.size() && vitri == -1) {
            if (listPC.get(i).getMaPC() == masanpham) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(PhieuChiDTO lh) {
        boolean check = pcDAO.insert(lh);
        if (check) {
            this.listPC.add(lh);
        }
        return check;
    }

    public Boolean delete(PhieuChiDTO lh) {
        boolean check = pcDAO.delete(lh.getMaPC());
        if (check) {
            this.listPC.remove(lh);
        }
        return check;
    }

    public Boolean update(PhieuChiDTO lh) {
        boolean check = pcDAO.update(lh);
        if (check) {
            this.listPC.set(getIndexByMaPC(lh.getMaPC()), lh);
        }
        return check;
    }

    public ArrayList<PhieuChiDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<PhieuChiDTO> result = new ArrayList<>();
        for (PhieuChiDTO i : this.listPC) {
            if (Integer.toString(i.getMaPC()).toLowerCase().contains(text) || i.getTenNguoiChi().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
    
}
