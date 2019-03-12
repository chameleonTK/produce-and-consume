package core.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

import core.game.Game;

public class Application extends JFrame {

    private JLabel statusbar;
    private Game board;
    public Application(Game g) {
        this.board = g;
        initUI();
    }
    
    private void initUI() {

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar, this.board));        
        
        setResizable(false);
        pack();
        
        setTitle("CS5001");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}