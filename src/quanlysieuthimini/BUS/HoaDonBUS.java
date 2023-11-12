package quanlysieuthimini.BUS;

import quanlysieuthimini.DAO.ChiTietHoaDonDAO;
import quanlysieuthimini.DAO.HoaDonDAO;
import quanlysieuthimini.DTO.ChiTietHoaDonDTO;
import quanlysieuthimini.DTO.HoaDonDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HoaDonBUS {

    private final HoaDonDAO hoadonDAO = HoaDonDAO.getInstance();

    private final ChiTietHoaDonDAO chiTietHoaDonDAO = ChiTietHoaDonDAO.getInstance();
    private final ArrayList<HoaDonDTO> listHoaDon;

    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangThanThietBUS khBUS = new KhachHangThanThietBUS();
    KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();

    public HoaDonBUS() {
        this.listHoaDon = hoadonDAO.getAll();
    }

    public ArrayList<HoaDonDTO> getAll() {
        return this.listHoaDon;
    }

    public HoaDonDTO getSelect(int index) {
        return listHoaDon.get(index);
    }

    public void remove(int hd) {
        listHoaDon.remove(hd);
    }

    public void insert(HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> ct) {
        hoadonDAO.insert(hd);
        chiTietHoaDonDAO.insert(ct);
    }

    public double getTongThanhTien(int maHD){
        double tong = 0;
        for(ChiTietHoaDonDTO cthd: selectCTP(maHD)){
            tong += cthd.getThanhTien();
        }
        return tong;
    }
    
    public ArrayList<ChiTietHoaDonDTO> selectCTP(int maphieu) {
        return chiTietHoaDonDAO.getAll(maphieu);
    }
    

    public ArrayList<HoaDonDTO> fillerHoaDon(int type, String input, int makh, int manv, int makm, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());

        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());

        ArrayList<HoaDonDTO> result = new ArrayList<>();
        for (HoaDonDTO hoadon : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(hoadon.getMaHD()).contains(input)
                            || khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)
                            || nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)
                            || kmBUS.getTenKhuyenMai(hoadon.getMaKM()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(hoadon.getMaHD()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (kmBUS.getTenKhuyenMai(hoadon.getMaKM()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (khBUS.getTenKhachHang(hoadon.getMaKH()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 4 -> {
                    if (nvBUS.getNameById(hoadon.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || hoadon.getMaNV() == manv) 
                    && (makh == 0 || hoadon.getMaKH() == makh)
                    && (makm == 0 || hoadon.getMaKM()== makm)
                    && (hoadon.getNgayLap().compareTo(time_start) >= 0)
                    && (hoadon.getNgayLap().compareTo(time_end) <= 0)
                    && hoadon.getTongTien() >= price_min
                    && hoadon.getTongTien() <= price_max) {
                result.add(hoadon);
            }
        }

        return result;
    }

}
