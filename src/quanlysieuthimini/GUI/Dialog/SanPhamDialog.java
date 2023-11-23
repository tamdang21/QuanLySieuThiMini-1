package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.HangSanXuatBUS;
import quanlysieuthimini.BUS.DonViBUS;
import quanlysieuthimini.BUS.LoaiSanPhamBUS;
import quanlysieuthimini.DAO.SanPhamDAO;
import quanlysieuthimini.DAO.HangSanXuatDAO;
import quanlysieuthimini.DAO.LoaiSanPhamDAO;
import quanlysieuthimini.DAO.DonViDAO;
import quanlysieuthimini.DTO.LoaiSanPhamDTO;
import quanlysieuthimini.DTO.DonViDTO;
import quanlysieuthimini.DTO.HangSanXuatDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Component.InputImage;
import quanlysieuthimini.GUI.Component.NumericDocumentFilter;
import quanlysieuthimini.GUI.Component.SelectForm;
import quanlysieuthimini.GUI.Component.InputDate;
import quanlysieuthimini.GUI.Panel.SanPham;
import quanlysieuthimini.helper.Formater;
import quanlysieuthimini.helper.Validation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
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
import javax.swing.text.PlainDocument;

public final class SanPhamDialog extends JDialog implements ActionListener {

   SanPhamDTO sp;
   private HeaderTitle titlePage;
   private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain;
   private ButtonCustom btnThemCHMS, btnHuyBo, btnAddSanPham, btnViewCauHinh;
   InputForm tenSP, dongia, soluong, dungtich, mavach;
   InputDate ngaySX, hanSD;
   SelectForm hangsx, loaisp, donvi;
   InputImage hinhanh;
   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
   quanlysieuthimini.GUI.Panel.SanPham jpSP;
   LoaiSanPhamDAO loaiSPDAO;
   DonViDAO       donviDAO;

   String[] arrHSX;
   String[] arrLoai;
   String[] arrDonVi;
   
   HangSanXuatBUS hangsxBUS = new HangSanXuatBUS();
   DonViBUS donviBUS = new DonViBUS();
   LoaiSanPhamBUS loaispBUS = new LoaiSanPhamBUS();
   int masp;
   int mach;
   private ButtonCustom btnEditCT;
   private ButtonCustom btnEditSP;

   public void init(SanPham jpSP) {
       this.jpSP = jpSP;
       masp = jpSP.spBUS.spDAO.getAutoIncrement();
//       mach = PhienBanSanPhamDAO.getInstance().getAutoIncrement();
       arrHSX = hangsxBUS.getArrTenHang();
       arrDonVi = donviBUS.getArrTenDonVi();
       arrLoai = loaispBUS.getArrTenLoai();
       
   }

   public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
       super(owner, title, modal);
       init(jpSP);
       initComponents(title, type);

   }

   public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
       super(owner, title, modal);
       init(jpSP);
       this.sp = sp;
       //get phiên bản sp
