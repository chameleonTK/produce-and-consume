package cs5001.game;
/**
 * The Farmer class consists of all behavior of a farmer 
 * @author 180008901 
 *
 */
abstract public class Farmer extends Item {
	
	protected int nutritionPerUnit;
	protected int produceRate;
	protected int waitingTime;
	
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	public Farmer(Grid grid, int x, int y) {
		super(grid, x, y);
	}
	
	/**
	 * Check whether it is able to produce food in its location
	 * @return true if it is OK to produce food and false for otherwise
	 */
	abstract public boolean meetRequirement();
	
	/* (non-Javadoc)
	 * @see Item#isFarmer()
	 */
	@Override
	public boolean isFarmer() {
		return true;
	}
}
