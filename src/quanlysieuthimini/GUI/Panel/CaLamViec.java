package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.CaLamViecBUS;
import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.GUI.Component.IntegratedSearch;
import quanlysieuthimini.GUI.Component.MainFunction;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CaLamViec extends JPanel {
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Main m;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    Color BackgroundColor = new Color(240, 247, 250);
    public IntegratedSearch search;
    JTable tableCaLamViec;
    JScrollPane scrollTableCaLamViec;
    private DefaultTableModel tblModel;
    CaLamViecBUS clvBUS = new CaLamViecBUS(this);
    PanelBorderRadius main, functionBar;
    MainFunction mainFunction;
    ArrayList<CaLamViecDTO> listclv = clvBUS.getAll();
    DecimalFormat decimalFormat;
    public CaLamViec(Main m){
        this.m = m;
        initComponent();
        tableCaLamViec.setDefaultEditor(Object.class, null);
        loadDataTalbe(listclv);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        decimalFormat = new DecimalFormat("#,###");

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
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

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentCenter.add(functionBar, BorderLayout.NORTH);

        String[] action = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(m.user.getMaQuyen(), "khuyenmai", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(clvBUS);
        }
        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tên Ca"});
        functionBar.add(search);
        search.btnReset.addActionListener(clvBUS);
        search.cbxChoose.addActionListener(clvBUS);
        search.txtSearchForm.getDocument().addDocumentListener(new CaLamViecBUS(search.txtSearchForm, this));

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableCaLamViec = new JTable();
        scrollTableCaLamViec = new JScrollPane();
        tableCaLamViec = new JTable();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã Ca", "Tên Ca", "Giờ bắt đầu", "Giờ kết thúc", "Lương theo ca"};

        tblModel.setColumnIdentifiers(header);
        tableCaLamViec.setModel(tblModel);
        tableCaLamViec.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableCaLamViec.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableCaLamViec.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableCaLamViec.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableCaLamViec.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableCaLamViec.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableCaLamViec.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
//        tableCaLamViec.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        scrollTableCaLamViec.setViewportView(tableCaLamViec);
        main.add(scrollTableCaLamViec);
    }
    public void loadDataTalbe(ArrayList<CaLamViecDTO> list) {
        listclv = list;
        tblModel.setRowCount(0);
        for (CaLamViecDTO caLamViec : listclv) {
            tblModel.addRow(new Object[]{
                    caLamViec.getMaCa(), caLamViec.getTenCa(), caLamViec.getGioBatDau(), caLamViec.getGioKetThuc(), decimalFormat.format(caLamViec.getLuongTheoCa()*1000) + " đ"
            });
            System.out.println(caLamViec.getGioBatDau());
        }
    }
    public int getRow() {
        return tableCaLamViec.getSelectedRow();
    }
    public CaLamViecDTO getCaLamViec() {
        return listclv.get(tableCaLamViec.getSelectedRow());
    }
}
