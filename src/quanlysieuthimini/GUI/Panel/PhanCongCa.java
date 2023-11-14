package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.KhachHangThanThietBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.DTO.PhanCongCaDTO;
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
//import quanlysieuthimini.GUI.Dialog.ChiTietPhanCongCaDialog;
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
import quanlysieuthimini.BUS.CaLamViecBUS;
import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.GUI.Dialog.ListNhanVien;
import quanlysieuthimini.GUI.Dialog.ListNhanVienPCC;

public final class PhanCongCa extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    PanelBorderRadius main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhanCongCa;
    JScrollPane scrollTablePhanCongCa;
    IntegratedSearch search;
    MainFunction mainFunction;
    DefaultTableModel tblModel;
    SelectForm cbxTenCa, cbxNhanVien, cbxThu;

    Main m;
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    TaiKhoanDTO tk;

    Color BackgroundColor = new Color(240, 247, 250);

    ArrayList<PhanCongCaDTO> listPhanCongCa;
    ArrayList<CaLamViecDTO> listCaLamViec;

    CaLamViecBUS calamviecBUS = new CaLamViecBUS();
    NhanVienBUS nhanvienBUS = new NhanVienBUS();
    
    public PhanCongCa() {
        
    }

    public PhanCongCa(Main m, TaiKhoanDTO tk) {
        this.m = m;
        this.tk = tk;
        initComponent();
        this.listCaLamViec = calamviecBUS.getAll();
        this.listPhanCongCa = calamviecBUS.getAllNotId();
        loadDataTalbe(this.listPhanCongCa);
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

        String[] action = {"quaylai", "create", "delete"};
        mainFunction = new MainFunction(m.user.getMaQuyen(), "nhacungcap", action);
        functionBar.add(mainFunction);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        
        search = new IntegratedSearch(new String[]{"Tất cả", "Tên ca", "Nhân viên"});
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

        tablePhanCongCa = new JTable();
        scrollTablePhanCongCa = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Tên ca", "Nhân viên", "Ngày làm", "Thời gian bắt đầu", "Thời gian bắt đầu"};
        tblModel.setColumnIdentifiers(header);
        tablePhanCongCa.setModel(tblModel);
        tablePhanCongCa.setFocusable(false);
        tablePhanCongCa.setAutoCreateRowSorter(true);
        scrollTablePhanCongCa.setViewportView(tablePhanCongCa);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhanCongCa.setDefaultRenderer(Object.class, centerRenderer);
        scrollTablePhanCongCa.setViewportView(tablePhanCongCa);
        tablePhanCongCa.setFocusable(false);

        main.add(scrollTablePhanCongCa);

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
        box.setLayout(new GridLayout(3, 1, 10, 0));
        box.setBorder(new EmptyBorder(0, 5, 350, 5));
        contentCenter.add(box, BorderLayout.WEST);

        // Handel
        String[] listTenCa = calamviecBUS.getArrTenCa();
        listTenCa = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listTenCa)).toArray(String[]::new);
        String[] listThu = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật"};
        listThu = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listThu)).toArray(String[]::new);
        String[] listNv = nhanvienBUS.getArrTenNhanVien();
        listNv = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listNv)).toArray(String[]::new);

        // init
        cbxTenCa = new SelectForm("Tên ca", listTenCa);
        cbxThu = new SelectForm("Ngày làm", listThu);
        cbxNhanVien = new SelectForm("Nhân viên", listNv);

        // add listener
        cbxTenCa.getCbb().addItemListener(this);
        cbxThu.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadTableTheoThu(listPhanCongCa, (String) cbxThu.getCbb().getSelectedItem());
            }
        });
        cbxNhanVien.getCbb().addItemListener(this);

        box.add(cbxTenCa);
        box.add(cbxThu);
        box.add(cbxNhanVien);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btn.get("create")) {
            new ListNhanVienPCC(this, owner, "Chọn ca làm việc", true);
        }
        else if (source == mainFunction.btn.get("delete")) {
//            if (tablePhanCongCa.getSelectedRow() < 0) {
//                JOptionPane.showMessageDialog(null, "Vui lòng chọn ca cần hủy!");
//            } else {
//                int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ca làm của nhân viên này?", "Xóa ca làm", JOptionPane.YES_NO_OPTION);
//                if (n == JOptionPane.YES_OPTION) {
//                    PhanCongCaDTO px = calamviecBUS.getSelect(tablePhanCongCa.getSelectedRow());
//                    calamviecBUS.deleteClv(px.getMaphieu());
//                    calamviecBUS.de(tablePhanCongCa.getSelectedRow());
//                    loadDataTalbe(listPhanCongCa);
//                    Notification notification = new Notification(m, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Hủy phiếu thành công");
//                    notification.showNotification();
//                }
//            }
        } 
        else if (source == search.btnReset) {
            resetForm();
        } 
        else if (source == mainFunction.btn.get("quaylai")) {
            CaLamViec clv = new CaLamViec(m, tk);
            m.setPanel(clv);
        }
        
    }

    public void loadDataTalbe(ArrayList<PhanCongCaDTO> listPCC) {
        tblModel.setRowCount(0);
        int size = listPCC.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1,
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getTenCa(),
                nhanvienBUS.getById(listPCC.get(i).getMaNV()).getTenNV(),
                listPCC.get(i).getThu(),
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getGioBatDau(),
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getGioKetThuc()
            });
        }
    }
    
    public void loadTableTheoThu(ArrayList<PhanCongCaDTO> listPCC, String thu) {
        tblModel.setRowCount(0);
        int size = listPCC.size();
        for (int i = 0; i < size; i++) {
            if(thu.equals("Tất cả")) {
                loadDataTalbe(listPCC);
                break;
            }
            else if(listPCC.get(i).getThu().equals(thu)) {
                tblModel.addRow(new Object[]{
                i + 1,
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getTenCa(),
                nhanvienBUS.getById(listPCC.get(i).getMaNV()).getTenNV(),
                listPCC.get(i).getThu(),
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getGioBatDau(),
                calamviecBUS.getById(listPCC.get(i).getMaCa()).getGioKetThuc()
            });
            }
        }
    }

    public int getRow() {
        return tablePhanCongCa.getSelectedRow();
    }

    public void Fillter() throws ParseException {
        int type = search.cbxChoose.getSelectedIndex();
            int maca = cbxTenCa.getSelectedIndex() == 0 ? 0 : calamviecBUS.getByIndex(cbxTenCa.getSelectedIndex() - 1).getMaCa();
            String thu = (String) cbxThu.getSelectedItem();
            int manv = cbxNhanVien.getSelectedIndex() == 0 ? 0 : nhanvienBUS.getByIndex(cbxNhanVien.getSelectedIndex() - 1).getMaNV();
            
            String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
            this.listPhanCongCa = calamviecBUS.fillerPCC(type, input, maca, manv, thu);
            loadDataTalbe(listPhanCongCa);
    }

    public void resetForm() {
        cbxTenCa.setSelectedIndex(0);
        cbxThu.setSelectedIndex(0);
        cbxNhanVien.setSelectedIndex(0);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.setText("");
        this.listPhanCongCa = calamviecBUS.getAllNotId();
        loadDataTalbe(listPhanCongCa);
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
            Logger.getLogger(PhanCongCa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhanCongCa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhanCongCa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
