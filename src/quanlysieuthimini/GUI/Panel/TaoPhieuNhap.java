package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.NhaCungCapBUS;
import quanlysieuthimini.BUS.PhieuNhapBUS;
import quanlysieuthimini.BUS.SanPhamBUS;
import quanlysieuthimini.DTO.ChiTietPhieuNhapDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.DTO.PhieuNhapDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Component.NumericDocumentFilter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Component.SelectForm;
import quanlysieuthimini.GUI.Dialog.QRCode_Dialog;
import quanlysieuthimini.GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import quanlysieuthimini.helper.Formater;
import quanlysieuthimini.helper.Validation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import quanlysieuthimini.BUS.DonViBUS;
import quanlysieuthimini.BUS.HangSanXuatBUS;
import quanlysieuthimini.BUS.LoaiSanPhamBUS;

public final class TaoPhieuNhap extends JPanel implements ActionListener {

    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_btn;
    JTable tablePhieuNhap, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnQuetMa, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtDongia, txtMaVach, txtSoLuong;
    SelectForm cbxNhaCungCap;
    JTextField txtTimKiem;
    JLabel lbltongtien;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nccBus = new NhaCungCapBUS();
    PhieuNhapBUS phieunhapBus = new PhieuNhapBUS();
    HangSanXuatBUS hangsxBUS = new HangSanXuatBUS();
    DonViBUS donviBUS = new DonViBUS();
    LoaiSanPhamBUS loaispBUS = new LoaiSanPhamBUS();
    NhanVienDTO nvDto;

    ArrayList<SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<ChiTietPhieuNhapDTO> listCTPN;
    //ArrayList<String> listmaimei = new ArrayList<>();
    int maphieunhap;
    int rowPhieuSelect = -1;
    //private ButtonCustom scanImei, importImei;

    public TaoPhieuNhap(NhanVienDTO nv, String type, Main m) {
        this.nvDto = nv;
        this.m = m;
        maphieunhap = phieunhapBus.phieunhapDAO.getAutoIncrement();
        listCTPN = new ArrayList<>();
        initComponent(type);
        loadDataTalbeSanPham(listSP);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 5));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 5));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(5, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(5, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent(String type) {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Phiếu nhập
        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Loại", "Tịnh lượng", "Đơn giá", "Số lượng", "Thành tiền"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuNhap.getColumnModel();
        for (int i = 0; i < 8; i++) {
            if (i != 2 || i != 0 || i != 1) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(50);
        tablePhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        tablePhieuNhap.setFocusable(false);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);

        tablePhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tablePhieuNhap.getSelectedRow();
                if (index != -1) {
                    setFormChiTietPhieu(listCTPN.get(index));
                    rowPhieuSelect = index;
                    actionbtn("update");
                }
            }
        });

        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);

        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    resetForm();
                    setInfoSanPham(listSP.get(index));
                    ChiTietPhieuNhapDTO ctp = checkTonTai();
                    if (ctp == null) {
                        actionbtn("add");
                    } else {
                        actionbtn("update");
                        setFormChiTietPhieu(ctp);
                    }
                }
            }
        });

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ArrayList<SanPhamDTO> rs = spBUS.search(txtTimKiem.getText());
                loadDataTalbeSanPham(rs);
            }
        });
        
        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 260));
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);

        String[] arrCauhinh = {"Chọn sản phẩm"};
        JPanel content_right_top_cbx = new JPanel(new BorderLayout());
        content_right_top_cbx.setPreferredSize(new Dimension(100, 180));
        
        txtSoLuong = new InputForm("Số lượng");
        PlainDocument soluong = (PlainDocument) txtSoLuong.getTxtForm().getDocument();
        soluong.setDocumentFilter((new NumericDocumentFilter()));
        txtSoLuong.setPreferredSize(new Dimension(100, 90));
        
        txtDongia = new InputForm("Giá nhập");
        txtDongia.setEditable(false);
        PlainDocument dongia = (PlainDocument) txtDongia.getTxtForm().getDocument();
        dongia.setDocumentFilter((new NumericDocumentFilter()));
        
        txtMaVach = new InputForm("Mã vạch");
