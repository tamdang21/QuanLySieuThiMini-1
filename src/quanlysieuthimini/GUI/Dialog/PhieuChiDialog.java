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
import quanlysieuthimini.BUS.NhaCungCapBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.BUS.PhieuChiBUS;
import quanlysieuthimini.DAO.PhieuNhapDAO;
import quanlysieuthimini.DTO.NhaCungCapDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.DTO.PhieuChiDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Panel.PhieuNhap;

public final class PhieuChiDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian, txtSoTienChi;
    DefaultTableModel tblModel, tblModelImei;
    JTable table, tblImei;
    JScrollPane scrollTable, scrollTableImei;

    PhieuChiDTO phieuchi;
    PhieuChiBUS phieuchiBUS;
    PhieuNhap phieunhap;

    ButtonCustom btnPdf, btnHuyBo, btnChinhSua;

    ArrayList<PhieuChiDTO> listPC;
    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    NhanVienDTO nvDTO = new NhanVienDTO();
    NhaCungCapDTO nccDTO = new NhaCungCapDTO();
    int maquyen;
    int mapn, mancc, manv;

    public PhieuChiDialog(JFrame owner, String title, boolean modal, PhieuNhap phieunhap, int maquyen, int mapn) {
        super(owner, title, modal);
        this.phieunhap = phieunhap;
        this.maquyen = maquyen;
        mapn = mapn;
        phieuchiBUS = new PhieuChiBUS();
        phieuchi = phieuchiBUS.getByMaPN(mapn);
        listPC = phieuchiBUS.getAll();
        
        if(checkTonTaiPC()) {
            mancc = phieunhapBUS.getById(mapn).getMaNCC();
            manv = phieunhapBUS.getById(mapn).getMaNV();
            initComponent(phieunhap, title);
            initPhieuChi();
            loadDataTablePhieuChi(listPC);
            this.setVisible(true);
        }
    }

    public void initPhieuChi() {
        if(checkTonTaiPC()) {
            txtMaPhieu.setText("PC" + Integer.toString(phieuchi.getMaPC()));
            txtNhaCungCap.setText(nccBUS.getById(mancc).getTenNCC());
            txtNhanVien.setText(nvBUS.getById(manv).getTenNV());
            txtThoiGian.setText(Formater.FormatDate(phieuchi.getNgayChi()));
            txtSoTienChi.setText(Formater.FormatVND(phieuchi.getSoTienChi()));
        }
    }
    
    public boolean checkTonTaiPC() {
        if(phieuchi == null) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập này chưa tạo phiếu chi", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void loadDataTablePhieuChi(ArrayList<PhieuChiDTO> arrPC) {
        tblModel.setRowCount(0);
        for (PhieuChiDTO phieuchi : arrPC) {
            tblModel.addRow(new Object[]{
                phieuchi.getLyDoChi(),
                phieuchi.getGhiChu()
            });
        }
    }

    public void initComponent(PhieuNhap phieunhap, String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 5));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Tên người chi");
        txtNhaCungCap = new InputForm("Tên người nhận");
        txtSoTienChi = new InputForm("Số tiền chi");
        txtThoiGian = new InputForm("Ngày chi");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtSoTienChi.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtSoTienChi);
        pnmain_top.add(txtThoiGian);

        //pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom = new JPanel(new GridLayout(1, 1));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu chi", "Lý do chi", "Ghi chú"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        pnmain_bottom.add(scrollTable);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        //btnDuyetPhieuNhap = new ButtonCustom("Phê duyệt", "return", 14);
        btnChinhSua = new ButtonCustom("Chỉnh sửa", "success", 14);
        
//        if(maquyen == 1) {
//            btnChinhSua.setVisible(true);
//            //btnDuyetPhieuNhap.setVisible(true);
//        }
//        else {
//            btnChinhSua.setVisible(false);
//        }
        
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        //btnDuyetPhieuNhap.addActionListener(this);
        btnChinhSua.addActionListener(this);
        
        pnmain_btn.add(btnChinhSua);
        //pnmain_btn.add(btnDuyetPhieuNhap);
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
            if (this.phieuchi != null) {
                w.WritePhieuChi(phieuchi.getMaPC());
            }
        }
//        if (source == btnDuyetPhieuNhap) {
//            PhieuNhapDAO.getInstance().getById(Integer.parseInt(txtMaPhieu.getText())).setTrangThai(2);
//            phieunhap.loadDataTalbe(phieunhapBUS.getAll());
//        }
        if (source == btnChinhSua) {
            
        }
    }
}
