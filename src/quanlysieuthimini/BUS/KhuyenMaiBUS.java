/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;

public class KhuyenMaiBUS {
    private final KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    public ArrayList<KhuyenMaiDTO> listKM = new ArrayList<>();

    public KhuyenMaiBUS() {
        listKM = kmDAO.getAll();
    }

    public ArrayList<KhuyenMaiDTO> getAll() {
        return this.listKM;
    }

    public KhuyenMaiDTO getByIndex(int index) {
        return this.listKM.get(index);
    }

    public int getIndexByMaKM(int maKM) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKM.size() && vitri == -1) {
            if (listKM.get(i).getMaKM() == maKM) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(KhuyenMaiDTO kh) {
        boolean check = kmDAO.insert(kh);
        if (check) {
            this.listKM.add(kh);
        }
        return check;
    }

    public Boolean delete(KhuyenMaiDTO kh) {
        boolean check = kmDAO.delete(kh.getMaKM());
        if (check) {
            this.listKM.remove(getIndexByMaKM(kh.getMaKM()));
        }
        return check;
    }

    public Boolean update(KhuyenMaiDTO kh) {
        boolean check = kmDAO.update(kh);
        if (check) {
            this.listKM.set(getIndexByMaKM(kh.getMaKM()), kh);
        }
        return check;
    }

//    public ArrayList<KhuyenMaiDTO> search(String text, String type) {
//        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
//        text = text.toLowerCase();
//        switch (type) {
//            case "Tất cả" -> {
//                for (KhuyenMaiDTO i : this.listKM) {
//                    if (Integer.toString(i.getMaKM()).toLowerCase().contains(text) || i.getTenKM().toLowerCase().contains(text) || i.getDiaChi().toLowerCase().contains(text) || i.getSDT().toLowerCase().contains(text)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Mã khách hàng" -> {
//                for (KhuyenMaiDTO i : this.listKM) {
//                    if (Integer.toString(i.getMaKM()).toLowerCase().contains(text)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Tên khách hàng" -> {
//                for (KhuyenMaiDTO i : this.listKM) {
//                    if (i.getTenKM().toLowerCase().contains(text)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Địa chỉ" -> {
//                for (KhuyenMaiDTO i : this.listKM) {
//                    if (i.getDiaChi().toLowerCase().contains(text)) {
//                        result.add(i);
//                    }
//                }
//            }
//            case "Số điện thoại" -> {
//                for (KhuyenMaiDTO i : this.listKM) {
//                    if (i.getSDT().toLowerCase().contains(text)) {
//                        result.add(i);
//                    }
//                }
//            }
//
//        }
//
//        return result;
//    }

    public String getTenKhuyenMai(int makm) {
        String name = "";
        for (KhuyenMaiDTO khachHangDTO : listKM) {
            if (khachHangDTO.getMaKM() == makm) {
                name = khachHangDTO.getTenKM();
            }
        }
        return name;
    }

    public String[] getArrTenKhuyenMai() {
        int size = listKM.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listKM.get(i).getTenKM();
        }
        return result;
    }

    public KhuyenMaiDTO selectKM(int makm) {
        return kmDAO.getById(makm);
    }

}