//        PlainDocument mavach = (PlainDocument) txtMaVach.getTxtForm().getDocument();
//        mavach.setDocumentFilter((new NumericDocumentFilter()));
        
        content_right_top_cbx.add(txtSoLuong, BorderLayout.WEST);
        content_right_top_cbx.add(txtDongia, BorderLayout.CENTER);
        content_right_top_cbx.add(txtMaVach, BorderLayout.SOUTH);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(content_right_top_cbx, BorderLayout.SOUTH);

        //content_right_bottom = new JPanel(new CardLayout());

//        JPanel card_content_one = new JPanel(new BorderLayout());
//        card_content_one.setBackground(Color.white);
//        card_content_one.setPreferredSize(new Dimension(100, 90));
//        JPanel card_content_one_model = new JPanel(new BorderLayout());
//        card_content_one_model.setPreferredSize(new Dimension(100, 90));
//        txtMaVach = new InputForm("Mã Imei bắt đầu");
        
//        card_content_one_model.add(txtMaVach, BorderLayout.CENTER);
//        card_content_one.add(card_content_one_model, BorderLayout.NORTH);

//        JPanel card_content_two_model = new JPanel(new BorderLayout());
//        card_content_two_model.setBorder(new EmptyBorder(10, 10, 10, 10));
//        labelMaVach = new JLabel("Mã Imei");
//        labelMaVach.setPreferredSize(new Dimension(70, 0));
//        scanImei = new ButtonCustom("Quét imei", "success", 13);
//        scanImei.setPreferredSize(new Dimension(110, 0));
//        importImei = new ButtonCustom("Nhập Excel", "excel", 13);
//        importImei.setPreferredSize(new Dimension(110, 0));
//        JPanel panelScanCenter = new JPanel();
//        panelScanCenter.setBackground(Color.WHITE);
//        JPanel jpanelImei = new JPanel(new BorderLayout());
//        jpanelImei.setPreferredSize(new Dimension(0, 40));
//        jpanelImei.setBackground(Color.WHITE);
//        jpanelImei.setBorder(new EmptyBorder(0, 0, 10, 0));
//        jpanelImei.add(labelMaVach, BorderLayout.WEST);
//        jpanelImei.add(panelScanCenter, BorderLayout.CENTER);
//        JPanel chucnang = new JPanel(new GridLayout(1, 2));
//        chucnang.setOpaque(false);
//        chucnang.add(scanImei);
//        chucnang.add(importImei);
//        jpanelImei.add(chucnang, BorderLayout.EAST);

