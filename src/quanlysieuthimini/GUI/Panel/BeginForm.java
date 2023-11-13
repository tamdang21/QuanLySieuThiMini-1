package quanlysieuthimini.GUI.Panel;

import java.awt.*;
import javax.swing.*;
import quanlysieuthimini.GUI.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class BeginForm extends JPanel {

    JPanel top, center;
    PanelShadow content[];
        String[][] getSt = {
        {"Tính chính xác", "tinhchinhxac_128px.svg", "<html>Mã vạch là một số duy nhất được <br>gán cho từng sản phầm siêu thị,<br> do đó hệ thống quản lý sản phẩm<br> theo mã vạch sẽ đảm bảo tính <br>chính xác và độ tin cậy cao.</html>"},
        {"Tính bảo mật", "tinhbaomat_128px.svg", "<html>Ngăn chặn việc sử dụng các thiết bị<br> sản phẩm giả mạo hoặc bị đánh cắp.<br> Điều này giúp tăng tính bảo mật cho <br>các hoạt động quản lý điện thoại.</html>"},
        {"Tính hiệu quả", "tinhhieuqua_128px.svg", "<html>Dễ dàng xác định được thông tin <br>về từng sản phầm siêu thị một cách <br>nhanh chóng và chính xác, giúp <br>cho việc quản lý điện thoại được <br>thực hiện một cách hiệu quả hơn.</html>"},
    };
    Color MainColor = new Color(255, 255, 255);
    Color FontColor = new Color(96, 125, 139);
    Color BackgroundColor = new Color(240, 247, 250);
    Color HowerFontColor = new Color(225, 230, 232);

    private void initComponent(String TenNV) {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        top = new JPanel();
        top.setBackground(MainColor);
        top.setPreferredSize(new Dimension(1100, 150));
        top.setLayout(new FlowLayout(1, 0, 10));

        JLabel text = new JLabel("Chào mừng " + TenNV + " đến với Hệ Thống");
        text.setFont(new Font("Arial", Font.BOLD, 30));
        text.setForeground(new Color(10,69,118));
        text.setIcon(new ImageIcon(this.getClass().getResource("/images/icon/icons8_thumb_up_99px.png")));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        top.add(text);

        this.add(top, BorderLayout.NORTH);

        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setPreferredSize(new Dimension(1100, 850));
        center.setLayout(new FlowLayout(1, 50, 50));

        content = new PanelShadow[getSt.length];

        for (int i = 0; i < getSt.length; i++) {
            content[i] = new PanelShadow(getSt[i][1], getSt[i][0], getSt[i][2]);
            center.add(content[i]);
        }

        this.add(center, BorderLayout.CENTER);

    }

    public BeginForm(String TenNV) {
        initComponent(TenNV);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }


}
