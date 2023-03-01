package experiment;
import java.util.*;

public class TestBoard {
	private TestBoardCell[][] grid; // grid of cells
	private Set<TestBoardCell> targets; // targets
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;

    public TestBoard() { // empty constructor as prescribed
    	grid = new TestBoardCell[ROWS][COLS]; // for testing
    	for (int i = 0; i < ROWS; i++) {
    		for (int j = 0; j < COLS; j++) {
    			TestBoardCell testCell = new TestBoardCell(i, j);
    			grid[i][j] = testCell; // fill grid with cells
    		}
    	}
    	for (int i = 0; i < ROWS; i++) {
    		for (int j = 0; j < COLS; j++) {
    			if (i != 0) {
    				grid[i][j].addAdjacency(grid[i - 1][j]);
    			}
    			if (i != 3) {
    				grid[i][j].addAdjacency(grid[i + 1][j]);
    			}
    			if (j != 0) {
    				grid[i][j].addAdjacency(grid[i][j - 1]);
    			}
    			if (j != 3) {
    				grid[i][j].addAdjacency(grid[i][j + 1]);
    			}
    		}
    	}
    	targets = new HashSet<TestBoardCell>();
    	visited = new HashSet<TestBoardCell>();
    }

    public void calcTargets(TestBoardCell startCell, int pathLength) { // function stub
    	visited.add(startCell);
    	for (TestBoardCell adjCell : startCell.getAdjList()) {
    		findAllTargets(adjCell, pathLength);
    	}
    }
    
    private void findAllTargets(TestBoardCell startCell, int pathLength) {
    	for (TestBoardCell adjCell : startCell.getAdjList()) {
    		if (visited.contains(adjCell) == false) {
    			visited.add(adjCell);
    			if (pathLength == 1) {
    				targets.add(adjCell);
    			} else {
    				findAllTargets(adjCell, pathLength - 1);
    			}
    			visited.remove(adjCell);
    		}
    	}
    	//visited.remove(startCell);
    }

    public TestBoardCell getCell(int row, int col) {
        return grid[row][col]; // returns the cell at the given parameters
    }

    public Set<TestBoardCell> getTargets() {
        return targets; // gets targets last created by calcTargets()
    }

	@Override
	public String toString() {
		for (TestBoardCell currentCell : targets) {
			System.out.println(currentCell);
		}
		return "";
	}
    
    
}
