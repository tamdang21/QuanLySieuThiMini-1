package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.PhieuNhapBUS;
import quanlysieuthimini.BUS.SanPhamBUS;
import quanlysieuthimini.DAO.NhaCungCapDAO;
import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.DAO.SanPhamDAO;
import quanlysieuthimini.DTO.PhieuNhapDTO;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import quanlysieuthimini.BUS.DonViBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.BUS.TaiKhoanBUS;
import quanlysieuthimini.DTO.ChiTietPhieuNhapDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Main;
import quanlysieuthimini.GUI.Panel.PhieuNhap;

public final class ChiTietPhieuNhapDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian;
    DefaultTableModel tblModel, tblModelImei;
    JTable table, tblImei;
    JScrollPane scrollTable, scrollTableImei;

    PhieuNhapDTO phieunhap;
    PhieuNhapBUS phieunhapBus;
    PhieuNhap phieunhapPanel;

    ButtonCustom btnPdf, btnHuyBo, btnDuyetPhieuNhap;

    ArrayList<ChiTietPhieuNhapDTO> chitietphieu;
    SanPhamBUS sanPhamBUS = new SanPhamBUS();
    DonViBUS donviBUS = new DonViBUS();
    TaiKhoanBUS taikhoanBUS = new TaiKhoanBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();

    //HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham = new HashMap<>();

    public ChiTietPhieuNhapDialog(JFrame owner, String title, boolean modal, PhieuNhapDTO phieunhapDTO) {
        super(owner, title, modal);
        phieunhapPanel = new PhieuNhap((Main) owner, nvBUS.getById(phieunhapDTO.getMaNV()));
        this.phieunhap = phieunhapDTO;
        phieunhapBus = new PhieuNhapBUS();
        chitietphieu = phieunhapBus.getChiTietPhieu_Type(phieunhapDTO.getMaPN());
        initComponent(title);
        initPhieuNhap();
        loadDataTableChiTietPhieuNhap(chitietphieu);
        this.setVisible(true);
    }

    public void initPhieuNhap() {
        txtMaPhieu.setText("PN" + Integer.toString(this.phieunhap.getMaPN()));
        txtNhaCungCap.setText(NhaCungCapDAO.getInstance().getById(phieunhap.getMaNCC()).getTenNCC());
        txtNhanVien.setText(NhanVienDAO.getInstance().getById(phieunhap.getMaNV()).getTenNV());
        txtThoiGian.setText(Formater.FormatTime(phieunhap.getNgayNhap()));
    }

    public void loadDataTableChiTietPhieuNhap(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            SanPhamDTO sanpham = sanPhamBUS.getByMaSP(ctPhieu.get(i).getMaSP());
            tblModel.addRow(new Object[]{
                i + 1,
                sanpham.getMaSP(),
                sanpham.getTenSP(),
                sanpham.getDungTich() + " " + donviBUS.getTenDonVi(sanpham.getMaDV()),
                Formater.FormatVND(ctPhieu.get(i).getDonGia()), 
                ctPhieu.get(i).getSoLuong(),
                Formater.FormatVND(ctPhieu.get(i).getThanhTien())
            });
        }
    }

//    public void loadDataTableImei(ArrayList<ChiTietSanPhamDTO> dssp) {
//        tblModelImei.setRowCount(0);
//        int size = dssp.size();
//        for (int i = 0; i < size; i++) {
//            tblModelImei.addRow(new Object[]{
//                i + 1, dssp.get(i).getImei()
//            });
//        }
//    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaCungCap = new InputForm("Nhà cung cấp");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtThoiGian);

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
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int index = table.getSelectedRow();
//                if (index != -1) {
//                    loadDataTableImei(chitietsanpham.get(chitietphieu.get(index).getMaphienbansp()));
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
        btnDuyetPhieuNhap = new ButtonCustom("Phê duyệt", "success", 14);
        
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        btnDuyetPhieuNhap.addActionListener(this);
        
        pnmain_btn.add(btnDuyetPhieuNhap);
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
            if (this.phieunhap != null) {
                w.writePN(phieunhap.getMaPN());
            }
        }
        if (source == btnDuyetPhieuNhap) {
            PhieuNhapDTO editTrangThaiPN = phieunhapBus.getById(Integer.parseInt(txtMaPhieu.getText().replace("PN", "")));
            if(editTrangThaiPN.getTrangThai() == 0) {
                JOptionPane.showMessageDialog(null, "Không thể phê duyệt phiếu đã bị hủy!!");
            }
            else {
                editTrangThaiPN.setTrangThai(2);
            
                phieunhapBus.update(editTrangThaiPN);

                phieunhapPanel.loadDataTalbe(phieunhapBus.getAll());

                JOptionPane.showMessageDialog(this, "Duyệt phiếu nhập thành công");
                
                ArrayList<HashMap> listCTPN = new ArrayList<>();
                    for (ChiTietPhieuNhapDTO ctpn : chitietphieu){
                        HashMap<String,Integer> map = new HashMap<>();
                        map.put("maSP",ctpn.getMaSP());
                        map.put("soLuong",ctpn.getSoLuong());
                        listCTPN.add(map);
                    }
                    for (HashMap<Integer,Integer> map : listCTPN){
                        int maSP = map.get("maSP");
                        int soLuong = sanPhamBUS.getById(maSP).getSoLuong() + map.get("soLuong");
                        sanPhamBUS.updateQuantity(maSP, soLuong);
                    }
                JOptionPane.showMessageDialog(this, "Đã cập nhật tồn kho");
                
                dispose();
            }
            
        }
    }
}
