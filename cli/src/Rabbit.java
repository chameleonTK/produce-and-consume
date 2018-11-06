
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
	public Rabbit(Grid grid, int x, int y) {
		super(grid, x, y);
		// TODO Auto-generated constructor stub
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
}
