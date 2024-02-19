package graphics;

import graphics.FractalTreePanelExtension;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FractalTreePanelExtensionTest {

    FractalTreePanelExtension testBranch = new FractalTreePanelExtension();

    @Test
    public void testSetRandomThicknessRange() {
        testBranch.setRandomThicknessRange(10,20);
        assertTrue(testBranch.thickness>=10 && testBranch.thickness <= 20);

    }

    @Test
    public void testSetTreeDepth() {
        testBranch.setTreeDepth(10);
        assertEquals(10, testBranch.treeDepth);
    }

    @Test
    public void testEnableRandomBranchColors() {
        testBranch.enableRandomBranchColors(true);
        assertEquals(true, testBranch.randomColors);
        testBranch.enableRandomBranchColors(false);
        assertEquals(false, testBranch.randomColors);
    }

    @Test
    public void testSetRandomAngleRange() {
        testBranch.setRandomAngleRange(10,20);
        assertTrue(testBranch.branchAngle>=10 && testBranch.branchAngle <= 20);
    }

    @Test
    public void testSetInitialAngle() {
        testBranch.setInitialAngle(10);
        assertEquals(10,testBranch.angle);
    }

    @Test
    public void testSetInitialLength() {
        testBranch.setInitialLength(10);
        assertEquals(10,testBranch.length);
    }

    @Test
    public void testSetX0Y0() {
        testBranch.setX0Y0(10,10);
        assertEquals(10,testBranch.x0);
        assertEquals(10,testBranch.y0);
    }

}