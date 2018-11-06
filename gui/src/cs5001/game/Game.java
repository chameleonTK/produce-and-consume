package cs5001.game;

/**
 * The Game class allows running a game for a given number of time-steps starting from an initial grid
 */
public class Game {
    private AbstractGrid grid;

    public TimeStep time;
    public Game(AbstractGrid grid) {
        this.grid = grid;
        this.time = new TimeStep();
    }
    
    public int getWidth() {
    	return this.grid.getWidth();
    }
    
    public int getHeight() {
    	return this.grid.getHeight();
    }
    
    public AbstractGrid getGrid() {
    	return this.grid;
    }

    /**
     * Run the game with the given initial grid for a number of time-steps.
     *
     * @param timeSteps Number of time-steps to run the game for
     */
    public void run(int timeSteps) {
        TimeStep t = new TimeStep();

        for (int i = 0; i < timeSteps; i++) {
            grid.processItems(t);
            grid.display();

            System.out.println("Time step: " + t.getValue());
            System.out.println("Total production: " + grid.getTotalProduction());
            System.out.println("Total consumption: " + grid.getTotalConsumption());
            if (grid.getTotalProduction() > 0) {
                System.out.println("Score: " + String.format("%.2f", (double) grid.getTotalConsumption() / grid.getTotalProduction()));
            } else {
                System.out.println("Score: NA");
            }
            System.out.println();
            t.increment();
        }
    }
    
    public void runByStep() {
        grid.processItems(time);
        time.increment();
    }
}