//        scanImei.addActionListener(this);
//        importImei.addActionListener(this);
//        card_content_two_model.setSize(new Dimension(0, 100));
//        card_content_two_model.setBackground(Color.white);
//        card_content_two_model.add(jpanelImei, BorderLayout.NORTH);
//
//        content_right_bottom.add(card_content_one);
//        content_right_bottom.add(card_content_two_model);

        content_right.add(content_right_top, BorderLayout.NORTH);
        //content_right.add(content_right_bottom, BorderLayout.CENTER);

        content_top.add(content_left);
        content_top.add(content_right);

        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 47));
        content_btn.setLayout(new GridLayout(1, 4, 5, 5));
        content_btn.setBorder(new EmptyBorder(8, 5, 0, 10));
        content_btn.setOpaque(false);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnQuetMa = new ButtonCustom("Quét mã", "excel", 14);
        btnAddSp.addActionListener(this);
        btnEditSP.addActionListener(this);
        btnDelete.addActionListener(this);
        btnQuetMa.addActionListener(this);
        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);
        content_btn.add(btnAddSp);
        content_btn.add(btnQuetMa);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        left_top.add(content_top, BorderLayout.CENTER);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 250));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTablePhieuNhap);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(4, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 360));
        right_top.setOpaque(false);
        txtMaphieu = new InputForm("Mã phiếu nhập");
        txtMaphieu.setText("PN" + maphieunhap);
        txtMaphieu.setEditable(false);
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhanVien.setText(nvDto.getTenNV());
        txtNhanVien.setEditable(false);
        cbxNhaCungCap = new SelectForm("Nhà cung cấp", nccBus.getArrTenNhaCungCap());
        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);
        right_top.add(cbxNhaCungCap);

        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new FlowLayout(1, 20, 0));
        pn_tongtien.setOpaque(false);
        JLabel lbltien = new JLabel("TỔNG TIỀN: ");
        lbltien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltongtien = new JLabel("0đ");
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltien.setForeground(new Color(255, 51, 51));
        pn_tongtien.add(lbltien);
        pn_tongtien.add(lbltongtien);
        right_bottom.add(pn_tongtien);

        btnNhapHang = new ButtonCustom("Nhập hàng", "excel", 14);
        btnNhapHang.addActionListener(this);
        right_bottom.add(btnNhapHang);
        left_top.add(content_btn, BorderLayout.SOUTH);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
    }

    public void loadDataTalbeSanPham(ArrayList<SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getSoLuong()});
        }
    }

    public void loadDataTableChiTietPhieuNhap(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            SanPhamDTO sanpham = spBUS.getByMaSP(ctPhieu.get(i).getMaSP());
            tblModel.addRow(new Object[]{
                i + 1, 
                sanpham.getMaSP(), 
                loaispBUS.getTenLoai(sanpham.getMaLoai()),
                hangsxBUS.getTenHang(sanpham.getMaHang()),
                sanpham.getDungTich() + " " + donviBUS.getTenDonVi(sanpham.getMaDV()),
                Formater.FormatVND(ctPhieu.get(i).getDonGia()),
                ctPhieu.get(i).getSoLuong(),
                Formater.FormatVND(ctPhieu.get(i).getThanhTien())
            });
        }
        lbltongtien.setText(Formater.FormatVND(phieunhapBus.getTongTien(ctPhieu)));
    }

    public void setInfoSanPham(SanPhamDTO sp) {
        this.txtMaSp.setText(Integer.toString(sp.getMaSP()));
        this.txtTenSp.setText(sp.getTenSP());
        this.txtMaVach.setText("");
        listSP = spBUS.getAll();
        int size = listSP.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = loaispBUS.getTenLoai(listSP.get(i).getMaLoai())
                    + hangsxBUS.getTenHang(listSP.get(i).getMaHang())
                    + (listSP.get(i).getDungTich() + " " + donviBUS.getTenDonVi(listSP.get(i).getMaDV()));
        }
        //this.cbxSanPham.setArr(arr);
//        mapb = ch.get(0).getMaphienbansp();
//        setMaVachByPb(mapb);
    }

    public ChiTietPhieuNhapDTO getInfoChiTietPhieu() {
        int masp = Integer.parseInt(txtMaSp.getText());
        int gianhap = Integer.parseInt(txtDongia.getText());
        //ArrayList<SanPhamDTO> sanpham = listSP.get(spBUS.getByMaSP(masp));
        int soluong = Integer.parseInt(txtSoLuong.getText());
        ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieunhap, masp, gianhap, soluong, gianhap*soluong);
        return ctphieu;
    }
    
//    public boolean checkImeiExists(){
//        ArrayList<SanPhamDTO> listSP = getChiTietSanPham();
//        ArrayList<SanPhamDTO> dsCheck = new ArrayList<>();
//        
//        for (SanPhamDTO spDTO : listSP) {
//            for (SanPhamDTO spDTO2 : dsCheck) {
//                if(spDTO.getMaVach().equals(spDTO2.getMaVach())){
//                    JOptionPane.showMessageDialog(this, "Có sự nhầm lẫn nào đó IMEI đã tồn tại trong phiếu");
//                    return false;
//                }
//            }
//        }
////        if(!spBUS.checkMaVachExists(ctSP)){
////                JOptionPane.showMessageDialog(this, "Có IMEI trùng với imei trong kho có sự sai sót nào đó!");
////                return false;
////        }
//        return true;
//    }

