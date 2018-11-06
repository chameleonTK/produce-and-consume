package cs5001.game;

/**
 * Grid stores all information in the game such as how much stock and what item in each space
 * @author 180008901 
 *
 */
public class Grid extends AbstractGrid{

	protected int width;
	protected int height;
	protected int nutrition;
	protected int consumption;
	
	/**
	 * Constructor
	 * @param height the height of the game's space 
	 * @param width the width of the game's space
	 */
	public Grid(int height, int width) {
		this.width = width;
		this.height = height;
		
		this.stock = new int[height][width];
		this.grid = new AbstractItem[height][width];
	}
	
	/* (non-Javadoc)
	 * @see AbstractGrid#getWidth()
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#getHeight()
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

	/**
	 * Check whether it is on the board 
	 * @param xCoordinate
	 * @param yCoordinate
	 * @return true if it is on the board and false for otherwise
	 */
	private boolean inBorder(int xCoordinate, int yCoordinate) {
		boolean flag = true;
	
		if (xCoordinate < 0 || xCoordinate >= this.height) {
			flag = false;
		}
		
		if (yCoordinate < 0 || yCoordinate >= this.width) {
			flag = false;
		}
		
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see AbstractGrid#registerItem(int, int, AbstractItem)
	 */
	@Override
	public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
		if (!this.inBorder(xCoordinate, yCoordinate)) {
			return;
		}
		
		this.grid[xCoordinate][yCoordinate] = item;
		
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#getItem(int, int)
	 */
	@Override
	public AbstractItem getItem(int xCoordinate, int yCoordinate) {
		if (!this.inBorder(xCoordinate, yCoordinate)) {
			return null;
		}
		
		return this.grid[xCoordinate][yCoordinate];
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#getStockAt(int, int)
	 */
	@Override
	public int getStockAt(int xCoordinate, int yCoordinate) {
		if (!this.inBorder(xCoordinate, yCoordinate)) {
			return 0;
		}
		
		return this.stock[xCoordinate][yCoordinate];
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#emptyStockAt(int, int)
	 */
	@Override
	public void emptyStockAt(int xCoordinate, int yCoordinate) {
		this.setStockAt(xCoordinate, yCoordinate, 0);
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#addToStockAt(int, int, int)
	 */
	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		this.setStockAt(xCoordinate, yCoordinate, this.getStockAt(xCoordinate, yCoordinate) + nutrition);
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#reduceStockAt(int, int, int)
	 */
	@Override
	public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		this.setStockAt(xCoordinate, yCoordinate, this.getStockAt(xCoordinate, yCoordinate) - nutrition);
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#setStockAt(int, int, int)
	 */
	@Override
	public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		if (!this.inBorder(xCoordinate, yCoordinate) || nutrition < 0) {
			return;
		}
		
		this.stock[xCoordinate][yCoordinate] = nutrition;
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#processItems(TimeStep)
	 */
	@Override
	public void processItems(TimeStep timeStep) {
		// Firstly all farmers do whatever they do
		for(int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++) {
				Item thing = (Item)this.getItem(i, j);
				if (thing!= null && thing.isFarmer()) {
					this.getItem(i, j).process(timeStep);
				}
			}
		}
		
		// Secondly all transporters move things
		for(int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++) {
				Item thing = (Item)this.getItem(i, j);
				if (thing!= null && thing.isTransporter()) {
					thing.process(timeStep);
				}
			}
		}
		
		
		// Thirdly all consumers eat
		for(int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++) {
				Item thing = (Item)this.getItem(i, j);
				if (thing!= null && thing.isConsumer()) {
					thing.process(timeStep);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#recordProduction(int)
	 */
	@Override
	public void recordProduction(int nutrition) {
		this.nutrition += nutrition;
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#getTotalProduction()
	 */
	@Override
	public int getTotalProduction() {
		return this.nutrition;
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#recordConsumption(int)
	 */
	@Override
	public void recordConsumption(int nutrition) {
		this.consumption += nutrition;
	}

	/* (non-Javadoc)
	 * @see AbstractGrid#getTotalConsumption()
	 */
	@Override
	public int getTotalConsumption() {
		return this.consumption;
	}

}
