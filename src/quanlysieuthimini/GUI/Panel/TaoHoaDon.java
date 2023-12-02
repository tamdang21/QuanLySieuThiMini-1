package quanlysieuthimini.GUI.Panel;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import quanlysieuthimini.BUS.KhachHangThanThietBUS;
import quanlysieuthimini.BUS.HoaDonBUS;
import quanlysieuthimini.BUS.SanPhamBUS;
import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.DAO.HoaDonDAO;
import quanlysieuthimini.DTO.ChiTietHoaDonDTO;
import quanlysieuthimini.DTO.KhachHangThanThietDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.DTO.TaiKhoanDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.CustomComboCheck;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Component.Notification;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Component.SelectForm;
import quanlysieuthimini.GUI.Dialog.ListKhachHang;
import quanlysieuthimini.GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kitfox.svg.A;
import quanlysieuthimini.helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import quanlysieuthimini.BUS.ChiTietThanhToanBUS;
import quanlysieuthimini.BUS.DonViBUS;
import quanlysieuthimini.BUS.HangSanXuatBUS;
import quanlysieuthimini.BUS.HinhThucThanhToanBUS;
import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.BUS.LoaiSanPhamBUS;
import quanlysieuthimini.DTO.ChiTietThanhToanDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.GUI.Dialog.ChonMaVach;
import quanlysieuthimini.GUI.Dialog.ListKhuyenMai;
import quanlysieuthimini.helper.writePDF;

public final class TaoHoaDon extends JPanel {

    SanPhamBUS sanphamBUS = new SanPhamBUS();
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_btn;
    JTable tableHoaDon, tableSanPham;
    JScrollPane scrollTableHoaDon, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnIExcel, btnThanhToan, btnQuetMa;
    InputForm txtMaHD, txtNhanVien, txtMaSp, txtTenSp, txtSoLuongBan;
    SelectForm cbxPTTT;
    JTextField txtTimKiem;
    Color BackgroundColor = new Color(240, 247, 250);

    int sum;
    int maHD;
    int manv;
    int makh = 1, makm = 1;
    int tienGiam = 0;
    String type;

    SanPhamBUS spBUS = new SanPhamBUS();
    HoaDonBUS hoadonBUS = new HoaDonBUS();
    KhachHangThanThietBUS khachHangBUS = new KhachHangThanThietBUS();
    HangSanXuatBUS hangsxBUS = new HangSanXuatBUS();
    DonViBUS donviBUS = new DonViBUS();
    LoaiSanPhamBUS loaispBUS = new LoaiSanPhamBUS();
    HinhThucThanhToanBUS htttBUS = new HinhThucThanhToanBUS();
    KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
    ChiTietThanhToanBUS ctttBUS = new ChiTietThanhToanBUS();
    
    
    //public JTextArea textAreaMaVach;
    private JLabel labelMaVach;
    private JPanel content_right_bottom_top;
    private JPanel content_right_bottom_bottom;

    ArrayList<ChiTietHoaDonDTO> arrListCTHD = new ArrayList<>();
    ArrayList<SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<Float> listDKKM = kmBUS.getAllDKKM();
    ArrayList<KhuyenMaiDTO> listKM = kmBUS.getAll();
    ArrayList<ChiTietThanhToanDTO> arrListCTTT = new ArrayList<>();
    
    TaiKhoanDTO tk;
    private int masp;
    private JLabel lbltongtien;
    private JTextField txtKH, txtKM;
    public JTextField txtMaVach;
    private Main mainChinh;
    private ButtonCustom btnQuayLai;
    private InputForm txtGiaBan;
    private ButtonCustom btnKh;

