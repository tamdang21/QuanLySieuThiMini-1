package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.QuyenBUS;
import quanlysieuthimini.DTO.QuyenDTO;
import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Dialog.PhanQuyenDialog;
import quanlysieuthimini.GUI.Component.IntegratedSearch;
import quanlysieuthimini.GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Main;
import quanlysieuthimini.helper.JTableExporter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PhanQuyen extends JPanel implements ActionListener {

    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    PanelBorderRadius main, functionBar;
    JPanel contentCenter;
    JTable tblQuyen;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    Main m;
    public QuyenBUS nhomquyenBUS = new QuyenBUS();
    public ArrayList<QuyenDTO> listnhomquyen = nhomquyenBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new GridLayout(1,1));
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setOpaque(true);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 20));
        this.add(contentCenter);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "export"};
        mainFunction = new MainFunction(m.user.getMaQuyen(), "nhomquyen", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ArrayList<QuyenDTO> rs = nhomquyenBUS.search(search.txtSearchForm.getText());
                loadDataTalbe(rs);
            }
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        contentCenter.add(main, BorderLayout.CENTER);

        tblQuyen = new JTable();
        tblQuyen.setDefaultEditor(Object.class, null);
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã nhóm quyền", "Tên nhóm quyền"};
        tblModel.setColumnIdentifiers(header);
        tblQuyen.setModel(tblModel);
        scrollTable.setViewportView(tblQuyen);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tblQuyen.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setPreferredWidth(300);
        tblQuyen.setFocusable(false);
        main.add(scrollTable);
    }

    public PhanQuyen(Main m) {
        this.m = m;
        initComponent();
        loadDataTalbe(listnhomquyen);
    }

    public void loadDataTalbe(ArrayList<QuyenDTO> result) {
        tblModel.setRowCount(0);
        for (QuyenDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMaQuyen(), ncc.getTenQuyen()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            PhanQuyenDialog pq = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Thêm nhóm quyền", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Chỉnh sửa nhóm quyền", true, "update", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Chi tiết nhóm quyền", true, "view", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                int input = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa nhà cung cấp!", "Xóa nhà cung cấp",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nhomquyenBUS.delete(listnhomquyen.get(index));
                    loadDataTalbe(listnhomquyen);
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tblQuyen);
            } catch (IOException ex) {
                Logger.getLogger(PhanQuyen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(e.getSource() == this.search.btnReset) {
            loadDataTalbe(listnhomquyen);
        }
    }

    public int getRowSelected() {
        int index = tblQuyen.getSelectedRow();
        if (tblQuyen.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhóm quyền");
            return -1;
        }
        return index;
    }
}
