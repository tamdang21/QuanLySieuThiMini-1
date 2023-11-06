/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    
//    public ArrayList<KhuyenMaiDTO> fillerHoaDon(int type, String input, int makm, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
//        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
//        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
//        Timestamp time_start = new Timestamp(time_s.getTime());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(time_e.getTime());
//
//        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());
//
//        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
//        for (KhuyenMaiDTO hoadon : getAll()) {
//            boolean match = false;
//            switch (type) {
//                case 0 -> {
//                    if (Integer.toString(hoadon.getMaKM()).contains(input)
//                            || khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)
//                            || nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)
//                            || kmBUS.getTenKhuyenMai(hoadon.getMaKM()).toLowerCase().contains(input)) {
//                        match = true;
//                    }
//                }
//                case 1 -> {
//                    if (Integer.toString(hoadon.getMaHD()).contains(input)) {
//                        match = true;
//                    }
//                }
//                case 2 -> {
//                    if (kmBUS.getTenKhuyenMai(hoadon.getMaKM()).toLowerCase().contains(input)) {
//                        match = true;
//                    }
//                }
//                case 3 -> {
//                    if (khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)) {
//                        match = true;
//                    }
//                }
//                case 4 -> {
//                    if (nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)) {
//                        match = true;
//                    }
//                }
//            }
//
//            if (match
//                    && (manv == 0 || hoadon.getMaNV() == manv) 
//                    && (makh == 0 || hoadon.getMaKH() == makh)
//                    && (makm == 0 || hoadon.getMaKM()== makm)
//                    && (hoadon.getNgayLap().compareTo(time_start) >= 0)
//                    && (hoadon.getNgayLap().compareTo(time_end) <= 0)
//                    && hoadon.getTongTien() >= price_min
//                    && hoadon.getTongTien() <= price_max) {
//                result.add(hoadon);
//            }
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