    public TaoHoaDon(Main mainChinh, TaiKhoanDTO tk, String type) {
        this.mainChinh = mainChinh;
        this.tk = tk;
        this.type = type;
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

        // Tạo hóa đơn
        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Loại", "Tịnh lượng", "Đơn giá", "Số lượng", "Thành tiền"};
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableHoaDon.getColumnModel();
        for (int i = 0; i < 8; i++) {
            if (i != 2 || i != 0 || i != 1) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableHoaDon.setFocusable(false);
        tableHoaDon.setDefaultEditor(Object.class, null);
        scrollTableHoaDon.setViewportView(tableHoaDon);
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
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);

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

        JPanel content_top, content_left, content_right, content_right_top, content_right_bottom;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./images/icon/search.svg"));

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
        content_right.setBackground(Color.WHITE);

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 165));
        txtMaSp = new InputForm("Mã SP");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        String[] arrCauhinh = {"Chọn sản phẩm"};
        JPanel panlePXGX = new JPanel(new GridLayout(1, 3));
        panlePXGX.setPreferredSize(new Dimension(100, 90));
        //cbxSanPham = new SelectForm("Tịnh lượng", arrCauhinh);
        txtGiaBan = new InputForm("Giá bán");
        txtGiaBan.setEditable(false);
        txtSoLuongBan = new InputForm("Số lượng bán");
        
        panlePXGX.add(txtSoLuongBan);
        panlePXGX.add(txtGiaBan);
        
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(panlePXGX, BorderLayout.SOUTH);
//        cbxSanPham.getCbb().addItemListener((ItemEvent e) -> {
//            masp = listSP.get(cbxSanPham.getSelectedIndex()).getMaSP();
//            //setMaVachByPb(masp);
//            if (checkTonTai()) {
//                actionbtn("update");
//            } else {
//                actionbtn("add");
//            }
//        });

        content_right_bottom = new JPanel(new BorderLayout());
        content_right_bottom.setBorder(new EmptyBorder(0, 10, 10, 10));
        content_right_bottom.setBackground(Color.WHITE);
        
        labelMaVach = new JLabel("Mã vạch");
        labelMaVach.setPreferredSize(new Dimension(70, 0));
        
        ButtonCustom scanMaVach = new ButtonCustom("Quét mã", "success", 14);
        scanMaVach.setPreferredSize(new Dimension(110, 0));
        
        JPanel panelScanCenter = new JPanel();
        panelScanCenter.setBackground(Color.WHITE);
        
        JPanel jpanelMaVach = new JPanel(new BorderLayout());
        jpanelMaVach.setPreferredSize(new Dimension(0, 40));
        jpanelMaVach.setBackground(Color.WHITE);
        jpanelMaVach.setBorder(new EmptyBorder(0, 0, 10, 0));
        jpanelMaVach.add(labelMaVach, BorderLayout.WEST);
        
        JPanel jPanelChonMaVach = new JPanel(new GridLayout(1, 2));
        jPanelChonMaVach.setPreferredSize(new Dimension(200, 0));
        jPanelChonMaVach.setOpaque(false);
        jPanelChonMaVach.add(scanMaVach);

        jpanelMaVach.add(panelScanCenter, BorderLayout.CENTER);
        jpanelMaVach.add(jPanelChonMaVach, BorderLayout.EAST);

        txtMaVach = new JTextField();
        txtMaVach.setEditable(true);
        txtMaVach.setSize(new Dimension(0, 0));
        txtMaVach.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        //this.txtMaVach.setEditable(false);
        
        scanMaVach.addActionListener((ActionEvent e) -> {
//            new QRCode_Dialog(owner, "Scan", true, txtMaVach);
              //txtMaVach.setEditable(true);
              txtMaVach.requestFocus();
        });
        
        content_right_bottom_top = new JPanel(new BorderLayout());
        content_right_bottom_top.setSize(new Dimension(0, 40));
        content_right_bottom_top.setBackground(Color.white);
        content_right_bottom_top.add(jpanelMaVach, BorderLayout.NORTH);
        content_right_bottom_top.add(txtMaVach, BorderLayout.CENTER);
        content_right_bottom_bottom = new JPanel(new BorderLayout());
        content_right_bottom_bottom.setSize(new Dimension(0, 100));
        content_right_bottom_bottom.setBorder(new EmptyBorder(20, 0, 0, 0));

        content_right_bottom.add(content_right_bottom_top, BorderLayout.CENTER);

        content_right.add(content_right_top, BorderLayout.NORTH);
        content_right.add(content_right_bottom, BorderLayout.CENTER);

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
        btnIExcel = new ButtonCustom("Nhập Excel", "excel", 14);
        content_btn.add(btnAddSp);
        content_btn.add(btnIExcel);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        btnAddSp.addActionListener((e) -> {
            addHandle();
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tableHoaDon.getSelectedRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
                } else {
                    ChiTietHoaDonDTO arrCTHDDel = arrListCTHD.get(index);
                    int masp = arrCTHDDel.getMaSP();
                    ArrayList<ChiTietHoaDonDTO> listCthd = new ArrayList<>();
                    for (ChiTietHoaDonDTO cthd : arrListCTHD) {
                        if (cthd.getMaSP()!= masp) {
                            listCthd.add(cthd);
                        }
                    }
                    arrListCTHD.remove(index);
                    arrListCTHD = listCthd;
                    loadDataTableChiTietHoaDon(arrListCTHD);
                }
                actionbtn("add");
                resetForm();
            }
        });
        
        btnEditSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tableHoaDon.getSelectedRow();
            if (index < 0)
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa");
            else{
                 ChiTietHoaDonDTO arrCTHDDel = arrListCTHD.get(index);
                 SanPhamDTO spTon = listSP.get(index);
                 int masp = arrCTHDDel.getMaSP();
                 int soluongton = spTon.getSoLuong();
                 ArrayList<ChiTietHoaDonDTO> listCthd = new ArrayList<>();
                 for (ChiTietHoaDonDTO cthd : arrListCTHD) {
                     if (cthd.getMaSP() == masp) {
                        if (Integer.valueOf(txtSoLuongBan.getText()) <= soluongton)
                            cthd.setSoLuong(Integer.valueOf(txtSoLuongBan.getText()));
                        else
                            JOptionPane.showMessageDialog(null, "Số lượng tồn không đủ. Vui lòng nhập lại");
                     }
                     listCthd.add(cthd);
                 }
                 arrListCTHD = listCthd;
                 loadDataTableChiTietHoaDon(arrListCTHD);
                 actionbtn("add");
            }
            }
            
        });

        btnIExcel.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Chức năng không khả dụng !", "Thông báo", JOptionPane.WARNING_MESSAGE);
        });

        left_top.add(content_top, BorderLayout.CENTER);
        left_top.add(content_btn, BorderLayout.SOUTH);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 280));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTableHoaDon);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        
        right_top = new JPanel(new GridLayout(5, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 360));
        
        txtMaHD = new InputForm("Mã hóa đơn");
        txtMaHD.setEditable(false);
        txtNhanVien = new InputForm("Nhân viên bán hàng");
        txtNhanVien.setEditable(false);
        maHD = HoaDonDAO.getInstance().getAutoIncrement();
        manv = tk.getMaNV();
        txtMaHD.setText("HD-" + HoaDonDAO.getInstance().getAutoIncrement());
        NhanVienDTO nhanvien = NhanVienDAO.getInstance().getById(tk.getMaNV());
        txtNhanVien.setText(nhanvien.getTenNV());
        
        // Khách hàng
        JLabel khachHangJLabel = new JLabel("Khách hàng");
        khachHangJLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        
        txtKH = new JTextField("");
        txtKH.setEditable(false);
        
        JPanel khachJPanel = new JPanel(new BorderLayout());
        khachJPanel.setPreferredSize(new Dimension(0, 40));
        khachJPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        khachJPanel.setOpaque(false);
        
        JPanel kJPanelLeft = new JPanel(new GridLayout(1, 1));
        kJPanelLeft.setOpaque(false);
        kJPanelLeft.setPreferredSize(new Dimension(40, 0));
        
        btnKh = new ButtonCustom("Chọn khách hàng", "success", 14);
        btnKh.addActionListener((ActionEvent e) -> {
            new ListKhachHang(TaoHoaDon.this, owner, "Chọn khách hàng", true);
        });
        
        kJPanelLeft.add(btnKh);
        
        JPanel khPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        khPanel.setBackground(Color.WHITE);
        khPanel.setPreferredSize(new Dimension(0, 60));
        
        khachJPanel.add(kJPanelLeft, BorderLayout.EAST);
        khachJPanel.add(txtKH, BorderLayout.CENTER);
        
        khPanel.add(khachHangJLabel);
        khPanel.add(khachJPanel);
        
        // Khuyến mãi
        JPanel khPanel3 = new JPanel(new GridLayout(2, 1, 5, 0));
        khPanel3.setBackground(Color.WHITE);
        khPanel3.setPreferredSize(new Dimension(0, 60));
        
        JLabel KhuyenMaiJLabel = new JLabel("Khuyến mãi");
        KhuyenMaiJLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        
        JPanel KhuyenMaiJPanel = new JPanel(new BorderLayout());
        KhuyenMaiJPanel.setPreferredSize(new Dimension(0, 40));
        KhuyenMaiJPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        KhuyenMaiJPanel.setOpaque(false);
        
        JPanel kJPanelLeft2 = new JPanel(new GridLayout(1, 1));
        kJPanelLeft2.setOpaque(false);
        kJPanelLeft2.setPreferredSize(new Dimension(40, 0));
        
        txtKM = new JTextField("");
        txtKM.setEditable(false);
        
        KhuyenMaiJPanel.add(kJPanelLeft2, BorderLayout.NORTH);
        KhuyenMaiJPanel.add(txtKM, BorderLayout.CENTER);
        
        khPanel3.add(KhuyenMaiJLabel);
        khPanel3.add(KhuyenMaiJPanel);
        
        // Phương thức thanh toán
        JPanel khPanel1 = new JPanel(new BorderLayout());
        khPanel1.setBackground(Color.WHITE);
        khPanel1.setPreferredSize(new Dimension(0, 60));
        
        String[] listNameHTTT = htttBUS.getArrTenHinhThucThanhToan();
        cbxPTTT = new SelectForm("Phương thức thanh toán", listNameHTTT);
        cbxPTTT.setSelectedIndex(0);
        cbxPTTT.setEnabled(false);
        
        khPanel1.add(cbxPTTT);

        right_top.add(txtMaHD);
        right_top.add(txtNhanVien);
        right_top.add(khPanel);
        right_top.add(khPanel3);
        right_top.add(khPanel1);
        
        //Tạo Qr thanh toán
        JLabel qrCodeLabel = new JLabel();
        
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

        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    SanPhamDTO sp = spBUS.getById(listSP.get(index).getMaSP());
                    setInfoSanPham(sp);
                    txtMaVach.setText(sp.getMaVach());
                }
                actionbtn("add");
            }
        });

        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tableHoaDon.getSelectedRow();
                if (index != -1) {
                    actionbtn("update");
                    setPhieuSelected();
                }
            }
        });
        btnThanhToan = new ButtonCustom("Thanh toán", "excel", 14);
        btnQuayLai = new ButtonCustom("Quay lại", "excel", 14);
        right_bottom.add(pn_tongtien);
        if (type.equals("create")) {
            right_bottom.add(btnThanhToan);
        } else if (type.equals("detail")) {
            right_bottom.add(btnQuayLai);
        }

        btnThanhToan.addActionListener((ActionEvent e) -> {
            if (arrListCTHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            } else {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xác nhận thanh toán !", "Xác nhận tạo hóa đơn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    long now = System.currentTimeMillis();
                    int maHTTT = htttBUS.getByIndex(cbxPTTT.getSelectedIndex()).getMaHTTT();
                    Timestamp currenTime = new Timestamp(now);
                    
                    HoaDonDTO HoaDon;
//                    if(makh == -1) {
//                        HoaDon = new HoaDonDTO(maHD, makm, tk.getMaNV(), currenTime, sum, 1);
//                        hoadonBUS.insertNotKH(HoaDon, arrListCTHD);
//                    }
//                    else {
                        HoaDon = new HoaDonDTO(maHD, makh, makm, tk.getMaNV(), currenTime, sum, 1);
                        hoadonBUS.insert(HoaDon, arrListCTHD);
                        khachHangBUS.upDiemTichLuy(makh, 1);
//                    }
                    
                    
                    ChiTietThanhToanDTO cttt = new ChiTietThanhToanDTO(maHD,maHTTT,sum,currenTime);
                    arrListCTTT.add(cttt);
                    ctttBUS.add(arrListCTTT);
                    ArrayList<HashMap> listCTHD = new ArrayList<>();
                    for (ChiTietHoaDonDTO cthd : arrListCTHD){
                        HashMap<String,Integer> map = new HashMap<>();
                        map.put("maSP",cthd.getMaSP());
                        map.put("soLuong",cthd.getSoLuong());
                        listCTHD.add(map);
                    }
                    for (HashMap<Integer,Integer> map : listCTHD){
                        int maSP = map.get("maSP");
                        int soLuong = spBUS.getById(maSP).getSoLuong() - map.get("soLuong");
                        spBUS.updateQuantity(maSP, soLuong);
                    }
                    
                    int selectPrint = JOptionPane.showConfirmDialog(null, "Bạn có muốn in bóa đơn không !", "Xác nhận in hóa đơn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (selectPrint == 0){
                        writePDF pdf = new writePDF();
                        pdf.writeHoaDon(maHD, tienGiam);
                    }
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công !");
                    mainChinh.setPanel(new HoaDon(mainChinh, tk));
                }
            }
        });

        btnQuayLai.addActionListener((ActionEvent e) -> {
            HoaDon HoaDonPanel = new HoaDon(mainChinh, tk);
            mainChinh.setPanel(HoaDonPanel);
        });

        txtMaVach.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SanPhamDTO sp = spBUS.getByMaVach(txtMaVach.getText());
                    setInfoSanPham(sp);
                    addHandle();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        if(txtMaVach.getText().length() >= 13) {
            SanPhamDTO sp = spBUS.getByMaVach(txtMaVach.getText());
            setInfoSanPham(sp);
            addHandle();
        }
        
        cbxPTTT.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == cbxPTTT.getCbb()) {
                    if(!cbxPTTT.getValue().equals("Tiền mặt")) {
                        try {
                            if(cbxPTTT.getValue().equals("Momo")) {
//                                BufferedImage image = ImageIO.read(this.getClass().getResource("/images/product/qr_momo.jpg"));
//                                qrCodeLabel.setIcon(new ImageIcon(scale(new ImageIcon(image))));
                                
                                String qrCodeText = String.format("2|99|%s|%s|%s|0|0|%s", 
                                                                    "0858212963", 
                                                                    "Koong Chấn Phong", 
                                                                    "koongchanphong0712@gmail.com", 
                                                                    String.valueOf(sum));
                                BufferedImage qrImg = generateQRCodeImage(qrCodeText, 300, 280);
                                qrCodeLabel.setIcon(new ImageIcon(scale(new ImageIcon(qrImg))));
                            }
                            else if(cbxPTTT.getValue().equals("MB Bank")) {
                                BufferedImage image = ImageIO.read(this.getClass().getResource("/images/product/qr_mbbank.jpg"));
                                qrCodeLabel.setIcon(new ImageIcon(scale(new ImageIcon(image))));
                            }
                            right_center.add(qrCodeLabel);
                        } 
                        catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Lỗi không thể tạo mã QR thanh toán");
                        }
                    }
                else {
                    right_center.remove(qrCodeLabel);
                }
                revalidate();
                repaint();
            }
        }
        });
        
        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
        actionbtn("add");
    }

    public void loadDataTalbeSanPham(ArrayList<SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getSoLuong()});
        }
    }
    
    public void resetForm() {
        this.txtMaSp.setText("");
        this.txtTenSp.setText("");
        this.txtGiaBan.setText("");
        this.txtSoLuongBan.setText("");
        this.txtMaVach.setText("");
    }

    public void setInfoSanPham(SanPhamDTO sp) {
        if(sp != null) {
            this.txtMaSp.setText(Integer.toString(sp.getMaSP()));
            this.txtTenSp.setText(sp.getTenSP());
            this.txtGiaBan.setText(String.valueOf(sp.getDonGia()));
            this.txtSoLuongBan.setText("1");
        }
    }

    public boolean checkInfo() {
        boolean check = true;
        
        if(spBUS.getByMaVach(txtMaVach.getText()) == null && txtMaVach.getText().length() == 13 && txtMaSp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mã vạch này không tồn tại !!!");
            check = false;
        }
        
        else if (txtMaSp.getText().equals("") || txtMaVach.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm hoặc quét mã vạch để kiểm tra");
            check = false;
        }
        

        return check;
    }

    public ChiTietHoaDonDTO getInfo() {
        int masp = Integer.parseInt(txtMaSp.getText());
        double dongia = Double.valueOf(txtGiaBan.getText());
        int soLuong = Integer.parseInt(txtSoLuongBan.getText());
        ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(maHD, masp, dongia,soLuong,  soLuong * dongia);
        if (!checkRowExist(tableHoaDon)){
            arrListCTHD.add(cthd);
            System.out.println("Da them CTHD vao Arrays");
        }
        return null;
    }

