import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Mike Milonakis on 11/10/2016.
 */
public class PercolationTest {

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_NegativeGridValue_ExceptionThrown() {
        new Percolation(-5);
    }

    @Test
    public void Create_Grid_AllSitesBlocked() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; i <= 5; i++) {
                Assert.assertFalse(percolation.isOpen(i, j));
            }
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IsOpen_IllegalValue_ExceptionThrown() {
        percolation.isOpen(0, 0);
    }

    @Test
    public void Open_regularSite_BecomesOpen() {
        percolation.open(1,1);

        Assert.assertTrue(percolation.isOpen(1,1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IsFull_IllegalValue_Exceptionthrown() {
        percolation.isFull(6, 6);
    }

    @Test
    public void IsFull_SiteInBottomRowIsOpen_ReturnsTrue() {
        percolation.open(5, 5);

        Assert.assertTrue(percolation.isFull(5, 5));
        Assert.assertFalse(percolation.isFull(5, 4));
    }

    @Test
    public void Percolates_NoBottomSiteFull_ReturnsFalse() {
        percolation.open(5, 5);
        percolation.open(4, 5);
        percolation.open(3, 5);
        percolation.open(2, 5);

        Assert.assertFalse(percolation.percolates());
        Assert.assertFalse(percolation.isFull(1, 5));
    }

    @Test
    public void Percolates_BottomSiteFull_ReturnsTrue() {
        percolation.open(5, 5);
        percolation.open(4, 5);
        percolation.open(3, 5);
        percolation.open(2, 5);
        percolation.open(1, 5);

        Assert.assertTrue(percolation.percolates());
    }
}