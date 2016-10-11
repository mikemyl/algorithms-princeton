import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Mike Milonakis on 11/10/2016.
 */
public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final boolean[] openSites;
    private final int gridSize;
    private final int topHelperSite;
    private final int bottomHelperSite;

    public Percolation(int size) {
        if (size <= 0)
            throw new IllegalArgumentException();
        gridSize = size;
        uf = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        topHelperSite = gridSize * gridSize + 1;
        bottomHelperSite = gridSize * gridSize;
        openTheAdditionalHelperSites();
        openSites = new boolean[gridSize * gridSize];
    }


    public void open(int row, int column) {
        validateIndices(row, column);
        openSites[(convertTo1D(row, column))] = true;
        unionWithNeighbors(row, column);
    }


    public boolean isOpen(int row, int column) {
        validateIndices(row, column);
        return openSites[convertTo1D(row, column)];
    }


    private void unionWithNeighbors(int row, int column) {
        if ((row - 1) > 0) {
            int upper = convertTo1D(row - 1, column);
            if (isOpen(upper))
                uf.connected(convertTo1D(row, column), upper);
        }
        if ((column + 1) <= gridSize) {
            int right = convertTo1D(row, column + 1);
            if (isOpen(right))
                uf.connected(convertTo1D(row, column), right);
        }
        if ((row + 1) <= gridSize) {
            int down = convertTo1D(row + 1, column);
            if (isOpen(down))
                uf.connected(convertTo1D(row, column), down);
        }
        if ((column - 1) > 0) {
            int left = convertTo1D(row, column - 1);
            if (isOpen(left))
                uf.connected(convertTo1D(row, column), left);
        }
    }

    private boolean isOpen(int index) {
        return openSites[index];
    }

    private void validateIndices(int row, int column) {
        if (((row <= 0) || (row > gridSize)) || ((column <= 0) || (column >
                gridSize)))
            throw new IndexOutOfBoundsException();
    }

    private int convertTo1D(int row, int column) {
        return ((row - 1) * gridSize) + (column - 1);
    }

    private void openTheAdditionalHelperSites() {
        for (int column = 1; column <= gridSize; column++) {
            uf.union(bottomHelperSite, convertTo1D(1, column));
            uf.union(topHelperSite, convertTo1D(gridSize, column));
        }
    }

    public boolean isFull(int row, int column) {
        validateIndices(row, column);
        return isOpen(row, column) &&
                uf.connected(convertTo1D(row, column), topHelperSite);
    }

    public boolean percolates() {
        return uf.connected(topHelperSite, bottomHelperSite);
    }
}
