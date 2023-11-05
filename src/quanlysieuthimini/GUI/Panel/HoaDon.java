package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.KhachHangThanThietBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.BUS.HoaDonBUS;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.DTO.TaiKhoanDTO;
import quanlysieuthimini.GUI.Component.InputDate;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Main;
import quanlysieuthimini.GUI.Component.IntegratedSearch;
import quanlysieuthimini.GUI.Component.MainFunction;
import quanlysieuthimini.GUI.Component.Notification;
import quanlysieuthimini.GUI.Component.NumericDocumentFilter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Component.SelectForm;
import quanlysieuthimini.GUI.Component.TableSorter;
import quanlysieuthimini.GUI.Dialog.ChiTietHoaDonDialog;
import quanlysieuthimini.helper.Formater;
import quanlysieuthimini.helper.JTableExporter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.PlainDocument;
import quanlysieuthimini.BUS.KhuyenMaiBUS;

public final class HoaDon extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    PanelBorderRadius main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableHoaDon;
    JScrollPane scrollTableHoaDon;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    SelectForm cbxKhachHang, cbxNhanVien, cbxKhuyenMai;
    InputDate dateStart, dateEnd;
    InputForm moneyMin, moneyMax;

    Main m;
    TaoHoaDon taoHoaDon;
    TaiKhoanDTO tk;

    Color BackgroundColor = new Color(240, 247, 250);

    ArrayList<HoaDonDTO> listHoaDon;

    NhanVienBUS nvBUS = new NhanVienBUS();
    HoaDonBUS pxBUS = new HoaDonBUS();
    KhachHangThanThietBUS khachHangBUS = new KhachHangThanThietBUS();
    KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();

    public HoaDon(Main m, TaiKhoanDTO tk) {
        this.m = m;
        this.tk = tk;
        initComponent();
        this.listHoaDon = pxBUS.getAll();
        loadDataTalbe(this.listHoaDon);
    }

    private void initComponent() {

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "detail", "cancel", "export"};
        mainFunction = new MainFunction(m.user.getMaQuyen(), "banhang", action);
        functionBar.add(mainFunction);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã hóa đơn", "Khuyến mãi", "Khách hàng", "Nhân viên bán"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(this);
        search.btnReset.addActionListener(this);
        functionBar.add(search);
        contentCenter.add(functionBar, BorderLayout.NORTH);

        leftFunc();

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        contentCenter.add(main, BorderLayout.CENTER);

        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã hóa đơn", "Khách hàng", "Khuyến mãi", "Nhân viên bán", "Ngày lập", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        tableHoaDon.setFocusable(false);
        tableHoaDon.setAutoCreateRowSorter(true);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableHoaDon.setDefaultRenderer(Object.class, centerRenderer);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        tableHoaDon.setFocusable(false);
        TableSorter.configureTableColumnSorter(tableHoaDon, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableHoaDon, 1, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableHoaDon, 6, TableSorter.VND_CURRENCY_COMPARATOR);

        main.add(scrollTableHoaDon);

    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    public void leftFunc() {
        box = new PanelBorderRadius();
        box.setPreferredSize(new Dimension(250, 0));
        box.setLayout(new GridLayout(7, 1, 10, 0));
        box.setBorder(new EmptyBorder(0, 5, 100, 5));
        contentCenter.add(box, BorderLayout.WEST);

        // Handel
        String[] listKh = khachHangBUS.getArrTenKhachHang();
        listKh = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listKh)).toArray(String[]::new);
        String[] listKM = khuyenmaiBUS.getArrTenKhuyenMai();
        listKM = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listKM)).toArray(String[]::new);
        String[] listNv = nvBUS.getArrTenNhanVien();
        listNv = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listNv)).toArray(String[]::new);

        // init
        cbxKhachHang = new SelectForm("Khách hàng", listKh);
        cbxKhuyenMai = new SelectForm("Khuyến mãi", listKM);
        cbxNhanVien = new SelectForm("Nhân viên bán hàng", listNv);
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");
        moneyMin = new InputForm("Từ số tiền (VND)");
        moneyMax = new InputForm("Đến số tiền (VND)");

        PlainDocument doc_min = (PlainDocument) moneyMin.getTxtForm().getDocument();
        doc_min.setDocumentFilter(new NumericDocumentFilter());

        PlainDocument doc_max = (PlainDocument) moneyMax.getTxtForm().getDocument();
        doc_max.setDocumentFilter(new NumericDocumentFilter());

        // add listener
        cbxKhachHang.getCbb().addItemListener(this);
        cbxKhuyenMai.getCbb().addItemListener(this);
        cbxNhanVien.getCbb().addItemListener(this);
        dateStart.getDateChooser().addPropertyChangeListener(this);
        dateEnd.getDateChooser().addPropertyChangeListener(this);
        moneyMin.getTxtForm().addKeyListener(this);
        moneyMax.getTxtForm().addKeyListener(this);

        box.add(cbxKhachHang);
        box.add(cbxKhuyenMai);
        box.add(cbxNhanVien);
        box.add(dateStart);
        box.add(dateEnd);
        box.add(moneyMin);
        box.add(moneyMax);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btn.get("create")) {
            taoHoaDon = new TaoHoaDon(m, tk, "create");
            m.setPanel(taoHoaDon);
        } 
        else if (source == mainFunction.btn.get("detail")) {
            if (getRow() < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần xem!");
            } else {
                ChiTietHoaDonDialog cthd = new ChiTietHoaDonDialog(m, "Thông tin hóa đơn", true, pxBUS.getSelect(getRow()));
            }
        } 
        else if (source == mainFunction.btn.get("cancel")) {
//            if (tableHoaDon.getSelectedRow() < 0) {
//                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu!");
//            } else {
//                int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phiếu này?", "Xóa phiếu", JOptionPane.YES_NO_OPTION);
//                if (n == JOptionPane.YES_OPTION) {
//                    HoaDonDTO px = pxBUS.getSelect(tableHoaDon.getSelectedRow());
//                    pxBUS.cancel(px.getMaphieu());
//                    pxBUS.remove(tableHoaDon.getSelectedRow());
//                    loadDataTalbe(pxBUS.getAll());
//                    Notification notification = new Notification(m, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Hủy phiếu thành công");
//                    notification.showNotification();
//                }
//            }
        } 
        else if (source == search.btnReset) {
            resetForm();
        } else if (source == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableHoaDon);
            } catch (IOException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadDataTalbe(ArrayList<HoaDonDTO> listHD) {
        tblModel.setRowCount(0);
        int size = listHD.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1,
                listHD.get(i).getMaHD(),
                khachHangBUS.getTenKhachHang(listHD.get(i).getMaKH()),
                khuyenmaiBUS.getTenKhuyenMai(listHD.get(i).getMaKM()),
                nvBUS.getNameById(listHD.get(i).getMaNV()),
                Formater.FormatTime(listHD.get(i).getNgayLap()),
                Formater.FormatVND(listHD.get(i).getTongTien())});
        }
    }

    public int getRow() {
        return tableHoaDon.getSelectedRow();
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            int type = search.cbxChoose.getSelectedIndex();
            int makh = cbxKhachHang.getSelectedIndex() == 0 ? 0 : khachHangBUS.getByIndex(cbxKhachHang.getSelectedIndex() - 1).getMaKH();
            int makm = cbxKhuyenMai.getSelectedIndex() == 0 ? 0 : khuyenmaiBUS.getByIndex(cbxKhuyenMai.getSelectedIndex() - 1).getMaKM();
            int manv = cbxNhanVien.getSelectedIndex() == 0 ? 0 : nvBUS.getByIndex(cbxNhanVien.getSelectedIndex() - 1).getMaNV();
            String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
            Date time_start = dateStart.getDate() != null ? dateStart.getDate() : new Date(0);
            Date time_end = dateEnd.getDate() != null ? dateEnd.getDate() : new Date(System.currentTimeMillis());
            String min_price = moneyMin.getText();
            String max_price = moneyMax.getText();
            this.listHoaDon = pxBUS.fillerHoaDon(type, input, makh, manv, makm, time_start, time_end, min_price, max_price);
            loadDataTalbe(listHoaDon);
        }
    }

    public void resetForm() {
        cbxKhachHang.setSelectedIndex(0);
        cbxKhuyenMai.setSelectedIndex(0);
        cbxNhanVien.setSelectedIndex(0);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.setText("");
        moneyMin.setText("");
        moneyMax.setText("");
        dateStart.getDateChooser().setCalendar(null);
        dateEnd.getDateChooser().setCalendar(null);
        this.listHoaDon = pxBUS.getAll();
        loadDataTalbe(listHoaDon);
    }

    public boolean validateSelectDate() throws ParseException {
        Date time_start = dateStart.getDate();
        Date time_end = dateEnd.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateStart.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
