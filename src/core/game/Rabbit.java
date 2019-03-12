package core.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.gui.Board;

/**
 * The Rabbit class behaves as a consumer item which be able to consume 8 units at a time 
 * @author 180008901 
 *
 */
public class Rabbit extends Consumer {
	
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	
	private Image[] img;
	private final int NImage = 3;
	public Rabbit(Grid grid, int x, int y) {
		super(grid, x, y);
		img = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img[i] = (new ImageIcon("src/resources/ogre_idle_anim_f"+i+".png")).getImage();
		}
	}

	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		int nutrition = this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
		if (nutrition > 8) {
			nutrition = 8;
		}
		
		this.grid.emptyStockAt(this.xCoordinate, this.yCoordinate);
		this.grid.recordConsumption(nutrition);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Rabbit(%d)", this.grid.getStockAt(this.xCoordinate, this.yCoordinate));
	}
	
	public void draw(Board b, Graphics g, int step)  {
		b.drawMonster(g, img[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate);
	}
}
