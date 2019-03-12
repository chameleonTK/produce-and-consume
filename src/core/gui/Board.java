package core.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.game.Game;
import core.game.Grid;
import core.game.Item;

public class Board extends JPanel implements ActionListener{
	private Game board;
//    private final int NUM_IMAGES = 13;
    public final int CELL_SIZE = 50;
//
//    private final int COVER_FOR_CELL = 10;
//    private final int MARK_FOR_CELL = 10;
//    private final int EMPTY_CELL = 0;
//    private final int MINE_CELL = 9;
//    private final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
//    private final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;
//
//    private final int DRAW_MINE = 9;
//    private final int DRAW_COVER = 10;
//    private final int DRAW_MARK = 11;
//    private final int DRAW_WRONG_MARK = 12;
//    
//    private final int N_MINES = 40;
    private int N_ROWS;
    private int N_COLS;
//    
    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;     
//
//    private int[] field;
//    private boolean inGame;
//    private int mines_left;
    private Image img, tile;
//
    public final int SAMPLING = 10;
    public final int RENDER_RATE = 200;
    
    private final JLabel statusbar;
    
    private Grid grid;
    private Item currentItem;
    private int counter = 0;
    public Board(JLabel statusbar, Game g) {
    	this.board = g;
        this.statusbar = statusbar;
        
        this.N_ROWS = this.board.getHeight();
        this.N_COLS = this.board.getWidth();
    //    
        this.BOARD_WIDTH = N_ROWS * CELL_SIZE + 1;
        this.BOARD_HEIGHT = N_COLS * CELL_SIZE + 1; 
        
        initBoard();
        grid = (Grid)this.board.getGrid();
    }
    
    private void initBoard() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        tile = (new ImageIcon("src/resources/tile-resized.png")).getImage();
        setDoubleBuffered(true);
        newGame();
        
        Timer timer = new Timer(RENDER_RATE, this);
        timer.start();
    }
    private void newGame() {
    	
    }

    public void drawMonster(Graphics g, Image img, int i, int j) {
    	g.drawImage(img, (i * CELL_SIZE), (j * CELL_SIZE)-5, this);
    }
    
    public void drawMonster(Graphics g, Image img, int i, int j, int offsetX, int offsetY) {
    	g.drawImage(img, (i * CELL_SIZE) - offsetX, (j * CELL_SIZE) - offsetY, this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	
    	for(int i=0;i < grid.getHeight(); i++) {
    		for(int j=0;j < grid.getWidth(); j++) {
    			g.drawImage(tile, (i * CELL_SIZE), (j * CELL_SIZE), this);
    		}
    		
    	}
    	
    	
    	//Draw transporter first
    	for(int i=0;i < grid.getHeight(); i++) {
    		for(int j=0;j < grid.getWidth(); j++) {
    			currentItem = (Item)grid.getItem(i, j);
    			if (currentItem != null && currentItem.isTransporter()) {
    				currentItem.draw(this, g, this.counter);
    			}
    		}
    	}
    	
    	for(int i=0;i < grid.getHeight(); i++) {
    		for(int j=0;j < grid.getWidth(); j++) {
    			currentItem = (Item)grid.getItem(i, j);
    			if (currentItem != null && !currentItem.isTransporter()) {
    				currentItem.draw(this, g, this.counter);
    			}
    		}
    	}
    	
    	statusbar.setText("Step:"+ board.time.getValue() +" Consm:"+grid.getTotalConsumption()+"  Prod:"+grid.getTotalProduction());
    	g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	counter++;
    	repaint();
    	
    	if (counter%SAMPLING == 0) {
    		counter = 0;
    		board.runByStep();
    	}
    }
    
}