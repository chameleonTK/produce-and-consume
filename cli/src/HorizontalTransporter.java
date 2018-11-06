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
	public HorizontalTransporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y, carryUnit);
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
		Consumer consumer;
		Farmer farmer;
		
		Item l = this.findLeft();
		Item r = this.findRight();
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
		
		this.move(farmer, consumer);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HT";
	}
}
