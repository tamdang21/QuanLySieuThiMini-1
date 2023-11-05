package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.HoaDonDAO;
import quanlysieuthimini.DAO.KhachHangThanThietDAO;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.DTO.KhachHangThanThietDTO;

public class KhachHangThanThietBUS {

    private final KhachHangThanThietDAO khDAO = new KhachHangThanThietDAO();
    public ArrayList<KhachHangThanThietDTO> listKhachHang = new ArrayList<>();
    private ArrayList<HoaDonDTO> listHoaDon = HoaDonDAO.getInstance().getAll();

    public KhachHangThanThietBUS() {
        listKhachHang = khDAO.getAll();
    }

    public ArrayList<KhachHangThanThietDTO> getAll() {
        return this.listKhachHang;
    }

    public KhachHangThanThietDTO getByIndex(int index) {
        return this.listKhachHang.get(index);
    }

    public int getIndexByMaDV(int makhachhang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKhachHang.size() && vitri == -1) {
            if (listKhachHang.get(i).getMaKH() == makhachhang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(KhachHangThanThietDTO kh) {
        boolean check = khDAO.insert(kh);
        if (check) {
            this.listKhachHang.add(kh);
        }
        return check;
    }

    public Boolean delete(KhachHangThanThietDTO kh) {
        boolean check = khDAO.delete(kh.getMaKH());
        if (check) {
            this.listKhachHang.remove(getIndexByMaDV(kh.getMaKH()));
        }
        return check;
    }

    public Boolean update(KhachHangThanThietDTO kh) {
        boolean check = khDAO.update(kh);
        if (check) {
            this.listKhachHang.set(getIndexByMaDV(kh.getMaKH()), kh);
        }
        return check;
    }

    public ArrayList<KhachHangThanThietDTO> search(String text, String type) {
        ArrayList<KhachHangThanThietDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhachHangThanThietDTO i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text) || i.getTenKH().toLowerCase().contains(text) || i.getDiaChi().toLowerCase().contains(text) || i.getSDT().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã khách hàng" -> {
                for (KhachHangThanThietDTO i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khách hàng" -> {
                for (KhachHangThanThietDTO i : this.listKhachHang) {
                    if (i.getTenKH().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhachHangThanThietDTO i : this.listKhachHang) {
                    if (i.getDiaChi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (KhachHangThanThietDTO i : this.listKhachHang) {
                    if (i.getSDT().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }

        }

        return result;
    }

    public String getTenKhachHang(int makh) {
        String name = "";
        for (KhachHangThanThietDTO khachHangDTO : listKhachHang) {
            if (khachHangDTO.getMaKH() == makh) {
                name = khachHangDTO.getTenKH();
            }
        }
        return name;
    }

    public String[] getArrTenKhachHang() {
        int size = listKhachHang.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listKhachHang.get(i).getTenKH();
        }
        return result;
    }

    public KhachHangThanThietDTO selectKh(int makh) {
        return khDAO.getById(makh);
    }
    
    public void tinhChietKhauTheoKH(int maKH) {
        for(HoaDonDTO hoadon : listHoaDon) {
            if(hoadon.getMaKH() == maKH) {
                
            }
        }
    }

}
