package core.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.gui.Board;

/**
 * The HorizontalTransporter class behaves as a transporter item which be move food from farmer to consumer only in horizontal direction 
 * @author 180008901 
 *
 */

public class HorizontalTransporter extends Transporter {
	
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 * @param carryUnit How many nutrition it can carry
	 */
	
	private Image[] img;
	private final int NImage = 3;
	public Consumer consumer;
	public Farmer farmer;
	
	int nowX = 0;
	int nowY = 0;
	
	int s, t, w=0, speed = 1;
	
	public HorizontalTransporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y, carryUnit);
		img = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img[i] = (new ImageIcon("src/resources/swampy_idle_anim_f"+i+".png")).getImage();
		}
		
		Item l = this.findLeft();
		Item r = this.findRight();
		if (l == null || r == null) {
			return;
		}
		
		if (l.isConsumer() && r.isFarmer()) {
			this.consumer = (Consumer) l;
			this.farmer = (Farmer) r;
		} else if (r.isConsumer() && l.isFarmer()) {
			this.consumer = (Consumer) r;
			this.farmer = (Farmer) l;
		} else {
			return;
		}
		
		this.s = (this.yCoordinate - this.farmer.yCoordinate)*50;
		this.t = (this.yCoordinate - this.consumer.yCoordinate)*50;
		
		if (s < 0) {
			speed = -5;
		} else {
			speed = 5;
		}
	}

	/**
	 * find any item that is in west direction
	 * @return the first item that closest to transporter in west direction
	 */
	private Item findLeft() {
		for(int j = this.yCoordinate-1; j >= 0; j--) {
			Item thing = (Item)this.grid.getItem(this.xCoordinate, j);
			if (thing!= null) {
				return thing;
			}
		}
		return null;
	} 
	
	/**
	 * find any item that is in east direction
	 * @return the first item that closest to transporter in east direction
	 */
	private Item findRight() {
		for(int j = this.yCoordinate+1; j < this.grid.getWidth(); j++) {
			Item thing = (Item)this.grid.getItem(this.xCoordinate, j);
			if (thing!= null) {
				return thing;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		if (this.farmer == null || this.consumer == null) {
			return;
		}
		
		this.move(farmer, consumer);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HT";
	}

	
	public void draw(Board b, Graphics g, int step)  {
		
		if (this.nowY == this.s || this.nowY == this.t) {
			speed *= -1;
		}
		
		this.nowY += speed;
		b.drawMonster(g, img[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate, -12+this.nowX, -10+this.nowY);;
	}
}
