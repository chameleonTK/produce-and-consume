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
	public VerticalTransporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y, carryUnit);
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
		Consumer consumer;
		Farmer farmer;
		
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
		
		this.move(farmer, consumer);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VT";
	}
}
