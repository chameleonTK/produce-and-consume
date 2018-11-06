

/**
 * The Item class implements share behaviors for all items 
 * @author 180008901 
 *
 */
abstract public class Item extends AbstractItem {

	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	public Item(AbstractGrid grid, int x, int y) {
		this.grid = grid;
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.grid.registerItem(x, y, this);
	}
	
	/**
	 * Check it is a farmer or not
	 * @return true if it is a farmer and false otherwise
	 */
	public boolean isFarmer() {
		return false;
	}
	
	/**
	 * Check it is a consumer or not
	 * @return true if it is a consumer and false otherwise
	 */
	public boolean isConsumer() {
		return false;
	}
	
	/**
	 * Check it is a transporter or not
	 * @return true if it is a transporter and false otherwise
	 */
	public boolean isTransporter() {
		return false;
	}
	
	/**
	 * Calculate distance from itself to a particular item
	 * @param n target item
	 * @return distance from itself to @param n
	 */
	public double getDistanceTo(Item n) {
		if (n == null) {
			return Double.MAX_VALUE;
		}
		
		return Math.abs(n.xCoordinate - this.xCoordinate) + Math.abs(n.yCoordinate - this.yCoordinate); 
	}

	/* (non-Javadoc)
	 * @see AbstractItem#getStock()
	 */
	@Override
	protected int getStock() {
		return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
	}

	/* (non-Javadoc)
	 * @see AbstractItem#addToStock(int)
	 */
	@Override
	protected void addToStock(int nutrition) {
		this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, nutrition);
	}

	/* (non-Javadoc)
	 * @see AbstractItem#reduceStock(int)
	 */
	@Override
	protected void reduceStock(int nutrition) {
		this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
	}

}
