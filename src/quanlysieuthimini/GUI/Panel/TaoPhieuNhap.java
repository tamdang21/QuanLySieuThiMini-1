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
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
import quanlysieuthimini.BUS.HinhThucThanhToanBUS;
import quanlysieuthimini.BUS.LoaiSanPhamBUS;
import quanlysieuthimini.BUS.PhieuChiBUS;
import quanlysieuthimini.DAO.PhieuChiDAO;
import quanlysieuthimini.DTO.PhieuChiDTO;

public final class TaoPhieuNhap extends JPanel implements ActionListener {

    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_btn;
    JTable tablePhieuNhap, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnQuetMa, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtDongia, txtMaVach, txtSoLuong;
    SelectForm cbxNhaCungCap, cbxHinhThucThanhToan;
    JTextField txtTimKiem;
    JLabel lbltongtien;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nccBus = new NhaCungCapBUS();
    HinhThucThanhToanBUS htttBUS = new HinhThucThanhToanBUS();
    PhieuNhapBUS phieunhapBus = new PhieuNhapBUS();
    HangSanXuatBUS hangsxBUS = new HangSanXuatBUS();
    DonViBUS donviBUS = new DonViBUS();
    LoaiSanPhamBUS loaispBUS = new LoaiSanPhamBUS();
    PhieuChiBUS phieuchiBUS = new PhieuChiBUS();
    NhanVienDTO nvDto;

    ArrayList<SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<ChiTietPhieuNhapDTO> listCTPN;
    int maphieunhap;
    int rowPhieuSelect = -1;

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
                int masp = listSP.get(index).getMaSP();
                System.out.println("Ma sp: " + masp);
                if (index != -1) {
                    resetForm();
                    setInfoSanPham(spBUS.getByMaSP(masp));
//                    ChiTietPhieuNhapDTO ctp = checkTonTai();
//                    if (ctp == null) {
//                        actionbtn("add");
//                    } else {
//                        actionbtn("update");
//                        setFormChiTietPhieu(ctp);
//                    }
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

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 260));
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        
        JPanel content_right_top_cbx = new JPanel(new BorderLayout());
        content_right_top_cbx.setPreferredSize(new Dimension(100, 180));
        
        txtSoLuong = new InputForm("Số lượng");
        PlainDocument soluong = (PlainDocument) txtSoLuong.getTxtForm().getDocument();
        soluong.setDocumentFilter((new NumericDocumentFilter()));
        txtSoLuong.setPreferredSize(new Dimension(100, 90));
        
        txtDongia = new InputForm("Giá nhập");
        txtDongia.setEditable(false);
        
        txtMaVach = new InputForm("Mã vạch");
        
        content_right_top_cbx.add(txtSoLuong, BorderLayout.WEST);
        content_right_top_cbx.add(txtDongia, BorderLayout.CENTER);
        content_right_top_cbx.add(txtMaVach, BorderLayout.SOUTH);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(content_right_top_cbx, BorderLayout.SOUTH);
        
        content_right.add(content_right_top, BorderLayout.NORTH);

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
        cbxHinhThucThanhToan = new SelectForm("Phương thức thanh toán", htttBUS.getArrTenHinhThucThanhToan());
        
        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);
        right_top.add(cbxNhaCungCap);
        right_top.add(cbxHinhThucThanhToan);
        
        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);
        
        JLabel qrThanhToan = new JLabel();
        
        cbxHinhThucThanhToan.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(!cbxHinhThucThanhToan.getValue().equals("Tiền mặt")) {
                    try {
                        if(cbxHinhThucThanhToan.getValue().equals("Momo")) {
                            BufferedImage image = ImageIO.read(this.getClass().getResource("/images/product/qr_momo.jpg"));
                            qrThanhToan.setIcon(new ImageIcon(scale(new ImageIcon(image))));
                            
                        }
                        else if(cbxHinhThucThanhToan.getValue().equals("MB Bank")) {
                            BufferedImage image = ImageIO.read(this.getClass().getResource("/images/product/qr_mbbank.jpg"));
                            qrThanhToan.setIcon(new ImageIcon(scale(new ImageIcon(image))));
                        }
                        
                        right_center.add(qrThanhToan);
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(TaoPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else {
                    right_center.remove(qrThanhToan);
                }
            }
            
        });
        
        
        cbxHinhThucThanhToan.getCbb().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        

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
            tblModelSP.addRow(new Object[]{
                sp.getMaSP(), 
                sp.getTenSP(), 
                sp.getSoLuong()
            });
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
        this.txtMaVach.setText(sp.getMaVach());
        this.txtDongia.setText(Double.toString(sp.getDonGia()));
        this.txtSoLuong.setText("1");
        //listSP = spBUS.getAll();
        
