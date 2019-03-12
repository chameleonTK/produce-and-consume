package core.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.gui.Board;

/**
 * The Beaver class behaves as a consumer item which be able to consume 5 units and keep up to 50 units for later 
 * @author 180008901 
 *
 */
public class Beaver extends Consumer {
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	private Image[] img;
	private final int NImage = 3;
	public Beaver(Grid grid, int x, int y) {
		super(grid, x, y);
		img = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img[i] = (new ImageIcon("src/resources/big_demon_idle_anim_f"+i+".png")).getImage();
		}
	}


	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		int nutrition = this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
		int consumedNutrition;
		if (nutrition > 5) {
			consumedNutrition = 5;
		} else {
			consumedNutrition = nutrition;
		}
		
		this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, consumedNutrition);
		this.grid.recordConsumption(consumedNutrition);
		
		if (this.grid.getStockAt(this.xCoordinate, this.yCoordinate) > 50) {
			this.grid.setStockAt(this.xCoordinate, this.yCoordinate, 50);
		}
		
	}
	
	@Override
	public void draw(Board b, Graphics g, int step)  {
		b.drawMonster(g, img[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Beaver(%d)", this.grid.getStockAt(this.xCoordinate, this.yCoordinate));
	}
}