//    public int getChiTietSp() {
//        String strMaVach = txtMaVach.getText();
//        ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(arrMaVach[i], mapb, 0, maHD, 0);
//        listSP.add(ct);
//        return arrMaVach.length;
//    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnIExcel.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

    public boolean checkTonTai() {
        boolean check = false;
        int maSP = Integer.parseInt(txtMaSp.getText());
        for (ChiTietHoaDonDTO chiTietPhieu : arrListCTHD) {
            if (chiTietPhieu.getMaSP() == maSP) {
                return true;
            }
        }
        return check;
    }
    

//    public void setMaVachByPb(int mapb) {
//        ctpb = ChiTietSanPhamDAO.getInstance().selectAllbyPb(mapb);
//        PhienBanSanPhamDTO pbsp = sanphamBUS.getByMaPhienBan(mapb);
//        txtGiaBan.setText(pbsp.getGiaxuat() + "");
//        txtSoLuongBan.setText(pbsp.getSoLuong()+"");
//        textAreaMaVach.setText("");
//        for (int i = 0; i < ctpb.size(); i++) {
//            for (ChiTietSanPhamDTO cthd : arrListSanPham) {
//                if (cthd.getMaVach().equals(ctpb.get(i).getMaVach())) {
//                    textAreaMaVach.append(cthd.getMaVach() + "\n");
//                }
//            }
//        }
//    }

    public void loadDataTableChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> arrCTHD) {
        tblModel.setRowCount(0);
        int size = arrCTHD.size();
        sum = 0;
        tienGiam = 0;
        for (int i = 0; i < size; i++) {
            SanPhamDTO sanpham = sanphamBUS.getById(arrCTHD.get(i).getMaSP());
            sum += arrCTHD.get(i).getThanhTien();
            tblModel.addRow(new Object[]{
                i + 1, 
                sanpham.getMaSP(),
                sanpham.getTenSP(),
                loaispBUS.getTenLoai(sanpham.getMaLoai()),
                sanpham.getDungTich() + " " + donviBUS.getTenDonVi(sanpham.getMaDV()),
                Formater.FormatVND(arrCTHD.get(i).getDonGia()),
                arrCTHD.get(i).getSoLuong(),
                Formater.FormatVND(arrCTHD.get(i).getThanhTien())
            });
        }

        LocalDate now = LocalDate.now();
        int maKM = kmBUS.KiemTraKhuyenMai(listKM, sum, now);
        
        if (maKM != 0)
            setKhuyenMai(maKM);
        else{
            txtKM.setText("Không có khuyễn mãi");
            lbltongtien.setText(Formater.FormatVND(sum));
        }
        
        if(makh != -1)
            setKhachHang(makh);
        
        if (tableSanPham.getSelectedRow() != -1)
            tableSanPham.clearSelection();
    }
    
    public void setKhachHang(int index) {
        makh = index;
        KhachHangThanThietDTO khachhang = khachHangBUS.selectKh(makh);
        float phantramgiamKH = (float) khachhang.getChietKhauTheoDiem();
        sum -= (phantramgiamKH * sum);
        tienGiam += sum*phantramgiamKH;
        lbltongtien.setText(Formater.FormatVND(sum));
        txtKH.setText(khachhang.getTenKH());
        txtKH.setEditable(false);
        btnKh.setEnabled(false);
    }
    
    public void setKhuyenMai(int index) {
        makm = index;
        KhuyenMaiDTO khuyenmai = kmBUS.getById(makm);
        txtKM.setText(khuyenmai.getTenKM());
        float phantramgiamKM = (float) khuyenmai.getPhanTramKM();
        sum -= (phantramgiamKM * sum);
        tienGiam += sum*phantramgiamKM;
        lbltongtien.setText(Formater.FormatVND(sum));
    }

    public void setPhieuSelected() {
        ChiTietHoaDonDTO cthd = arrListCTHD.get(tableHoaDon.getSelectedRow());
        SanPhamDTO spSel = spBUS.getById(cthd.getMaSP());
        this.txtMaSp.setText(Integer.toString(spSel.getMaSP()));
        this.txtTenSp.setText(spSel.getTenSP());
        this.txtGiaBan.setText(String.valueOf(spSel.getDonGia()));
        this.txtSoLuongBan.setText(String.valueOf(arrListCTHD.get(tableHoaDon.getSelectedRow()).getSoLuong()));
        //cbxSanPham.setSelectedItem(cthd.getMaSP()+ "");
    }
    
    private boolean checkRowExist (JTable table){
        int rowCount = table.getRowCount();
        for (int i=0;i<rowCount;i++){
            if (table.getValueAt(i, 1) == Integer.valueOf(txtMaSp.getText())){
                return true;
            }
        }
        return false;
    }
    
    public BufferedImage generateQRCodeImage(String text, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgbColor = bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
                    bufferedImage.setRGB(x, y, rgbColor);
                }
            }
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addHandle() {
        if (checkInfo()) {
                if (spBUS.getById(Integer.valueOf(txtMaSp.getText())).getSoLuong()
                        < Integer.valueOf(txtSoLuongBan.getText())){
                    JOptionPane.showMessageDialog(null, "Xin lỗi sản phẩm tồn kho không đủ!!!");
                    this.txtMaSp.setText("");
                    this.txtTenSp.setText("");
                    this.txtGiaBan.setText("");
                    this.txtSoLuongBan.setText("");
                    txtMaVach.setText("");
                    return;
                }
                getInfo();
                if(checkRowExist(tableHoaDon)){
                    int maSP = Integer.valueOf(txtMaSp.getText());
                    double dongia = Double.valueOf(txtGiaBan.getText());
                    int soLuongTxt = Integer.valueOf(txtSoLuongBan.getText());
                    ArrayList<ChiTietHoaDonDTO> listCTHD = new ArrayList<>();
                    for (ChiTietHoaDonDTO cthd : arrListCTHD){
                        if (cthd.getMaSP() == maSP){
                            int soLuongMoi = soLuongTxt + cthd.getSoLuong();
                            cthd.setSoLuong(soLuongMoi);
                            cthd.setThanhTien(soLuongMoi * dongia);   
                        }
                        listCTHD.add(cthd);
                    }
                    arrListCTHD = listCTHD;
                    loadDataTableChiTietHoaDon(arrListCTHD);
                }else{
                    loadDataTableChiTietHoaDon(arrListCTHD);
                }
                this.txtMaSp.setText("");
                this.txtTenSp.setText("");
                this.txtGiaBan.setText("");
                this.txtSoLuongBan.setText("");
                txtMaVach.setText("");
            }
    }

    public BufferedImage addLogoToQRCode(BufferedImage qrCodeImage, BufferedImage logo) {
        int qrCodeWidth = qrCodeImage.getWidth();
        int qrCodeHeight = qrCodeImage.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        BufferedImage qrCodeWithLogo = new BufferedImage(qrCodeWidth, qrCodeHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = qrCodeWithLogo.createGraphics();
        g.drawImage(qrCodeImage, 0, 0, null);
        int x = (qrCodeWidth - logoWidth) / 2;
        int y = (qrCodeHeight - logoHeight) / 2;
        g.drawImage(logo, x, y, null);
        g.dispose();

        return qrCodeWithLogo;
    }

    public Image resizeImage(Image image, int newHeight, int newWidth) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return newImage;
    }
    
    public BufferedImage convertToBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        BufferedImage bufferedImage = null;
        
        // Tạo một BufferedImage với các thuộc tính phù hợp
        if (image.getWidth(null) > 0 && image.getHeight(null) > 0) {
            bufferedImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB
        );
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
        } else {
            System.out.println("Lỗi: Kích thước hình ảnh không hợp lệ hoặc chưa tải.");
        }

        return bufferedImage;
    }
    
    public Image scale(ImageIcon x) {
        int WIDTH = 300;
        int HEIGHT = 280;
        Image scaledImage = x.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        return scaledImage;
    }
    
}
