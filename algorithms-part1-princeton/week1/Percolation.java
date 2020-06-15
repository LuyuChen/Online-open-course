/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int gridSize;
    private final WeightedQuickUnionUF quickUnion;
    private int countOfOpenSites;
    private final int virtualTopRoot;
    private final int virtualBottomRoot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is not valid");
        }
        grid = new boolean[n][n];
        gridSize = n;
        quickUnion = new WeightedQuickUnionUF(n * n + 2);
        countOfOpenSites = 0;
        virtualTopRoot = n * n;
        virtualBottomRoot = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("Input is not valid");
        }

        if (isOpen(row, col)) {
            return;
        }

        grid[row - 1][col - 1] = true;
        int curSizeIndex = get1dIndex(row, col);
        if (row == 1) {
            quickUnion.union(virtualTopRoot, curSizeIndex);
        }

        if (row == gridSize) {
            quickUnion.union(virtualBottomRoot, curSizeIndex);
        }

        if (isIndexValid(row - 1, col) && isOpen(row - 1, col)) {
            int neighborSiteIndex = get1dIndex(row - 1, col);
            quickUnion.union(curSizeIndex, neighborSiteIndex);
        }

        if (isIndexValid(row + 1, col) && isOpen(row + 1, col)) {
            int neighborSiteIndex = get1dIndex(row + 1, col);
            quickUnion.union(curSizeIndex, neighborSiteIndex);
        }

        if (isIndexValid(row, col - 1) && isOpen(row, col - 1)) {
            int neighborSiteIndex = get1dIndex(row, col - 1);
            quickUnion.union(curSizeIndex, neighborSiteIndex);
        }

        if (isIndexValid(row, col + 1) && isOpen(row, col + 1)) {
            int neighborSiteIndex = get1dIndex(row, col + 1);
            quickUnion.union(curSizeIndex, neighborSiteIndex);
        }

        countOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("Input is not valid");
        }

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("Input is not valid");
        }

        int curSiteIndex = get1dIndex(row, col);

        return quickUnion.find(curSiteIndex) == quickUnion.find(virtualTopRoot);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUnion.find(virtualBottomRoot) == quickUnion.find(virtualTopRoot);
    }

    private int get1dIndex(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("Input is not valid");
        }

        return (row - 1) * gridSize + col - 1;
    }

    private boolean isIndexValid(int row, int col) {
        return row > 0 && row <= gridSize && col > 0 && col <= gridSize;
    }
}