//    public ArrayList<SanPhamDTO> getChiTietSanPham() {
//        //int phienbansp = ch.get(cbxCauhinh.cbb.getSelectedIndex()).getMaphienbansp();
//        ArrayList<SanPhamDTO> result = new ArrayList<>();
//        long imeibatdau = Long.parseLong(txtMaVach.getText());
//        int soluong = Integer.parseInt(txtSoLuong.getText());
//        
//        for (long i = imeibatdau; i < imeibatdau + soluong; i++) {
//            result.add(new SanPhamDTO(Long.toString(i), phienbansp, maphieunhap, 0, 1));
//        }                
//        
//        return result;
//    }

    public boolean validateNhap() {
        if (Validation.isEmpty(txtMaSp.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Chọn sản phẩm", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (Validation.isEmail(txtDongia.getText())) {
            JOptionPane.showMessageDialog(this, "Giá nhập không được để rỗng !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        Object source = e.getSource();
//        if (source == cbxCauhinh.cbb) {
//            int index = cbxCauhinh.cbb.getSelectedIndex();
//            this.txtDongia.setText(Integer.toString(ch.get(index).getGianhap()));
//            ChiTietPhieuNhapDTO ctp = checkTonTai();
//            if (ctp == null) {
//                actionbtn("add");
//                this.txtSoLuong.setText("");
//                this.txtMaVach.setText("");
//            } else {
//                actionbtn("update");
//                setImei(ctp);
//            }
//        } 
//        else if(source == btnQuetMa ) {
//            JOptionPane.showMessageDialog(this, "Tính năng chưa phát triển");
//        }
//    }

    public void addCtPhieu() {
        ChiTietPhieuNhapDTO ctphieu = getInfoChiTietPhieu();
        ChiTietPhieuNhapDTO p = phieunhapBus.findCT(listCTPN, ctphieu.getMaPN());
        if (p == null) {
            listCTPN.add(ctphieu);
            loadDataTableChiTietPhieuNhap(listCTPN);
            resetForm();
        } 
        else {
            int input = JOptionPane.showConfirmDialog(this, "Sản phẩm đã tồn tại trong phiếu !\nBạn có muốn chỉnh sửa không ?", "Sản phẩm đã tồn tại !", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                setFormChiTietPhieu(ctphieu);
            }
        }
    }

    public ChiTietPhieuNhapDTO checkTonTai() {
        int mapn = listCTPN.get(Integer.parseInt(txtMaSp.getText())).getMaPN();
        ChiTietPhieuNhapDTO p = phieunhapBus.findCT(listCTPN, mapn);
        return p;
    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnQuetMa.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

//    public void setImei(ChiTietPhieuNhapDTO phieu) {
//        ArrayList<SanPhamDTO> arrSP = findMaPhienBan(phieu.getMaphienbansp());
//        this.cbxPtNhap.setSelectedIndex(phieu.getPhuongthucnnhap());
//        if (phieu.getPhuongthucnnhap() == 0) {
//            this.txtMaVach.setText(ctsp.get(0).getImei());
//            this.txtSoLuong.setText(Integer.toString(ctsp.size()));
//        } else {
//            CardLayout c = (CardLayout) content_right_bottom.getLayout();
//            c.last(content_right_bottom);
//            this.textAreaImei.setText(getStringListImei(ctsp));
//        }
//    }

    public void setFormChiTietPhieu(ChiTietPhieuNhapDTO phieu) {
        SanPhamDTO sanpham = spBUS.getByMaSP(phieu.getMaSP());
        this.txtMaSp.setText(Integer.toString(sanpham.getMaSP()));
        this.txtTenSp.setText(spBUS.getByMaSP(sanpham.getMaSP()).getTenSP());
        this.txtDongia.setText(Double.toString(phieu.getDonGia()));
        this.txtMaVach.setText(spBUS.getByMaSP(sanpham.getMaSP()).getMaVach());
    }

//    public String[] getCauHinhPhienBan(int masp) {
//        ch = phienbanBus.getAll(masp);
//        int size = ch.size();
//        String[] arr = new String[size];
//        for (int i = 0; i < size; i++) {
//            arr[i] = romBus.getKichThuocById(ch.get(i).getRom()) + "GB - "
//                    + ramBus.getKichThuocById(ch.get(i).getRam()) + "GB - " + mausacBus.getTenMau(ch.get(i).getMausac());
//        }
//        return arr;
//    }

    public void resetForm() {
        this.txtMaSp.setText("");
        this.txtTenSp.setText("");
        String[] arr = {"Chọn sản phẩm"};
        this.txtDongia.setText("");
        this.txtSoLuong.setText("");
        this.txtMaVach.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnAddSp && validateNhap()) {
            addCtPhieu();
            
        } 
        else if (source == btnDelete) {
            int index = tablePhieuNhap.getSelectedRow();
            listCTPN.remove(index);
            actionbtn("add");
            loadDataTableChiTietPhieuNhap(listCTPN);
            resetForm();
        } 
        else if (source == btnEditSP) {
            int masp = Integer.parseInt(txtMaSp.getText());
            //ArrayList<SanPhamDTO> arrSP = getChiTietSanPham();
            listCTPN.get(rowPhieuSelect).setSoLuong(listSP.size());
            loadDataTableChiTietPhieuNhap(listCTPN);
        } 
        else if (source == btnNhapHang) {
            eventBtnNhapHang();
        } 
//        else if (source == scanImei) {
//            if (listSP.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để quét mã!");
//            } 
//            else {
//                //QRCode_Dialog qr = new QRCode_Dialog(owner, "Scan", true, textAreaImei);
//            }
//        } 
//        else if (source == importImei) {
//            getImeifromFile();
//            for (String i : listmaimei) {
//                textAreaImei.append(i + "\n");
//            }
//        } 
        else if(source == btnQuetMa) {
            JOptionPane.showMessageDialog(this, "Chức năng không khả dụng !", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void eventBtnNhapHang() {
        if (listCTPN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        } 
        else {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu nhập !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                int mancc = nccBus.getByIndex(cbxNhaCungCap.getSelectedIndex()).getMaNCC();
                long now = System.currentTimeMillis();
                Timestamp currenTime = new Timestamp(now);
                PhieuNhapDTO pn = new PhieuNhapDTO(mancc, maphieunhap, nvDto.getMaNV(), currenTime, phieunhapBus.getTongTien(listCTPN), 1);
                boolean result = phieunhapBus.add(pn, listCTPN);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    PhieuNhap pnlPhieu = new PhieuNhap(m, nvDto);
                    m.setPanel(pnlPhieu);
                } else {
                    JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

//    public void getImeifromFile() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        JFileChooser jf = new JFileChooser();
//        int result = jf.showOpenDialog(null);
//        jf.setDialogTitle("Open file");
//        Workbook workbook = null;
//        if (result == JFileChooser.APPROVE_OPTION) {
//            try {
//                excelFile = jf.getSelectedFile();
//                JOptionPane.showMessageDialog(this, excelFile);
//                excelFIS = new FileInputStream(excelFile);
//                excelBIS = new BufferedInputStream(excelFIS);
//                excelJTableImport = new XSSFWorkbook(excelBIS);
//                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
//                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                    XSSFRow excelRow = excelSheet.getRow(row);
//                    String maimei = excelRow.getCell(0).getStringCellValue();
//                    if (maimei.length() != 15) {
//                        continue;
//                    } else {
//                        this.listmaimei.add(maimei);
//                        System.out.println(maimei);
//                    }
//                }
//            } catch (FileNotFoundException ex) {
//                System.out.println("Lỗi đọc file 1");
//            } catch (IOException ex) {
//                System.out.println("Lỗi đọc file 2");
//            }
//        }
//    }

}
