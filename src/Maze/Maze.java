package Maze;
import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	
    	// Return false if the current cell falls outside of the grid
    	if (x >= maze.getNCols() || y >= maze.getNRows()) { 
    		return false;
    } else if (x < 0 || y < 0) { 	
    	return false; 
    	// Return false if there is no possible path through that cell
    } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    	return false; 
    	// Return true if the current cell is at the exit of the maze
    } else if ((y == maze.getNRows() - 1) && (x == maze.getNCols() - 1)) { 
    	maze.recolor(x, y, PATH);
    	return true; 
    	// If an exit was not found, set the current coordinate point to path
    } else { maze.recolor(x, y, PATH);
    	
    // Return true if any neighboring coordinate is the exit point
    	if (findMazePath(x, y + 1)) {return true;} 
    	else if	(findMazePath(x, y - 1)){return true;} 
    	else if (findMazePath(x - 1, y)){return true;} 
    	else if (findMazePath(x + 1, y)){return true;} 
    	
    	
    	// Change the color to TEMPORARY if else
    	else { maze.recolor(x, y, TEMPORARY);
    	
    	return false; 
    	}
    }
} 
    
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	
    	// if current cell is out of the grid, the method breaks
    	if (x < 0 || y < 0) { 
    		return; 
    	}
    	if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1)
    		return; 
    	// method breaks if not color of cell is not red
    	if (!maze.getColor(x, y).equals(NON_BACKGROUND)){
    		return;
    	// push the current cell to trace if the exit point is reached, add result to stack
    	} else if (x == maze.getNCols() - 1 && y == maze.getNRows()- 1) {
    		
    		trace.push(new PairInt(x, y)); 
    		
    		ArrayList<PairInt> grid = new ArrayList<>(trace);
    		
    		result.add(grid); // add elements to the list 
    		
    		trace.pop(); // remove cell from stack after it is struck
    		
    		maze.recolor(x, y, NON_BACKGROUND);
    		
    		return;
    		
    	} else {
    		// else if the current cell does not reach exit point, continue with recursion algorithm
    		trace.push(new PairInt(x, y)); 
    		
    		// recolor the cell before recursion algorithm
    		maze.recolor(x, y, PATH); 
    		
    		//recursion algorithm is performed if the current grid point is not the exit point
    		findMazePathStackBased(x, y + 1, result, trace); findMazePathStackBased(x, y - 1, result, trace);
    		
    		findMazePathStackBased(x + 1, y, result, trace); findMazePathStackBased(x - 1, y, result, trace);

    		maze.recolor(x, y, NON_BACKGROUND);
    		
    		trace.pop();
    		
    		return;
    	}

    }
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);

    	return result;
    }
    
    // Calculate the shortest path by counting the number of elements in the list
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

    	int[] gridCounter;
    	int mazeInd = 0, pathMin, i;

    	// pathsPossible is a list of all possible paths taken
    	ArrayList<ArrayList<PairInt>> pathsPossible;
    	pathsPossible = findAllMazePaths(x, y);


    	gridCounter = new int[pathsPossible.size()];


    	for (i = 0; i < pathsPossible.size(); i++) {
    		
    		gridCounter[i] = pathsPossible.get(i).size();
    	}

    	pathMin = gridCounter[0]; // sets first minimum path to 0

    	// finds the smallest grid count
    	for (i = 1; i < gridCounter.length; i++) {
    		
    		if (gridCounter[i] < pathMin) {
    			
    			pathMin = gridCounter[i];
    			
    			mazeInd = i;
    		}
    }

    	return pathsPossible.get(mazeInd); // returns path with smallest grid count
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/


// Name: Ryan Knapp
// Date: 4/16/2021
// Assignment 4: Recursion - Maze




