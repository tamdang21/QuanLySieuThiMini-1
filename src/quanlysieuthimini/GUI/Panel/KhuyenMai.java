package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.GUI.Component.IntegratedSearch;
import quanlysieuthimini.GUI.Component.MainFunction;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public final class KhuyenMai extends JPanel {
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Main m;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    Color BackgroundColor = new Color(240, 247, 250);
    public IntegratedSearch search;
    JTable tableKhuyenMai;
    JScrollPane scrollTableKhuyenMai;
    private DefaultTableModel tblModel;
    KhuyenMaiBUS kmBUS = new KhuyenMaiBUS(this);
    PanelBorderRadius main, functionBar;
    MainFunction mainFunction;
    ArrayList<KhuyenMaiDTO> listkm = kmBUS.getAll();
    DecimalFormat decimalFormat;
    public KhuyenMai(Main m){
        this.m = m;
        initComponent();
        tableKhuyenMai.setDefaultEditor(Object.class, null);
        loadDataTalbe(listkm);
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

        String[] action = {"create", "update", "delete", "detail", "import", "export"};
        mainFunction = new MainFunction(m.user.getMaQuyen(), "khuyenmai", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(kmBUS);
        }
        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Tên KM", "Phần trăm KM"});
        functionBar.add(search);
        search.btnReset.addActionListener(kmBUS);
        search.cbxChoose.addActionListener(kmBUS);
        search.txtSearchForm.getDocument().addDocumentListener(new KhuyenMaiBUS(search.txtSearchForm, this));

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableKhuyenMai = new JTable();
        scrollTableKhuyenMai = new JScrollPane();
        tableKhuyenMai = new JTable();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã khuyến mãi", "Tên khuyến mãi", "Điều Kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};

        tblModel.setColumnIdentifiers(header);
        tableKhuyenMai.setModel(tblModel);
        tableKhuyenMai.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableKhuyenMai.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhuyenMai.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        scrollTableKhuyenMai.setViewportView(tableKhuyenMai);
        main.add(scrollTableKhuyenMai);
    }

    public void loadDataTalbe(ArrayList<KhuyenMaiDTO> list) {
        listkm = list;
        tblModel.setRowCount(0);
        for (KhuyenMaiDTO khuyenMai : listkm) {
            tblModel.addRow(new Object[]{
                khuyenMai.getMaKM(), 
                khuyenMai.getTenKM(), 
                "≥ "+ decimalFormat.format(khuyenMai.getDieuKienKM()*1000) + " đ", 
                ((double) Math.floor(khuyenMai.getPhanTramKM() * 10) / 10)*100 + " %", 
                khuyenMai.getNgayBatDau(), 
                khuyenMai.getNgayKetThuc(),
                khuyenMai.getTrangThai()
            });
        }
    }
    public int getRow() {
        return tableKhuyenMai.getSelectedRow();
    }
    public KhuyenMaiDTO getKhuyenMai() {
        return listkm.get(tableKhuyenMai.getSelectedRow());
    }


}
