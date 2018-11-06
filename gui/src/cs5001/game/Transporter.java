package cs5001.game;

/**
 * The Transporter class consists of all behavior of a transporter 
 * @author 180008901 
 *
 */
abstract public class Transporter extends Item {
	protected int carryUnit;

	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 * @param carryUnit How many nutrition can carry
	 */
	public Transporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y);
		this.carryUnit = carryUnit;
	}
	
	/**
	 * Reduce stock from one farmer to one consumer
	 * @param farmer target farmer
	 * @param consumer target consumer
	 */
	protected void move(Farmer farmer, Consumer consumer) {
		int nutrition = this.grid.getStockAt(farmer.xCoordinate, farmer.yCoordinate);
		if (nutrition > this.carryUnit) {
			nutrition = this.carryUnit;
		}
		this.grid.reduceStockAt(farmer.xCoordinate, farmer.yCoordinate, nutrition);
		this.grid.addToStockAt(consumer.xCoordinate, consumer.yCoordinate, nutrition);
	}
	
	/* (non-Javadoc)
	 * @see Item#isTransporter()
	 */
	@Override
	public boolean isTransporter() {
		return true;
	}	
}
