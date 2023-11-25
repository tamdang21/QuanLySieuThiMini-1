/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.HangSanXuatDAO;
import quanlysieuthimini.DTO.HangSanXuatDTO;

public class HangSanXuatBUS {
    private final HangSanXuatDAO hangsxDAO = new HangSanXuatDAO();
    private ArrayList<HangSanXuatDTO> listHangSX = new ArrayList<>();
    
    public HangSanXuatBUS() {
        this.listHangSX = hangsxDAO.getAll();
    }

    public ArrayList<HangSanXuatDTO> getAll() {
        return this.listHangSX;
    }

    public HangSanXuatDTO getByIndex(int index) {
        return this.listHangSX.get(index);
    }

    public boolean add(HangSanXuatDTO ncc) {
        boolean check = hangsxDAO.insert(ncc);
        if (check) {
            this.listHangSX.add(ncc);
        }
        return check;
    }
    
    public boolean delete(HangSanXuatDTO ncc, int index) {
        boolean check = hangsxDAO.delete(ncc.getMaHang());
        if (check) {
            this.listHangSX.remove(index);
        }
        return check;
    }

    public boolean update(HangSanXuatDTO ncc) {
        boolean check = hangsxDAO.update(ncc);
        if (check) {
            this.listHangSX.set(getIndexByMaNCC(ncc.getMaHang()), ncc);
        }
        return check;
    }
    
    public int getIndexByMaNCC(int maTL) {
        int i = 0;
        int vitri = -1;
        while (i < this.listHangSX.size() && vitri == -1) {
            if (listHangSX.get(i).getMaHang() == maTL) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrTenHang() {
        int size = listHangSX.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listHangSX.get(i).getTenHang();
        }
        return result;
    }

    public String getTenHang(int mancc) {
        return this.listHangSX.get(getIndexByMaNCC(mancc)).getTenHang();
    }
    
    public HangSanXuatDTO findCT(ArrayList<HangSanXuatDTO> ncc, String tenTL) {
        HangSanXuatDTO p = null;
        int i = 0;
        while (i < ncc.size() && p == null) {
            if (ncc.get(i).getTenHang().equals(tenTL)) {
                p = ncc.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
    
    public ArrayList<HangSanXuatDTO> search(String txt, String type) {
        ArrayList<HangSanXuatDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (HangSanXuatDTO i : listHangSX) {
                    if (Integer.toString(i.getMaHang()).contains(txt) || i.getTenHang().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã Hãng" -> {
                for (HangSanXuatDTO i : listHangSX) {
                    if (Integer.toString(i.getMaHang()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên Hãng" -> {
                for (HangSanXuatDTO i : listHangSX) {
                    if (i.getTenHang().toLowerCase().contains(txt)) {
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
//        while (i <= this.listHangSX.size() && check == true) {
//            if (this.listHangSX.get(i).getTenHang().toLowerCase().contains(name.toLowerCase())) {
//                check = false;
//            } else {
//                i++;
//            }
//        }
//        return check;
//    }
    
        
    public boolean checkDup(String tenhang, String truso) {
//        boolean check = false;
        for (HangSanXuatDTO mau : listHangSX) {
        if (mau.getTenHang().equalsIgnoreCase(tenhang) && mau.getTruSo().equalsIgnoreCase(truso)) {
            return true; // Trùng tên màu
        }
    }
        return false;
    }
  
}
