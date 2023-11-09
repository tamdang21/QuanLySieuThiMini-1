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

/**
*
* @author Tran Nhat Sinh
*/
public final class SanPhamDialog extends JDialog implements ActionListener {

   SanPhamDTO sp;
   private HeaderTitle titlePage;
   private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
   private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCTCauHinh, btnDeleteCauHinh, btnResetCauHinh, btnAddSanPham, btnBack, btnViewCauHinh;
   InputForm tenSP, dongia, soluong, dungtich, ngaySX, hanSD;
   InputForm txtgianhap, txtgiaxuat;
   SelectForm hangsx, loaisp, donvi;
   SelectForm thuonghieu, khuvuc;
   InputImage hinhanh;
   JTable tblcauhinh;
   JScrollPane scrolltblcauhinh;
   DefaultTableModel tblModelch;
   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
   quanlysieuthimini.GUI.Panel.SanPham jpSP;
   HangSanXuatDAO hangsxDAO;
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
   private ButtonCustom btnSaveCH;

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
       System.out.println("khơi tao xem sanphamdialog");
       init(jpSP);
       initComponents(title, type);

   }

   public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
       super(owner, title, modal);
       System.out.println("Update sanphamdialog");
       init(jpSP);
       this.sp = sp;
       //get phiên bản sp
//     this.listch = jpSP.spBUS.cauhinhBus.getAll(sp.getMasp());
       initComponents(title, type);
   }

   public void initCardOne(String type) {
       pnCenter = new JPanel(new BorderLayout());
       pninfosanpham = new JPanel(new GridLayout(3, 3, 0, 0));
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
       dungtich = new InputForm("Dung Tích");
       ngaySX = new InputForm("Ngày Sản Xuất");
       hanSD =  new InputForm("Hạn sử dụng");
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
       
       pnbottom = new JPanel (new  FlowLayout());
       pnbottom.setBorder (new EmptyBorder(20,0,10,0));
       pnbottom.setBackground(Color.white);
       switch (type){
//            case "view" -> {
//                btnViewCauHinh = new ButtonCustom("Xem cấu hình", "warning", 14);
//                btnViewCauHinh.addActionListener(this);
//                pnbottom.add(btnViewCauHinh);
//            }
            case "update" -> {
                btnSaveCH = new ButtonCustom("Lưu thông tin", "success", 14);
//                btnEditCT = new ButtonCustom("Sửa cấu hình", "warning", 14);
                btnSaveCH.addActionListener(this);
//                btnEditCT.addActionListener(this);
                pnbottom.add(btnSaveCH);
//                pnbottom.add(btnEditCT);
            }
            case "create" -> {
//                btnThemCHMS = new ButtonCustom("Tạo cấu hình", "success", 14);
//                btnThemCHMS.addActionListener(this);
//                pnbottom.add(btnThemCHMS);
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
    public void setInfo(SanPhamDTO sp) throws Exception{
        hinhanh.setUrl_img(sp.getHinhAnh());
        tenSP.setText(sp.getTenSP());
//        hangsx.set(hangsxDAO.getById(sp.getMaHang()).getTenHang());
        hangsx.setSelectedIndex(hangsxBUS.getIndexByMaNCC(sp.getMaHang()));
        loaisp.setSelectedItem(loaiSPDAO.getInstance().getById(sp.getMaLoai()).getTenLoai());
        donvi.setSelectedItem(donviDAO.getInstance().getById(sp.getMaDV()).getTenDV());
        dongia.setText(String.valueOf(sp.getDonGia()));
        soluong.setText(String.valueOf(sp.getSoLuong()));
        dungtich.setText(String.valueOf(sp.getDungTich()));
        ngaySX.setText(Formater.FormatDate(sp.getNgaySanXuat()));
        hanSD.setText(Formater.FormatDate(sp.getHanSuDung()));
        
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
        if (source == btnThemCHMS && validateCardOne()) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } 
//        else if (source == btnBack) {
//            CardLayout c = (CardLayout) pnmain.getLayout();
//            c.previous(pnmain);
//        } 
//        else if (source == btnAddCauHinh) {
//            if (validateCardTwo() && checkTonTai()) {
//                listch.add(getCauHinh());
//                loadDataToTableCauHinh(this.listch);
//                resetFormCauHinh();
//            }
//        }else if (source == btnResetCauHinh) {
//            resetFormCauHinh();
//            loadDataToTableCauHinh(this.listch);
//        } else if (source == btnDeleteCauHinh) {
//            int index = getRowCauHinh();
//            this.listch.remove(index);
//            loadDataToTableCauHinh(this.listch);
//            resetFormCauHinh();
//        } else if (source == btnEditCTCauHinh) {
//            eventEditCauHinh();
//           loadDataToTableCauHinh(this.listch);
//        }
        else if (source == btnAddSanPham) {
            try{
                eventAddSanPham();
                System.out.print("tbn addThem sp");
            }catch(Exception ex){
                System.out.print(ex);
            }
        } 
        else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnEditCT){
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if(source == btnSaveCH){

            SanPhamDTO snNew = new SanPhamDTO();
            try{
                snNew = getInfo();
            }catch(Exception ex){
                System.out.println(ex);
            }
            if(!snNew.getHinhAnh().equalsIgnoreCase(this.sp.getHinhAnh())){
                snNew.setHinhAnh(addImage(snNew.getHinhAnh()));
            }
            snNew.setMaSP(this.sp.getMaSP());
            SanPhamDAO.getInstance().update(sp);
            this.jpSP.spBUS.update(snNew);
            this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
            dispose();
            
        }
        if(source == btnHuyBo){
            dispose();
        }
    }
        public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) || Validation.isEmpty(hanSD.getText())
                || Validation.isEmpty(hangsx.getValue()) || Validation.isEmpty(loaisp.getValue())
                || Validation.isEmpty(donvi.getValue()) || Validation.isEmpty(soluong.getText())
                || Validation.isEmpty(dongia.getText()) || Validation.isEmpty(dungtich.getText())
                || Validation.isEmpty(ngaySX.getText())) {
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
            dispose();
        }
    }

    public SanPhamDTO getInfo() throws Exception{
        String hinhanh = this.hinhanh.getUrl_img();
        String vtensp = tenSP.getText();
        int mahangsx = hangsxBUS.getAll().get(this.hangsx.getSelectedIndex()).getMaHang();
        int maloaisp = loaispBUS.getAll().get(this.hangsx.getSelectedIndex()).getMaLoai();
        int madonvi  = donviBUS.getAll().get(this.hangsx.getSelectedIndex()).getMaDV();
        double vdongia = Double.parseDouble(dongia.getText());
        int vsoluong = Integer.parseInt(soluong.getText());
        int vdungtich = Integer.parseInt(dungtich.getText());
        Date vngaySX = sp.getNgaySanXuat();
        Date vhanSD = sp.getHanSuDung();
        String mavach = "";
        
        SanPhamDTO result = new SanPhamDTO(masp,maloaisp,mahangsx,madonvi,vtensp,mavach,vsoluong,vdungtich,vdongia,vngaySX, vhanSD,hinhanh,1);
        return result;
    }
}
