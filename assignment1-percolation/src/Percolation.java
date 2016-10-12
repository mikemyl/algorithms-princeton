import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*----------------------------------------------------------------
        *  Author:        Mike Milonakis
        *  Written:       12/10/2016
        *  Last updated:  12/10/2016
        *
        *
        *  (Needs to have algs4.jar in classpath)
        *
        *  This is the first programming assignment of
        *  princeton - algorithms course in coursera
        *
        *----------------------------------------------------------------*/

/**
 * The PercolationStats  class estimates the percolation Threshold by
 * executing (T) experiments on N-by-N grid
 *
 * @author Mike Milonakis
 */
public class Percolation {

    private WeightedQuickUnionUF uf;
    private final boolean[] openSites;
    private final int gridSize;
    private final int topHelperSite;
    private final int bottomHelperSite;
    private final WeightedQuickUnionUF ufBackwash;
    private boolean isPercolated;

    /**
     *
     * @param size the Size of the Percolation Grid to be created
     */
    public Percolation(int size) {
        if (size <= 0)
            throw new IllegalArgumentException();
        gridSize = size;
        uf = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        ufBackwash = new WeightedQuickUnionUF(gridSize * gridSize + 1);
        topHelperSite = gridSize * gridSize;
        bottomHelperSite = gridSize * gridSize + 1;
        openTheAdditionalHelperSites();
        openSites = new boolean[gridSize * gridSize];
    }

    private void openTheAdditionalHelperSites() {
        for (int column = 1; column <= gridSize; column++) {
            uf.union(bottomHelperSite, convertTo1D(gridSize, column));
            uf.union(topHelperSite, convertTo1D(1, column));
            ufBackwash.union(topHelperSite, convertTo1D(1, column));
        }
    }

    /**
     * Opens the site in grid(row,column)
     * @param row the row of the site
     * @param column the column of the site
     */
    public void open(int row, int column) {
        validateIndices(row, column);
        openSites[(convertTo1D(row, column))] = true;
        unionWithNeighbors(row, column);
    }

    private void validateIndices(int row, int column) {
        if (((row <= 0) || (row > gridSize)) || ((column <= 0) || (column >
                gridSize)))
            throw new IndexOutOfBoundsException();
    }

    private void unionWithNeighbors(int row, int column) {
        if ((row - 1) > 0)
            connectWithNeighborIfOpen(convertTo1D(row - 1, column), convertTo1D(row, column));
        if ((column + 1) <= gridSize)
            connectWithNeighborIfOpen(convertTo1D(row, column + 1), convertTo1D(row, column));
        if ((row + 1) <= gridSize)
            connectWithNeighborIfOpen(convertTo1D(row + 1, column), convertTo1D(row, column));
        if ((column - 1) > 0)
            connectWithNeighborIfOpen(convertTo1D(row, column - 1), convertTo1D(row, column));
        if (percolates()) {
            uf = ufBackwash;
            isPercolated = true;
        }
    }

    private void connectWithNeighborIfOpen(int neighbor, int node) {
        if (isOpen(neighbor)) {
            uf.union(neighbor, node);
            ufBackwash.union(neighbor, node);
        }
    }

    private int convertTo1D(int row, int column) {
        return ((row - 1) * gridSize) + (column - 1);
    }

    /**
     * Checks if the site at grid(row,column) is open
     * @param row the row of the site
     * @param column the column of the site
     * @return true is open - false otherwise
     */
    public boolean isOpen(int row, int column) {
        validateIndices(row, column);
        return openSites[convertTo1D(row, column)];
    }

    private boolean isOpen(int index) {
        return openSites[index];
    }

    /**
     * Checks if the site (row,column) is full i.e connected to a top-row site
     * @param row the row of the site to be checked
     * @param column the column of the site to be checked
     * @return true if full - false otherwise
     */
    public boolean isFull(int row, int column) {
        validateIndices(row, column);
        return isOpen(row, column) &&
                ufBackwash.connected(convertTo1D(row, column), topHelperSite);
    }

    /**
     * Checks if any site at the bottom is connected to any site at the top
     * @return true if it grid percolates - false otherwise
     */
    public boolean percolates() {
        if (isPercolated)
            return true;
        if (gridSize == 1)
            return openSites[0];
        return uf.connected(topHelperSite, bottomHelperSite);
    }
}
