/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.HinhThucThanhToanDAO;
import quanlysieuthimini.DTO.HinhThucThanhToanDTO;

public class HinhThucThanhToanBUS {
    private final HinhThucThanhToanDAO htttDAO = new HinhThucThanhToanDAO();
    private ArrayList<HinhThucThanhToanDTO> listHinhThucThanhToan = new ArrayList<>();
    
    public HinhThucThanhToanBUS() {
        this.listHinhThucThanhToan = htttDAO.getAll();
    }

    public ArrayList<HinhThucThanhToanDTO> getAll() {
        return this.listHinhThucThanhToan;
    }

    public HinhThucThanhToanDTO getByIndex(int index) {
        return this.listHinhThucThanhToan.get(index);
    }

    public boolean add(HinhThucThanhToanDTO HinhThucThanhToan) {
        boolean check = htttDAO.insert(HinhThucThanhToan);
        if (check) {
            this.listHinhThucThanhToan.add(HinhThucThanhToan);
        }
        return check;
    }
    
    public boolean delete(HinhThucThanhToanDTO HinhThucThanhToan, int index) {
        boolean check = htttDAO.delete(HinhThucThanhToan.getMaHTTT());
        if (check) {
            this.listHinhThucThanhToan.remove(index);
        }
        return check;
    }

    public boolean update(HinhThucThanhToanDTO HinhThucThanhToan) {
        boolean check = htttDAO.update(HinhThucThanhToan);
        if (check) {
            this.listHinhThucThanhToan.set(getIndexByMaHinhThucThanhToan(HinhThucThanhToan.getMaHTTT()), HinhThucThanhToan);
        }
        return check;
    }
    
    public int getIndexByMaHinhThucThanhToan(int maTL) {
        int i = 0;
        int vitri = -1;
        while (i < this.listHinhThucThanhToan.size() && vitri == -1) {
            if (listHinhThucThanhToan.get(i).getMaHTTT() == maTL) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrTenHinhThucThanhToan() {
        int size = listHinhThucThanhToan.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listHinhThucThanhToan.get(i).getTenHTTT();
        }
        return result;
    }

    public String getTenHinhThucThanhToan(int maHinhThucThanhToan) {
        return this.listHinhThucThanhToan.get(getIndexByMaHinhThucThanhToan(maHinhThucThanhToan)).getTenHTTT();
    }
    
    public HinhThucThanhToanDTO findCT(ArrayList<HinhThucThanhToanDTO> HinhThucThanhToan, String tenTL) {
        HinhThucThanhToanDTO p = null;
        int i = 0;
        while (i < HinhThucThanhToan.size() && p == null) {
            if (HinhThucThanhToan.get(i).getTenHTTT().equals(tenTL)) {
                p = HinhThucThanhToan.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
    
    public ArrayList<HinhThucThanhToanDTO> search(String txt, String type) {
        ArrayList<HinhThucThanhToanDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (HinhThucThanhToanDTO i : listHinhThucThanhToan) {
                    if (Integer.toString(i.getMaHTTT()).contains(txt) || i.getTenHTTT().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã Đơn Vị" -> {
                for (HinhThucThanhToanDTO i : listHinhThucThanhToan) {
                    if (Integer.toString(i.getMaHTTT()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên Đơn Vị" -> {
                for (HinhThucThanhToanDTO i : listHinhThucThanhToan) {
                    if (i.getTenHTTT().toLowerCase().contains(txt)) {
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
//        while (i <= this.listHinhThucThanhToan.size() && check == true) {
//            if (this.listHinhThucThanhToan.get(i).getTenHTTT().toLowerCase().contains(name.toLowerCase())) {
//                check = false;
//            } else {
//                i++;
//            }
//        }
//        return check;
//    }
    
        public boolean checkDup(String name) {
//        boolean check = false;
        for (HinhThucThanhToanDTO mau : listHinhThucThanhToan) {
        if (mau.getTenHTTT().equalsIgnoreCase(name)) {
            return true; // Trùng tên màu
        }
    }
        return false;
    }
    
}
