package core.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.gui.Board;

/**
 * The VerticalTransporter class behaves as a transporter item which be move food from farmer to consumer only in vertical direction 
 * @author 180008901 
 *
 */

public class VerticalTransporter extends Transporter {
	
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
	public VerticalTransporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y, carryUnit);
		
		img = new Image[NImage];
		for(int i=0; i< NImage; i++) {
			img[i] = (new ImageIcon("src/resources/skelet_idle_anim_f"+i+".png")).getImage();
		}
		
		Item l = this.findTop();
		Item r = this.findBottom();
		if (l == null || r == null) {
			return;
		}
		
		if (l.isConsumer() && r.isFarmer()) {
			consumer = (Consumer) l;
			farmer = (Farmer) r;
		} else if (r.isConsumer() && l.isFarmer()) {
			consumer = (Consumer) r;
			farmer = (Farmer) l;
		} else {
			return;
		}
		
		this.s = (this.xCoordinate - this.farmer.xCoordinate)*50;
		this.t = (this.xCoordinate - this.consumer.xCoordinate)*50;
		
		if (s < 0) {
			speed = -5;
		} else {
			speed = 5;
		}
	}

	/**
	 * find any item that is in north direction
	 * @return the first item that closest to transporter in north direction
	 */
	private Item findTop() {
		for(int i = this.xCoordinate-1; i >= 0; i--) {
			Item thing = (Item)this.grid.getItem(i, this.yCoordinate);
			if (thing!= null) {
				return thing;
			}
		}
		return null;
	} 
	
	/**
	 * find any item that is in south direction
	 * @return the first item that closest to transporter in south direction
	 */
	private Item findBottom() {
		for(int i = this.xCoordinate+1; i < this.grid.getHeight(); i++) {
			Item thing = (Item)this.grid.getItem(i, this.yCoordinate);
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
		return "VT";
	}
	
	public void draw(Board b, Graphics g, int step)  {
		if (this.nowX == this.s || this.nowX == this.t) {
			speed *= -1;
		}
		
		this.nowX += speed;
		b.drawMonster(g, img[step % NImage], (int)this.xCoordinate, (int)this.yCoordinate, -12+this.nowX, -10+this.nowY);;
	}
}
