/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] pValues;
    private final int gridSize;
    private final int numOfTrials;
    private final double confidence95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Input is not valid");
        }

        gridSize = n;
        numOfTrials = trials;
        pValues = new double[trials];
        for (int i = 0; i < numOfTrials; i++) {
            Percolation percolationObject = new Percolation(gridSize);
            while (!percolationObject.percolates()) {
                openRandomSite(percolationObject);
            }

            double totalSites = gridSize * gridSize * 1.0;
            int openSites = percolationObject.numberOfOpenSites();
            pValues[i] = openSites / totalSites;
        }
    }

    private void openRandomSite(Percolation percolationObject) {
        boolean isOpen = true;
        int row = -1, col = -1;
        while (isOpen) {
            row = StdRandom.uniform(gridSize) + 1;
            col = StdRandom.uniform(gridSize) + 1;
            isOpen = percolationObject.isOpen(row, col);
        }

        percolationObject.open(row, col);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pValues);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pValues);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double pMean = this.mean();
        double lowerVal = confidence95 / Math.sqrt(numOfTrials);
        return pMean - lowerVal;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double pMean = this.mean();
        double lowerVal = confidence95 / Math.sqrt(numOfTrials);
        return pMean + lowerVal;
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int numOfTrials = Integer.parseInt(args[1]);

        PercolationStats percolationStatsObject = new PercolationStats(gridSize, numOfTrials);

        StdOut.printf("mean() = %f\n", percolationStatsObject.mean());
        StdOut.printf("stddev() = %f\n", percolationStatsObject.stddev());
        double cLow = percolationStatsObject.confidenceLo();
        double cHigh = percolationStatsObject.confidenceHi();
        StdOut.printf("95% confidence interval = %f, %f\n", cLow, cHigh);
    }
}