//        int size = listSP.size();
//        String[] arr = new String[size];
//        for (int i = 0; i < size; i++) {
//            arr[i] = loaispBUS.getTenLoai(listSP.get(i).getMaLoai())
//                    + hangsxBUS.getTenHang(listSP.get(i).getMaHang())
//                    + (listSP.get(i).getDungTich() + " " + donviBUS.getTenDonVi(listSP.get(i).getMaDV()));
//        }
    }

    public ChiTietPhieuNhapDTO getInfoChiTietPhieu() {
        int masp = Integer.parseInt(txtMaSp.getText());
        double gianhap = Double.parseDouble(txtDongia.getText());
        int soluong = Integer.parseInt(txtSoLuong.getText());
        ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieunhap, masp, gianhap, soluong, (double) gianhap*soluong);
        return ctphieu;
    }

    public boolean validateNhap() {
        if (Validation.isEmpty(txtMaSp.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Chọn sản phẩm", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        else if (Validation.isEmpty(txtSoLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để rỗng !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void addCtPhieu() {
        if(validateNhap()) {
            ChiTietPhieuNhapDTO ctphieu = getInfoChiTietPhieu();
            
            if(listCTPN.isEmpty()) {
                listCTPN.add(ctphieu);
            }
            else {
                for(ChiTietPhieuNhapDTO ctpn : listCTPN) {
                    if(ctpn.getMaSP() == ctphieu.getMaSP()){
                        ctpn.setSoLuong(ctpn.getSoLuong() + ctphieu.getSoLuong());
                        break;
                    }
                    else {
                        listCTPN.add(ctphieu);
                        break;
                    }
                }
            }
            
            
            loadDataTableChiTietPhieuNhap(listCTPN);
            resetForm();
            
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

    public void setFormChiTietPhieu(ChiTietPhieuNhapDTO phieu) {
        SanPhamDTO sanpham = spBUS.getByMaSP(phieu.getMaSP());
        this.txtMaSp.setText(Integer.toString(sanpham.getMaSP()));
        this.txtTenSp.setText(spBUS.getByMaSP(sanpham.getMaSP()).getTenSP());
        this.txtDongia.setText(Double.toString(phieu.getDonGia()));
        this.txtMaVach.setText(spBUS.getByMaSP(sanpham.getMaSP()).getMaVach());
    }

    public void resetForm() {
        this.txtMaSp.setText("");
        this.txtTenSp.setText("");
        this.txtDongia.setText("");
        this.txtSoLuong.setText("");
        this.txtMaVach.setText("");
    }
    
    public Image scale(ImageIcon x) {
        int WIDTH = 250;
        int HEIGHT = 280;
        Image scaledImage = x.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        return scaledImage;
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
            listCTPN.get(rowPhieuSelect).setSoLuong(listSP.size());
            loadDataTableChiTietPhieuNhap(listCTPN);
        } 
        else if (source == btnNhapHang) {
            eventBtnNhapHang();
        }
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
                PhieuNhapDTO pn = new PhieuNhapDTO(maphieunhap, nvDto.getMaNV() ,mancc, currenTime, phieunhapBus.getTongTien(listCTPN), 1);
                boolean result = phieunhapBus.add(pn, listCTPN);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
                    PhieuNhap pnlPhieu = new PhieuNhap(m, nvDto);
                    m.setPanel(pnlPhieu);
                } else {
                    JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if(htttBUS.getByIndex(cbxHinhThucThanhToan.getSelectedIndex()).getTenHTTT().equals("Tiền mặt")) {
                int input2 = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo Phiếu Chi", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    int mapc = PhieuChiDAO.getInstance().getAutoIncrement();
                    String lidochi = "";
                    String ghichu = "";
                    long now = System.currentTimeMillis();
                    Timestamp currenTime = new Timestamp(now);
                    
                    PhieuChiDTO pc = new PhieuChiDTO(mapc, maphieunhap, nvDto.getMaNV(), nvDto.getTenNV(), lidochi, ghichu, currenTime, phieunhapBus.getTongTien(listCTPN));
                    boolean result = phieuchiBUS.add(pc);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Tạo phiếu chi thành công !");
                        PhieuNhap pnlPhieu = new PhieuNhap(m, nvDto);
                        m.setPanel(pnlPhieu);
                    } else {
                        JOptionPane.showMessageDialog(this, "Tạo phiếu chi không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                    }
            }
            }
        }
    }

}
