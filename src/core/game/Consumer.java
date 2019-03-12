package core.game;
/**
 * The Consumer class consists of all behavior of a consumer 
 * @author 180008901 
 *
 */
abstract public class Consumer extends Item {
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	public Consumer(Grid grid, int x, int y) {
		super(grid, x, y);
	}
	
	/* (non-Javadoc)
	 * @see Item#isConsumer()
	 */
	@Override
	public boolean isConsumer() {
		return true;
	}
}
