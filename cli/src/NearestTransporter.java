import java.util.LinkedList;
import java.util.List;

/**
 * The NearestTransporter class behaves as a transporter item which be move food from the nearest farmer to the nearest consumer 
 * @author 180008901 
 *
 */

public class NearestTransporter extends Transporter {

	/**
	 * Constructor
	 * @param grid Representation all information in grid
	 * @param x x-coordination which it lies on
	 * @param y y-coordination which it lies on
	 * @param carryUnit How many nutrition it can carry
	 */
	public NearestTransporter(Grid grid, int x, int y, int carryUnit) {
		super(grid, x, y, carryUnit);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Find the closest item from given list 
	 * @param lst list of items 
	 * @return one closest item
	 */
	private Item findClosestItemFromList(List<Item> lst) {
		double distance = Double.MAX_VALUE;
		Item selected = null;
		for(Item consumer: lst) {
			if (distance > consumer.getDistanceTo(this)) {
				distance = consumer.getDistanceTo(this);
				selected = consumer;
			}
		}
		
		return selected;
	}
	
	/**
	 * Check whether it is only have that distance; no more others that have same distance
	 * It can be implemented in the fame function with  findClosestItemFromList(...) but I decided to separate them because of it has separate logic
	 * It could be reused in other cases.
	 * @param lst list of item
	 * @param selected target item
	 * @return true if the target item is the only one which have same distance with itself
	 */
	private boolean onlyClosestItem(List<Item> lst, Item selected) {
		//Check it is the only one closest item.
		double d = selected.getDistanceTo(this);
		for(Item o: lst) {
			if (o != selected && d == o.getDistanceTo(this)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Find the closest consumer in any direction and it has to be the only one which has that distance
	 * @return the closest consumer; if no consumer meets the requirements it will return null
	 */
	private Item findConsumer() {
		//Find possible items
		List<Item> possibleConsumers = new LinkedList<Item>();
		for(int i=0; i< this.grid.getHeight(); i++) {
			for(int j=0; j< this.grid.getWidth(); j++) {
				Item thing = (Item)this.grid.getItem(i, j);
				if (thing!= null && thing.isConsumer()) {
					possibleConsumers.add(thing);
				}
			}
		}
		
		if (possibleConsumers.isEmpty()) {
			return null;
		}
		
		
		Item selected = this.findClosestItemFromList(possibleConsumers);
		if(this.onlyClosestItem(possibleConsumers, selected)) {
			return selected;
		}
		return null;
	} 
	
	/**
	 * Find the closest farmer in any direction and it has to be the only one which has that distance
	 * @return the closest farmer; if no farmer meets the requirements it will return null
	 */
	private Item findFarmer() {
		//Find possible items
		List<Item> possibleFarmers = new LinkedList<Item>();
		for(int i=0; i< this.grid.getHeight(); i++) {
			for(int j=0; j< this.grid.getWidth(); j++) {
				Item thing = (Item)this.grid.getItem(i, j);
				if (thing!= null && thing.isFarmer()) {
					possibleFarmers.add(thing);
				}
			}
		}
		
		if (possibleFarmers.isEmpty()) {
			return null;
		}
		
		
		Item selected = this.findClosestItemFromList(possibleFarmers);
		if(this.onlyClosestItem(possibleFarmers, selected)) {
			return selected;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see AbstractItem#process(TimeStep)
	 */
	@Override
	public void process(TimeStep timeStep) {
		Consumer consumer = (Consumer)this.findConsumer();
		Farmer farmer = (Farmer)this.findFarmer();
		
		if (farmer == null || consumer == null) {
			return;
		}
		
		this.move(farmer, consumer);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NT";
	}

}