//     this.listch = jpSP.spBUS.cauhinhBus.getAll(sp.getMasp());
       initComponents(title, type);
   }

   public void initCardOne(String type) {
       pnCenter = new JPanel(new BorderLayout());
       pninfosanpham = new JPanel(new GridLayout(4, 3, 0, 0));
       pninfosanpham.setBackground(Color.WHITE);
       pnCenter.add(pninfosanpham, BorderLayout.CENTER);

       pninfosanphamright = new JPanel();
       pninfosanphamright.setBackground(Color.WHITE);
       pninfosanphamright.setPreferredSize(new Dimension(300, 600));
       pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
       pnCenter.add(pninfosanphamright, BorderLayout.WEST);

       tenSP =  new InputForm("Tên sản phẩm");
       hangsx = new SelectForm("Hãng sản xuất",arrHSX);
       loaisp = new SelectForm("Loại",arrLoai);
       donvi =  new SelectForm("Đơn vị",arrDonVi);
       dongia = new InputForm("Đơn giá");
       soluong = new InputForm("Số lượng");
       mavach = new InputForm("Mã vạch");
       dungtich = new InputForm("Dung Tích");
       ngaySX = new InputDate("Ngày Sản Xuất");
       hanSD =  new InputDate("Hạn sử dụng");
       hinhanh = new InputImage("Hình minh họa");
       pninfosanpham.add(tenSP);
       pninfosanpham.add(hangsx);
       pninfosanpham.add(loaisp);
       pninfosanpham.add(donvi);
       pninfosanpham.add(dongia);
       pninfosanpham.add(soluong);
       pninfosanpham.add(dungtich);
       pninfosanpham.add(ngaySX);
       pninfosanpham.add(hanSD);
       pninfosanphamright.add(hinhanh);
       pninfosanpham.add(mavach);
       
       pnbottom = new JPanel (new  FlowLayout());
       pnbottom.setBorder (new EmptyBorder(20,0,10,0));
       pnbottom.setBackground(Color.white);
       switch (type){
            case "update" -> {
                btnEditSP = new ButtonCustom("Lưu thông tin", "success", 14);
                btnEditSP.addActionListener(this);
                pnbottom.add(btnEditSP);
            }
            case "create" -> {
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                pnbottom.add(btnAddSanPham);
            }
        }
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
        
    }
   
   public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCardOne(type);

        pnmain.add(pnCenter);
        
        try{
            switch (type) {
                case "view" -> setInfo(sp);
                case "update" -> setInfo(sp);
                default -> {
                }
            }
        }catch(Exception ex){
        
        }
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/images/product/";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source == btnThemCHMS ) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        }
        else if (source == btnAddSanPham) {
            try{
                if(validateInput())
                    if(validateData()){
                        eventAddSanPham();
                        dispose();
                    }
            }catch(Exception ex){
               Logger.getLogger(SanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        else if(source == btnQuetMa) {
//            System.out.println("Quét mã");
//        }
        else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnEditCT){
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain); 
        } else if(source == btnEditSP){

            SanPhamDTO snNew = new SanPhamDTO();
            try{
                if(validateInput())
                    if(validateData()){
                        snNew = getInfo();
                        if(!snNew.getHinhAnh().equalsIgnoreCase(this.sp.getHinhAnh())){
                            snNew.setHinhAnh(addImage(snNew.getHinhAnh()));
                        }
                        snNew.setMaSP(this.sp.getMaSP());
                        SanPhamDAO.getInstance().update(sp);
                        this.jpSP.spBUS.update(snNew);
                        this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
                        dispose();
                    }
            }catch(Exception ex){
                Logger.getLogger(SanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        if(source == btnHuyBo){
            dispose();
        }
    }
    public boolean validateData() throws Exception{
        boolean check = true;
        if(!Validation.isNumber(dongia.getText()) && !Validation.isDouble(dongia.getText())){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kiểu dữ liệu cho đơn giá");
            check = false;
        }
        if(!Validation.isNumber(soluong.getText())){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kiểu dữ liệu cho số lượng");
            check = false;
        }
        if(!Validation.isNumber(dungtich.getText())){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kiểu dữ liệu cho dung tích");
            check = false;
        }
        if(hanSD.getDate().compareTo(ngaySX.getDate()) <= 0){
            JOptionPane.showMessageDialog(this, "Hạn sử dụng phải đặt sau ngày sản xuất");
            check = false;
        }
        if(!Validation.isNumber(mavach.getText())){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kiểu dữ liệu cho mã vạch");
            check = false;
        }
        return check;
    }
    public boolean validateInput() throws Exception{
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) ||hanSD.getDate() == null
                || Validation.isEmpty(hangsx.getValue()) || Validation.isEmpty(loaisp.getValue())
                || Validation.isEmpty(donvi.getValue()) || Validation.isEmpty(soluong.getText())
                || Validation.isEmpty(mavach.getText()) || Validation.isEmpty(dongia.getText()) 
                || Validation.isEmpty(dungtich.getText()) || ngaySX.getDate() == null 
                || Validation.isEmpty(hinhanh.getUrl_img()) || Validation.isEmpty(mavach.getText())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            // Check number 
        }
        return check;
    }
    public void eventAddSanPham() throws Exception{
        SanPhamDTO sp = getInfo();
        sp.setHinhAnh(addImage(sp.getHinhAnh()));
        if (jpSP.spBUS.add(sp)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            jpSP.loadDataTalbe(jpSP.listsp);
        }
    }
    public SanPhamDTO getInfo() throws Exception{
        String vhinhanh = this.hinhanh.getUrl_img();
        System.out.print(vhinhanh);
        String vtensp = tenSP.getText();
        int mahangsx = hangsxBUS.getAll().get(this.hangsx.getSelectedIndex()).getMaHang();
        int maloaisp = loaispBUS.getAll().get(this.loaisp.getSelectedIndex()).getMaLoai();
        int madonvi  = donviBUS.getAll().get(this.donvi.getSelectedIndex()).getMaDV();
        double vdongia = Double.parseDouble(dongia.getText());
        int vsoluong = Integer.parseInt(soluong.getText());
        String vmavach = mavach.getText();
        int vdungtich = Integer.parseInt(dungtich.getText());
        Date vngaySX = ngaySX.getDate();
        Date vhanSD = hanSD.getDate();
        SanPhamDTO result = new SanPhamDTO(masp,maloaisp,mahangsx,madonvi,vtensp,vmavach,vsoluong,vdungtich,vdongia,vngaySX, vhanSD,vhinhanh,1);
        return result;
    }
    public void setInfo(SanPhamDTO sp) throws Exception{
        hinhanh.setUrl_img(sp.getHinhAnh());
        tenSP.setText(sp.getTenSP());
//        hangsx.set(hangsxDAO.getById(sp.getMaHang()).getTenHang());
        hangsx.setSelectedIndex(hangsxBUS.getIndexByMaNCC(sp.getMaHang()));
        loaisp.setSelectedItem(loaiSPDAO.getInstance().getById(sp.getMaLoai()).getTenLoai());
        donvi.setSelectedItem(donviDAO.getInstance().getById(sp.getMaDV()).getTenDV());
        dongia.setText(String.valueOf(sp.getDonGia()));
        soluong.setText(String.valueOf(sp.getSoLuong()));
        mavach.setText(String.valueOf(sp.getMaVach()));
        dungtich.setText(String.valueOf(sp.getDungTich()));
        ngaySX.setDate(sp.getNgaySanXuat());
        hanSD.setDate(sp.getHanSuDung());
        
    }
    
}
