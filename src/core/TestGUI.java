package core;

import java.awt.EventQueue;

import core.game.*;
import core.gui.*;

public class TestGUI {
	public static void main(String[] args) {
		Grid grid = new Grid(10, 10);
        new CornFarmer(grid, 0, 0);
        new Beaver(grid, 0, 5);
        new Rabbit(grid, 3, 2);
        new RadishFarmer(grid, 6, 2);
        
        ///Transporter have to be the last object
        new HorizontalTransporter(grid, 0, 1, 10);
        new VerticalTransporter(grid, 5, 2, 10);
        Game game = new Game(grid);
        
        EventQueue.invokeLater(() -> {
            Application ex = new Application(game);
            ex.setVisible(true);
        });
    }
}
