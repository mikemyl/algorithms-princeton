import edu.princeton.cs.algs4.StdIn;

/*----------------------------------------------------------------
        *  Author:        Mike Milonakis
        *  Written:       15/10/2016
        *  Last updated:  15/10/2016
        *
        *
        *
        *  This is the second programming assignment of
        *  princeton - algorithms course in coursera
        *
        *----------------------------------------------------------------*/

/**
 * The Subset class contains a main method that acts as test client for
 * randomized queue.
 *
 * @author Mike Milonakis
 */
public class Subset {

    /**
     * The main method takes as input an Integer k that corresponds to the strings
     * number. Then it expects N number of Strings from the StdIn, and after the
     * StdIn gets empty, it prints k of those Strings at random.
     *
     * @param args Takes 1 argument -- the number of the (random) Strings to be kept
     */
    public static void main(String[] args) {
        if (!(args.length == 1))
            throw new IllegalArgumentException("Usage: java Subset <number_of_strings>");
        int stringsNumber = Integer.valueOf(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            if (rq.size() >= stringsNumber)
                rq.dequeue();
            rq.enqueue(StdIn.readString());
        }
        rq.forEach(System.out::println);
    }
}
