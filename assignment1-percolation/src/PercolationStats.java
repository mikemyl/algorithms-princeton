import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*----------------------------------------------------------------
 *  Author:        Mike Milonakis
 *  Written:       12/10/2016
 *  Last updated:  12/10/2016
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java Perolation.class < input.txt
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

public class PercolationStats {
    private double[] x;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    /**
     * Default Constructor
     * @param N The size of the N-by-N grid
     * @param T The number of the experiments
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        x = new double[T];
        runExperiments(N, T);
    }

    private void runExperiments(int n, int t) {
        for (int i = 0; i < t; i++) {
            Percolation grid = new Percolation(n);
            int openSites = 0;
            while (!grid.percolates()) {
                int y = StdRandom.uniform(1, n+1);
                int z = StdRandom.uniform(1, n+1);
                if (grid.isOpen(z, y))
                    continue;
                grid.open(z, y);
                openSites++;
            }
            x[i] = (double) openSites / (n*n);
            mean = StdStats.mean(x);
            stddev = StdStats.stddev(x);
            confidenceLo =  mean - (1.96 * stddev / (Math.sqrt(x.length)));
            confidenceHi = mean + (1.96 * stddev / (Math.sqrt(x.length)));
        }

    }

    /**
     * Computes the sample mean of percolation threshold
     * @return mean the mean
     */
    public double mean() {
        return mean;
    }

    /**
     * Computes the standard deviation of percolation threshhold
     *
     * @return stdev the sample standard deviation
     */
    public double stddev() {
        return stddev;
    }

    /**
     * Computes the low endpoint of 95% confidence interval
     *
     * @return the low endpoint
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * Computes the high endpoint of 95% confidence interval
     *
     * @return the high endpoint
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean\t\t\t= " + stats.mean());
        StdOut.println("stddev\t\t\t= " + stats.stddev());
        StdOut.println("95% confidence interval\t= " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }
}
