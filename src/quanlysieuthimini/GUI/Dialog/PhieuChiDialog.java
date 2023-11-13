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
    JPanel pnmain, pnmain_top, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian, txtSoTienChi, txtLiDoChi, txtGhiChu;

    PhieuChiDTO phieuchi;
    PhieuChiBUS phieuchiBUS;
    PhieuNhap phieunhap;

    ButtonCustom btnPdf, btnHuyBo;

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
            txtLiDoChi.setText(phieuchi.getLyDoChi());
            txtGhiChu.setText("");
        }
    }
    
    public boolean checkTonTaiPC() {
        if(phieuchi == null) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập này chưa tạo phiếu chi", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void initComponent(PhieuNhap phieunhap, String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(3, 3));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Tên người chi");
        txtNhaCungCap = new InputForm("Tên người nhận");
        txtSoTienChi = new InputForm("Số tiền chi");
        txtThoiGian = new InputForm("Ngày chi");
        txtLiDoChi = new InputForm("Lí do chi");
        txtGhiChu = new InputForm("Ghi chú");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtSoTienChi.setEditable(false);
        txtThoiGian.setEditable(false);
        txtLiDoChi.setEditable(false);
        txtGhiChu.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtSoTienChi);
        pnmain_top.add(txtThoiGian);
        pnmain_top.add(txtLiDoChi);
        pnmain_top.add(txtGhiChu);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.CENTER);
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
    }
}
