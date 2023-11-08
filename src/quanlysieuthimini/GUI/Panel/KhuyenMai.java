//package quanlysieuthimini.GUI.Panel;
//
//import quanlysieuthimini.BUS.KhachHangThanThietBUS;
//import quanlysieuthimini.BUS.NhanVienBUS;
//import quanlysieuthimini.BUS.KhuyenMaiBUS;
//import quanlysieuthimini.DTO.KhuyenMaiDTO;
//import quanlysieuthimini.DTO.TaiKhoanDTO;
//import quanlysieuthimini.GUI.Component.InputDate;
//import quanlysieuthimini.GUI.Component.InputForm;
//import quanlysieuthimini.GUI.Main;
//import quanlysieuthimini.GUI.Component.IntegratedSearch;
//import quanlysieuthimini.GUI.Component.MainFunction;
//import quanlysieuthimini.GUI.Component.Notification;
//import quanlysieuthimini.GUI.Component.NumericDocumentFilter;
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import quanlysieuthimini.GUI.Component.PanelBorderRadius;
//import quanlysieuthimini.GUI.Component.SelectForm;
//import quanlysieuthimini.GUI.Component.TableSorter;
//import quanlysieuthimini.GUI.Dialog.ChiTietKhuyenMaiDialog;
//import quanlysieuthimini.helper.Formater;
//import quanlysieuthimini.helper.JTableExporter;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Stream;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableRowSorter;
//import javax.swing.text.PlainDocument;
//import quanlysieuthimini.BUS.KhuyenMaiBUS;
//
//public final class KhuyenMai extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {
//
//    PanelBorderRadius main, functionBar, box;
//    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
//    JTable tableKhuyenMai;
//    JScrollPane scrollTableKhuyenMai;
//    MainFunction mainFunction;
//    IntegratedSearch search;
//    DefaultTableModel tblModel;
//    SelectForm cbxTenKM, cbxTrangThai, cbxGiamGia;
//    InputDate dateStart, dateEnd;
//
//    Main m;
//
//    Color BackgroundColor = new Color(240, 247, 250);
//
//    ArrayList<KhuyenMaiDTO> listKhuyenMai;
//
//    NhanVienBUS nvBUS = new NhanVienBUS();
//    //KhachHangThanThietBUS khachHangBUS = new KhachHangThanThietBUS();
//    KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();
//
//    public KhuyenMai(Main m) {
//        this.m = m;
//        initComponent();
//        this.listKhuyenMai = khuyenmaiBUS.getAll();
//        loadDataTalbe(this.listKhuyenMai);
//    }
//
//    private void initComponent() {
//
//        this.setBackground(BackgroundColor);
//        this.setLayout(new BorderLayout(0, 0));
//        this.setOpaque(true);
//
//        initPadding();
//
//        contentCenter = new JPanel();
//        contentCenter.setPreferredSize(new Dimension(1100, 600));
//        contentCenter.setBackground(BackgroundColor);
//        contentCenter.setLayout(new BorderLayout(10, 10));
//        this.add(contentCenter, BorderLayout.CENTER);
//
//        functionBar = new PanelBorderRadius();
//        functionBar.setPreferredSize(new Dimension(0, 100));
//        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
//        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//        String[] action = {"create", "cancel", "export"};
//        mainFunction = new MainFunction(m.user.getMaQuyen(), "khuyenmai", action);
//        functionBar.add(mainFunction);
//
//        //Add Event MouseListener
//        for (String ac : action) {
//            mainFunction.btn.get(ac).addActionListener(this);
//        }
//
//        search = new IntegratedSearch(new String[]{"Tất cả", "Mã khuyến mãi", "Tên khuyến mãi", "% Khuyến mãi"});
//        search.cbxChoose.addItemListener(this);
//        search.txtSearchForm.addKeyListener(this);
//        search.btnReset.addActionListener(this);
//        functionBar.add(search);
//        contentCenter.add(functionBar, BorderLayout.NORTH);
//
//        leftFunc();
//
//        main = new PanelBorderRadius();
//        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
//        main.setLayout(boxly);
//        contentCenter.add(main, BorderLayout.CENTER);
//
//        tableKhuyenMai = new JTable();
//        scrollTableKhuyenMai = new JScrollPane();
//        tblModel = new DefaultTableModel();
//        String[] header = new String[]{"STT", "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
//        tblModel.setColumnIdentifiers(header);
//        tableKhuyenMai.setModel(tblModel);
//        tableKhuyenMai.setFocusable(false);
//        tableKhuyenMai.setAutoCreateRowSorter(true);
//        scrollTableKhuyenMai.setViewportView(tableKhuyenMai);
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        tableKhuyenMai.setDefaultRenderer(Object.class, centerRenderer);
//        scrollTableKhuyenMai.setViewportView(tableKhuyenMai);
//        tableKhuyenMai.setFocusable(false);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 0, TableSorter.INTEGER_COMPARATOR);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 1, TableSorter.INTEGER_COMPARATOR);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 3, TableSorter.DOUBLE_COMPARATOR);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 4, TableSorter.DOUBLE_COMPARATOR);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 5, TableSorter.DATE_COMPARATOR);
//        TableSorter.configureTableColumnSorter(tableKhuyenMai, 6, TableSorter.DATE_COMPARATOR);
//        
//        main.add(scrollTableKhuyenMai);
//
//    }
//
//    public void initPadding() {
//        pnlBorder1 = new JPanel();
//        pnlBorder1.setPreferredSize(new Dimension(0, 10));
//        pnlBorder1.setBackground(BackgroundColor);
//        this.add(pnlBorder1, BorderLayout.NORTH);
//
//        pnlBorder2 = new JPanel();
//        pnlBorder2.setPreferredSize(new Dimension(0, 10));
//        pnlBorder2.setBackground(BackgroundColor);
//        this.add(pnlBorder2, BorderLayout.SOUTH);
//
//        pnlBorder3 = new JPanel();
//        pnlBorder3.setPreferredSize(new Dimension(10, 0));
//        pnlBorder3.setBackground(BackgroundColor);
//        this.add(pnlBorder3, BorderLayout.EAST);
//
//        pnlBorder4 = new JPanel();
//        pnlBorder4.setPreferredSize(new Dimension(10, 0));
//        pnlBorder4.setBackground(BackgroundColor);
//        this.add(pnlBorder4, BorderLayout.WEST);
//    }
//
//    public void leftFunc() {
//        box = new PanelBorderRadius();
//        box.setPreferredSize(new Dimension(250, 0));
//        box.setLayout(new GridLayout(7, 1, 10, 0));
//        box.setBorder(new EmptyBorder(0, 5, 100, 5));
//        contentCenter.add(box, BorderLayout.WEST);
//
//        // Handel
//        String[] listTenKM = khuyenmaiBUS.getArrTenKhuyenMai();
//        listTenKM = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listTenKM)).toArray(String[]::new);
//        String[] listGiamGia = khuyenmaiBUS.getArrTenKhuyenMai();
//        listGiamGia = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listGiamGia)).toArray(String[]::new);
//        String[] listTrangThai = nvBUS.getArrTenNhanVien();
//        listTrangThai = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listTrangThai)).toArray(String[]::new);
//
//        // init
//        cbxTenKM = new SelectForm("Tên khuyến mãi", listTenKM);
//        cbxGiamGia = new SelectForm("Giảm giá", listGiamGia);
//        cbxTrangThai = new SelectForm("Trạng thái", listTrangThai);
//        dateStart = new InputDate("Từ ngày");
//        dateEnd = new InputDate("Đến ngày");
//
//        // add listener
//        cbxTenKM.getCbb().addItemListener(this);
//        cbxGiamGia.getCbb().addItemListener(this);
//        cbxTrangThai.getCbb().addItemListener(this);
//        dateStart.getDateChooser().addPropertyChangeListener(this);
//        dateEnd.getDateChooser().addPropertyChangeListener(this);
//
//        box.add(cbxTenKM);
//        box.add(cbxGiamGia);
//        box.add(cbxTrangThai);
//        box.add(dateStart);
//        box.add(dateEnd);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if (source == mainFunction.btn.get("create")) {
////            taoKhuyenMai = new TaoKhuyenMai(m, tk, "create");
////            m.setPanel(taoKhuyenMai);
//        }
//        else if (source == mainFunction.btn.get("cancel")) {
//            if (tableKhuyenMai.getSelectedRow() < 0) {
//                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu!");
//            }
//            else {
//                int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khuyến mãi này không?", "Xóa phiếu", JOptionPane.YES_NO_OPTION);
//                if (n == JOptionPane.YES_OPTION) {
//                    KhuyenMaiDTO px = khuyenmaiBUS.getSelect(tableKhuyenMai.getSelectedRow());
//                    khuyenmaiBUS.cancel(px.getMaKM());
//                    khuyenmaiBUS.remove(tableKhuyenMai.getSelectedRow());
//                    loadDataTalbe(khuyenmaiBUS.getAll());
//                    Notification notification = new Notification(m, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Hủy phiếu thành công");
//                    notification.showNotification();
//                }
//            }
//        } 
//        else if (source == search.btnReset) {
//            resetForm();
//        } 
//        else if (source == mainFunction.btn.get("export")) {
//            try {
//                JTableExporter.exportJTableToExcel(tableKhuyenMai);
//            } catch (IOException ex) {
//                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    public void loadDataTalbe(ArrayList<KhuyenMaiDTO> listKM) {
//        tblModel.setRowCount(0);
//        int size = listKM.size();
//        for (int i = 0; i < size; i++) {
//            tblModel.addRow(new Object[]{
//                i + 1,
//                listKM.get(i).getMaKM(),
//                listKM.get(i).getTenKM(),
//                listKM.get(i).getDieuKienKM(),
//                listKM.get(i).getPhanTramKM(),
//                listKM.get(i).getNgayBatDau(),
//                listKM.get(i).getNgayKetThuc(),
//                listKM.get(i).getTrangThai()});
//        }
//    }
//
//    public int getRow() {
//        return tableKhuyenMai.getSelectedRow();
//    }
//
//    public void Fillter() throws ParseException {
//        if (validateSelectDate()) {
//            int type = search.cbxChoose.getSelectedIndex();
//            int makh = cbxTenKM.getSelectedIndex() == 0 ? 0 : khachHangBUS.getByIndex(cbxTenKM.getSelectedIndex() - 1).getMaKH();
//            int makm = cbxGiamGia.getSelectedIndex() == 0 ? 0 : khuyenmaiBUS.getByIndex(cbxGiamGia.getSelectedIndex() - 1).getMaKM();
//            int manv = cbxTrangThai.getSelectedIndex() == 0 ? 0 : nvBUS.getByIndex(cbxTrangThai.getSelectedIndex() - 1).getMaNV();
//            String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
//            Date time_start = dateStart.getDate() != null ? dateStart.getDate() : new Date(0);
//            Date time_end = dateEnd.getDate() != null ? dateEnd.getDate() : new Date(System.currentTimeMillis());
//            this.listKhuyenMai = khuyenmaiBUS.fillerKhuyenMai(type, input, makh, manv, makm, time_start, time_end);
//            loadDataTalbe(listKhuyenMai);
//        }
//    }
//
//    public void resetForm() {
//        cbxTenKM.setSelectedIndex(0);
//        cbxGiamGia.setSelectedIndex(0);
//        cbxTrangThai.setSelectedIndex(0);
//        search.cbxChoose.setSelectedIndex(0);
//        search.txtSearchForm.setText("");
//        dateStart.getDateChooser().setCalendar(null);
//        dateEnd.getDateChooser().setCalendar(null);
//        this.listKhuyenMai = khuyenmaiBUS.getAll();
//        loadDataTalbe(listKhuyenMai);
//    }
//
//    public boolean validateSelectDate() throws ParseException {
//        Date time_start = dateStart.getDate();
//        Date time_end = dateEnd.getDate();
//
//        Date current_date = new Date();
//        if (time_start != null && time_start.after(current_date)) {
//            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
//            dateStart.getDateChooser().setCalendar(null);
//            return false;
//        }
//        if (time_end != null && time_end.after(current_date)) {
//            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
//            dateEnd.getDateChooser().setCalendar(null);
//            return false;
//        }
//        if (time_start != null && time_end != null && time_start.after(time_end)) {
//            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
//            dateEnd.getDateChooser().setCalendar(null);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        try {
//            Fillter();
//        } catch (ParseException ex) {
//            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        try {
//            Fillter();
//        } catch (ParseException ex) {
//            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        try {
//            Fillter();
//        } catch (ParseException ex) {
//            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}
