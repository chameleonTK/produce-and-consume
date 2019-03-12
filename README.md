# produce-and-consume
A simple single-player game with both CLI and GUI 
# Producers and Consumers
A simple single-player strategy game. It is played on a rectangular grid having a goal to maximize the score. Before the game
starts, the player places items on the cells of the grid. 

The game consist of 3 components: 
  * Farmers: producing food
  * Transporters: carrying the food to the consumer
  * Consumers: consuming the food

When the game starts, all items will do their job within thier own conditions.

At each time-step, the items will work in the following order: *all farmers, all transporters and all consumers starting from top-left, processing a row completely before moving to the next row*

Once the game is started, the game will runs automatically and produces a **final score** without the player interaction. The score is calculated by the number of products consumed divided by products produced.

It is an part of [CS5001](https://info.cs.st-andrews.ac.uk/student-handbook/modules/CS5001.html) at University of St. Andrews.


![](img/cli.png)
![](img/gui.png)

## Implementation

![](img/uml.png)

## How to play
A player write Test.java as an example below. Then, run and see your own score. Other examples are [TestCLI.java](src\core\TestCLI.java) and [TestGUI.java](src\core\TestGUI.java)

```javapackage core;

import core.gui.*;
import core.game.*;
import java.awt.EventQueue;

public class Test {
	public static void main(String[] args) {
		Grid grid = new Grid(10, 10);
        new CornFarmer(grid, 0, 0);
        new Beaver(grid, 0, 5);
        new Rabbit(grid, 3, 2);
        new RadishFarmer(grid, 6, 2);
        
        ///Transporter have to be the last object
        new HorizontalTransporter(grid, 0, 1, 10);
        new VerticalTransporter(grid, 5, 2, 10);
        Game game = new Game(grid);
        
        EventQueue.invokeLater(() -> {
            Application ex = new Application(game);
            ex.setVisible(true);
        });
    }
}
```


## Library
* Swing (Java)

## Resource
* So sorry I forgot. I promise to trace back to give a credit to the cute images I used.

### Todos
 * Make it moves precisely under each time-step.

## Author
[Pakawat Nakwijit](http://curve.in.th); An ordinary programmer who would like to share and challange himself. It is a part of my 2018 tasks to open source every projects in my old treasure chest with some good documentation. 

## License
This project is licensed under the terms of the MIT license.

