
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
	public Beaver(Grid grid, int x, int y) {
		super(grid, x, y);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Beaver(%d)", this.grid.getStockAt(this.xCoordinate, this.yCoordinate));
	}
}
