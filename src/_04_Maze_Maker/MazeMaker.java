package _04_Maze_Maker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	static int randy1;
	static int randy2;
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		 randy1 = new Random().nextInt(width);
		 randy2 = new Random().nextInt(height);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(randy1, randy2));
		int randy = new Random().nextInt(2);
		int which1 = new Random().nextInt(maze.getHeight());
		int which2 = new Random().nextInt(maze.getHeight());
		
			maze.getCell(0, which1).setWestWall(false);
		
		
			maze.getCell(maze.getWidth()-1, which2).setEastWall(false);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList <Cell> unvisited = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisited.size()>0) {
			//C1. select one at random.
			int randy = new Random().nextInt(unvisited.size());
			//C2. push it to the stack
		uncheckedCells.push(unvisited.get(randy));
			//C3. remove the wall between the two cells
		removeWalls(currentCell, unvisited.get(randy));
			//C4. make the new cell the current cell and mark it as visited
		currentCell = unvisited.get(randy);
		currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(unvisited.size()==0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size()>0) {
				// D1a. pop a cell from the stack
		currentCell=uncheckedCells.pop();
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX()+1==c2.getX()&&c1.getY()==c2.getY()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		else if(c1.getY()+1==c2.getY()&&c1.getX()==c2.getX()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
		else if(c1.getX()-1==c2.getX()&&c1.getY()==c2.getY()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		else if(c1.getY()-1==c2.getY()&&c1.getX()==c2.getX()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		System.out.println("neighbors cell "+c.getX()+", "+c.getY());
		ArrayList<Cell> end = new ArrayList<Cell>(); 
		if(c.getX()!=width-1&&maze.getCell(c.getX()+1, c.getY()).hasBeenVisited()==false) {
			end.add(maze.getCell(c.getX()+1, c.getY()));
		}
		if(c.getY()!=height-1&&maze.getCell(c.getX(), c.getY()+1).hasBeenVisited()==false) {
			end.add(maze.getCell(c.getX(), c.getY()+1));
		}
		if(c.getX()!=0&&maze.getCell(c.getX()-1, c.getY()).hasBeenVisited()==false) {
			end.add(maze.getCell(c.getX()-1, c.getY()));
		}
		if(c.getY()!=0&&maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()==false) {
			end.add(maze.getCell(c.getX(), c.getY()-1));
		}
		System.out.println(end.size());
		return end;
	}
}
