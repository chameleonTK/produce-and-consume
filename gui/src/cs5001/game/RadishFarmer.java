package cs5001.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import cs5001.gui.Board;

/**
 * The RadishFarmer class behaves as a farmer item which be able to produce 30 units in every 3 steps (with some condition)
 * @author 180008901
 *
 */
public class RadishFarmer extends Farmer {

	protected int nutritionPerUnit = 1;
	protected int produceRate = 10;
	protected int waitingTime = 3;
	
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	private Image[] img, img2;
	private final int NImage = 3;
	public RadishFarmer(Grid grid, int x, int y) {
		super(grid, x, y);
		img = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img[i] = (new ImageIcon("src/resources/chest_empty_open_anim_f"+i+".png")).getImage();
		}
		
		img2 = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img2[i] = (new ImageIcon("src/resources/chest_full_open_anim_f"+i+".png")).getImage();
		}
	}
	
	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		if ((timeStep.getValue()) % this.waitingTime != 0 ) {
			return;
		}
	
		if (!this.meetRequirement()) {
			return;
		}
		
		this.addToStock(this.produceRate * this.nutritionPerUnit);
		this.grid.recordProduction(this.produceRate * this.nutritionPerUnit);
	}
	

	/* (non-Javadoc)
	 * @see Farmer#meetRequirement()
	 */
	public boolean meetRequirement() {
		List<Item> surroungings = new LinkedList<Item>(); 
		surroungings.add((Item)this.grid.getItem(this.xCoordinate, this.yCoordinate + 1));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate, this.yCoordinate - 1));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate + 1, this.yCoordinate));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate - 1, this.yCoordinate));
		
		for (int i=0; i< surroungings.size(); i++) {
			Item thing = surroungings.get(i);
			if (thing != null && thing.isFarmer()) {
				return false;
			}
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Radish(%d)", this.grid.getStockAt(this.xCoordinate, this.yCoordinate));
	}
	
	public void draw(Board b, Graphics g, int step)  {
		if (this.getStock() > 0) {
			b.drawMonster(g, img2[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate, -12, -10);
		} else {
			b.drawMonster(g, img[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate, -12, -10);
		}
	}
}
