/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadFactory;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QRCode_Dialog2 extends JDialog {

    private final Dimension ds = new Dimension(700, 500);
    private final Dimension cs = WebcamResolution.VGA.getSize();
    private final Webcam wCam = Webcam.getWebcams().get(0);
    private final WebcamPanel wCamPanel = new WebcamPanel(wCam, ds, false);
    private JPanel panelCam;
    private JTextField textfield;
    private ButtonCustom btnExit;
    private Thread t;

    public QRCode_Dialog2(JFrame owner, String title, boolean modal, JTextField textfield) {
        super(owner, title, modal);
        this.textfield = textfield;
        init();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        
        this.setLayout(new BorderLayout());
        setSize(700, 500);
        
    }

    public void soundScan() {
        try {
            File soundFile = new File("./sound_scan/Barcode-scanner-beep-sound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
