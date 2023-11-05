/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DTO.LoaiSanPhamDTO;
import quanlysieuthimini.DAO.LoaiSanPhamDAO;
import quanlysieuthimini.DTO.HangSanXuatDTO;
        
/**
 *
 * @author Admin
 */
public class LoaiSanPhamBUS {
    private final LoaiSanPhamDAO loaispDAO = new LoaiSanPhamDAO();
    private ArrayList<LoaiSanPhamDTO> listLoaiSP = new ArrayList<>();
    
    public LoaiSanPhamBUS() {
        this.listLoaiSP = loaispDAO.getAll();
    }

    public ArrayList<LoaiSanPhamDTO> getAll() {
        return this.listLoaiSP;
    }

    public LoaiSanPhamDTO getByIndex(int index) {
        return this.listLoaiSP.get(index);
    }

    public boolean add(LoaiSanPhamDTO ncc) {
        boolean check = loaispDAO.insert(ncc);
        if (check) {
            this.listLoaiSP.add(ncc);
        }
        return check;
    }
    
    public boolean delete(LoaiSanPhamDTO ncc, int index) {
        boolean check = loaispDAO.delete(ncc.getMaLoai());
        if (check) {
            this.listLoaiSP.remove(index);
        }
        return check;
    }

    public boolean update(LoaiSanPhamDTO ncc) {
        boolean check = loaispDAO.update(ncc);
        if (check) {
            this.listLoaiSP.set(getIndexByMaNCC(ncc.getMaLoai()), ncc);
        }
        return check;
    }
    
    public int getIndexByMaNCC(int maTL) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLoaiSP.size() && vitri == -1) {
            if (listLoaiSP.get(i).getMaLoai() == maTL) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrTenLoai() {
        int size = listLoaiSP.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLoaiSP.get(i).getTenLoai();
        }
        return result;
    }

    public String getTenLoai(int mancc) {
        return this.listLoaiSP.get(getIndexByMaNCC(mancc)).getTenLoai();
    }
    
    public LoaiSanPhamDTO findCT(ArrayList<LoaiSanPhamDTO> ncc, String tenTL) {
        LoaiSanPhamDTO p = null;
        int i = 0;
        while (i < ncc.size() && p == null) {
            if (ncc.get(i).getTenLoai().equals(tenTL)) {
                p = ncc.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
    
    public ArrayList<LoaiSanPhamDTO> search(String txt, String type) {
        ArrayList<LoaiSanPhamDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (LoaiSanPhamDTO i : listLoaiSP) {
                    if (Integer.toString(i.getMaLoai()).contains(txt) || i.getTenLoai().contains(txt) || i.getCachBaoQuan().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã Loại" -> {
                for (LoaiSanPhamDTO i : listLoaiSP) {
                    if (Integer.toString(i.getMaLoai()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên Loại" -> {
                for (LoaiSanPhamDTO i : listLoaiSP) {
                    if (i.getTenLoai().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Cách Bảo Quản" -> {
                for (LoaiSanPhamDTO i : listLoaiSP) {
                    if (i.getCachBaoQuan().toLowerCase().contains(txt)) {
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
//        while (i <= this.listLoaiSP.size() && check == true) {
//            if (this.listLoaiSP.get(i).getTenLoai().toLowerCase().contains(name.toLowerCase())) {
//                check = false;
//            } else {
//                i++;
//            }
//        }
//        return check;
//    }
    
        public boolean checkDup(String name) {
//        boolean check = false;
        for (LoaiSanPhamDTO mau : listLoaiSP) {
        if (mau.getTenLoai().equalsIgnoreCase(name)) {
            return true; // Trùng tên màu
        }
    }
        return false;
    }
}
