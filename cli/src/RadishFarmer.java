import java.util.LinkedList;
import java.util.List;

/**
 * The RadishFarmer class behaves as a farmer item which be able to produce 30 units in every 3 steps (with some condition)
 * @author 180008901
 *
 */
public class RadishFarmer extends Farmer {

	protected int nutritionPerUnit = 1;
	protected int produceRate = 10;
	protected int waitingTime = 3;
	
	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 */
	public RadishFarmer(Grid grid, int x, int y) {
		super(grid, x, y);
	}
	
	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		if ((timeStep.getValue()) % this.waitingTime != 0 ) {
			return;
		}
	
		if (!this.meetRequirement()) {
			return;
		}
		
		this.addToStock(this.produceRate * this.nutritionPerUnit);
		this.grid.recordProduction(this.produceRate * this.nutritionPerUnit);
	}
	

	/* (non-Javadoc)
	 * @see Farmer#meetRequirement()
	 */
	public boolean meetRequirement() {
		List<Item> surroungings = new LinkedList<Item>(); 
		surroungings.add((Item)this.grid.getItem(this.xCoordinate, this.yCoordinate + 1));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate, this.yCoordinate - 1));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate + 1, this.yCoordinate));
		surroungings.add((Item)this.grid.getItem(this.xCoordinate - 1, this.yCoordinate));
		
		for (int i=0; i< surroungings.size(); i++) {
			Item thing = surroungings.get(i);
			if (thing != null && thing.isFarmer()) {
				return false;
			}
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Radish(%d)", this.grid.getStockAt(this.xCoordinate, this.yCoordinate));
	}
}
