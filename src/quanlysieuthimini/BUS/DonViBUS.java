/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.DonViDAO;
import quanlysieuthimini.DTO.DonViDTO;

public class DonViBUS {
    private final DonViDAO donviDAO = new DonViDAO();
    private ArrayList<DonViDTO> listDonVi = new ArrayList<>();
    
    public DonViBUS() {
        this.listDonVi = donviDAO.getAll();
    }

    public ArrayList<DonViDTO> getAll() {
        return this.listDonVi;
    }

    public DonViDTO getByIndex(int index) {
        return this.listDonVi.get(index);
    }

    public boolean add(DonViDTO DonVi) {
        boolean check = donviDAO.insert(DonVi);
        if (check) {
            this.listDonVi.add(DonVi);
        }
        return check;
    }
    
    public boolean delete(DonViDTO DonVi, int index) {
        boolean check = donviDAO.delete(DonVi.getMaDV());
        if (check) {
            this.listDonVi.remove(index);
        }
        return check;
    }

    public boolean update(DonViDTO DonVi) {
        boolean check = donviDAO.update(DonVi);
        if (check) {
            this.listDonVi.set(getIndexByMaDonVi(DonVi.getMaDV()), DonVi);
        }
        return check;
    }
    
    public int getIndexByMaDonVi(int maTL) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDonVi.size() && vitri == -1) {
            if (listDonVi.get(i).getMaDV() == maTL) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrTenDonVi() {
        int size = listDonVi.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listDonVi.get(i).getTenDV();
        }
        return result;
    }

    public String getTenDonVi(int maDonVi) {
        return this.listDonVi.get(getIndexByMaDonVi(maDonVi)).getTenDV();
    }
    
    public DonViDTO findCT(ArrayList<DonViDTO> DonVi, String tenTL) {
        DonViDTO p = null;
        int i = 0;
        while (i < DonVi.size() && p == null) {
            if (DonVi.get(i).getTenDV().equals(tenTL)) {
                p = DonVi.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
    
    public ArrayList<DonViDTO> search(String txt, String type) {
        ArrayList<DonViDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (DonViDTO i : listDonVi) {
                    if (Integer.toString(i.getMaDV()).contains(txt) || i.getTenDV().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã Đơn Vị" -> {
                for (DonViDTO i : listDonVi) {
                    if (Integer.toString(i.getMaDV()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên Đơn Vị" -> {
                for (DonViDTO i : listDonVi) {
                    if (i.getTenDV().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }
    
//    public boolean checkDup(String name) {
//        boolean check = true;
//        int i = 0;
//        while (i <= this.listDonVi.size() && check == true) {
//            if (this.listDonVi.get(i).getTenDV().toLowerCase().contains(name.toLowerCase())) {
//                check = false;
//            } else {
//                i++;
//            }
//        }
//        return check;
//    }
    
        public boolean checkDup(String name) {
//        boolean check = false;
        for (DonViDTO mau : listDonVi) {
        if (mau.getTenDV().equalsIgnoreCase(name)) {
            return true; // Trùng tên màu
        }
    }
        return false;
    }
    
}
