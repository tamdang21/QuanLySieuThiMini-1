package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.HoaDonBUS;
import quanlysieuthimini.BUS.SanPhamBUS;
import quanlysieuthimini.DAO.KhachHangThanThietDAO;
import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.DAO.SanPhamDAO;
import quanlysieuthimini.DTO.ChiTietHoaDonDTO;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.helper.Formater;
import quanlysieuthimini.helper.writePDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import quanlysieuthimini.BUS.DonViBUS;
import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.BUS.KhachHangThanThietBUS;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DTO.SanPhamDTO;

public final class ChiTietHoaDonDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaHD, txtNhanVien, txtKhachHang, txtKhuyenMai, txtNgayLap;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    HoaDonDTO hoadon;
    HoaDonBUS hoadonBus;
    KhachHangThanThietBUS khachhangBUS = new KhachHangThanThietBUS();
    KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();
    SanPhamBUS sanPhamBUS = new SanPhamBUS();
    DonViBUS donviBUS = new DonViBUS();
    double tienGiam;
    ButtonCustom btnPdf, btnHuyBo;

    ArrayList<ChiTietHoaDonDTO> ctHoaDon;

    public ChiTietHoaDonDialog(JFrame owner, String title, boolean modal, HoaDonDTO hoadonDTO) {
        super(owner, title, modal);
        this.hoadon = hoadonDTO;
        hoadonBus = new HoaDonBUS();
        ctHoaDon = hoadonBus.selectCTP(hoadon.getMaHD());
        tienGiam = hoadonBus.getTongThanhTien(hoadon.getMaHD()) * khuyenmaiBUS.getById(hoadon.getMaHD()).getPhanTramKM();
        
        initComponent(title);
        initHoaDon();
        loadDataTableChiTietHoaDon(ctHoaDon);
        this.setVisible(true);
    }

    public void initHoaDon() {
        txtMaHD.setText("HD-" + Integer.toString(this.hoadon.getMaHD()));
        txtKhachHang.setTitle("Khách hàng");
        txtKhachHang.setText(khachhangBUS.getTenKhachHang(hoadon.getMaKH()));
        txtNhanVien.setText(NhanVienDAO.getInstance().getById(hoadon.getMaNV()).getTenNV());
        txtKhuyenMai.setText(khuyenmaiBUS.getTenKhuyenMai(hoadon.getMaKM()));
        System.out.println(hoadon.toString());
        txtNgayLap.setText(Formater.FormatTime(hoadon.getNgayLap()));
    }

    public void loadDataTableChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> cthd) {
        tblModel.setRowCount(0);
        int size = cthd.size();
        for (int i = 0; i < size; i++) {
            SanPhamDTO sanpham = sanPhamBUS.getById(cthd.get(i).getMaSP());
            tblModel.addRow(new Object[]{
                i + 1,
                sanpham.getMaSP(),
                sanpham.getTenSP(),
                sanpham.getDungTich() + " " + donviBUS.getTenDonVi(sanpham.getMaDV()),
                Formater.FormatVND(cthd.get(i).getDonGia()), 
                cthd.get(i).getSoLuong(),
                Formater.FormatVND(cthd.get(i).getThanhTien())
            });
        }
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaHD = new InputForm("Mã hóa đơn");
        txtNhanVien = new InputForm("Nhân viên bán hàng");
        txtKhachHang = new InputForm("Khách hàng");
        txtKhuyenMai = new InputForm("Khuyến mãi");
        txtNgayLap = new InputForm("Thời gian tạo");

        txtMaHD.setEditable(false);
        txtNhanVien.setEditable(false);
        txtKhachHang.setEditable(false);
        txtKhuyenMai.setEditable(false);
        txtNgayLap.setEditable(false);

        pnmain_top.add(txtMaHD);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtKhachHang);
        pnmain_top.add(txtKhuyenMai);
        pnmain_top.add(txtNgayLap);

        pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Dung tích", "Đơn giá", "Số lượng", "Thành tiền"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int index = table.getSelectedRow();
//                if (index != -1) {
//                    loadDataTableImei(chitietsanpham.get(ctHoaDon.get(index).getMaSP()));
//                }
//            }
//        });
        pnmain_bottom_left.add(scrollTable);

//        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
//        pnmain_bottom_right.setPreferredSize(new Dimension(200, 10));
//        tblImei = new JTable();
//        scrollTableImei = new JScrollPane();
//        tblModelImei = new DefaultTableModel();
//        tblModelImei.setColumnIdentifiers(new String[]{"STT", "Mã Imei"});
//        tblImei.setModel(tblModelImei);
//        tblImei.setFocusable(false);
//        tblImei.setDefaultRenderer(Object.class, centerRenderer);
//        tblImei.getColumnModel().getColumn(1).setPreferredWidth(170);
//        scrollTableImei.setViewportView(tblImei);
//        pnmain_bottom_right.add(scrollTableImei);

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
        //pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            writePDF w = new writePDF();
            if (this.hoadon != null) {
                w.writeHoaDon(hoadon.getMaHD(),tienGiam);
            }
        }
    }
}