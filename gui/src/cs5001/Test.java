package cs5001;

import cs5001.gui.*;
import cs5001.game.*;
import java.awt.EventQueue;

public class Test {
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
