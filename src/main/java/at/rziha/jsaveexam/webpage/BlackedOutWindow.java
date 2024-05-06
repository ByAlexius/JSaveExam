package at.rziha.jsaveexam.webpage;

import com.sun.webkit.WebPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class BlackedOutWindow extends JFrame {
    public BlackedOutWindow() {
        setTitle("Blacked Out");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        getContentPane().setBackground(Color.BLACK);

        addWindowFocusListener((new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                requestFocus();
            }
        }));
    }
}
