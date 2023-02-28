package tests;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp {
    TestBoard board;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAdjacency() {   

        TestBoardCell cell = board.getCell(0,0);
        Set<TestBoardCell> testList = cell.getAdjList();
        Assert.assertTrue(testList.contains(board.getCell(1, 0)));
        Assert.assertTrue(testList.contains(board.getCell(0, 1)));
        Assert.assertTrue(testList.contains(board.getCell(0, 0)));
        Assert.assertTrue(testList.contains(board.getCell(3, 3)));
        Assert.assertTrue(testList.contains(board.getCell(2, 3)));
        Assert.assertTrue(testList.contains(board.getCell(3, 0)));
    }

    @Test 
    public void testTargetsNormal() {
    	TestBoardCell cell = board.getCell(0,0);
        board.calcTargets(cell, 3);
        Set<TestBoardCell> targets = board.getTargets();
        Assert.assertEquals(6, targets.size());
        Assert.assertTrue(targets.contains(board.getCell(3, 0)));
        Assert.assertTrue(targets.contains(board.getCell(2, 1)));
        Assert.assertTrue(targets.contains(board.getCell(0, 1)));
        Assert.assertTrue(targets.contains(board.getCell(1, 2)));
        Assert.assertTrue(targets.contains(board.getCell(0, 3)));
        Assert.assertTrue(targets.contains(board.getCell(1, 0)));
        }
    
    @Test
    public void testTargetsRoom() {
    	board.getCell(0, 2).setRoom(true);
    	board.getCell(1, 3).setRoom(true);
    	board.getCell(0, 3).setRoom(true);
    	TestBoardCell cell = board.getCell(0, 1);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	Assert.assertEquals(3, targets.size());
    	Assert.assertTrue(targets.contains(board.getCell(0, 2)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 3)));
    	Assert.assertTrue(targets.contains(board.getCell(0, 3)));
    }
    
    @Test
    public void testTargetsOccupied() {
    	board.getCell(0, 2).setOccupied(true);
    	board.getCell(1, 3).setOccupied(true);
    	board.getCell(0, 3).setOccupied(true);
    	TestBoardCell cell = board.getCell(0, 1);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	Assert.assertEquals(3, targets.size());
    	Assert.assertTrue(targets.contains(board.getCell(0, 2)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 3)));
    	Assert.assertTrue(targets.contains(board.getCell(0, 3)));
    }
    
    @Test
    public void testTargetsMixed() {
    	board.getCell(0, 2).setOccupied(true);
    	board.getCell(1, 3).setOccupied(true);
    	board.getCell(0, 3).setRoom(true);
    	board.getCell(1, 1).setRoom(true);
    	TestBoardCell cell = board.getCell(0, 1);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	Assert.assertEquals(3, targets.size());
    	Assert.assertTrue(targets.contains(board.getCell(0, 2)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 3)));
    	Assert.assertTrue(targets.contains(board.getCell(0, 3)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 1)));
    }

}