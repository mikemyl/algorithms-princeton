import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {

    private SearchNode finalNode;
    private Stack<Board> solutionStack;

    public Solver(Board board) {
        if (board == null)
            throw new NullPointerException();
        findSolution(board);
    }

    public Iterable<Board> solution() {
        if (finalNode == null)
            return null;
        if (solutionStack != null)
            return solutionStack;
        solutionStack = new Stack<>();
        for (SearchNode s = finalNode; s != null; s = s.previous) {
            solutionStack.push(s.board);
        }
        return solutionStack;
    }

    public boolean isSolvable() {
        return finalNode != null;
    }

    public int moves() {
        if (isSolvable())
            return finalNode.moves;
        return -1;
    }

    private void findSolution(Board board) {
        solve(board);
    }

    private void solve(Board board) {
        SearchNode twin = new SearchNode(board.twin(), null, 0);
        SearchNode orig = new SearchNode(board, null, 0);
        MinPQ<SearchNode> pq = new MinPQ<>(newManhattanComparator());
        MinPQ<SearchNode> twinPq = new MinPQ<>(newManhattanComparator());
        while (!orig.board.isGoal() && !twin.board.isGoal()) {
            for (Board b : orig.board.neighbors()) {
                if ((orig.previous == null) || (!b.equals(orig.previous.board)))
                    pq.insert(new SearchNode(b, orig, orig.moves + 1));
            }
            orig = pq.delMin();
            for (Board b : twin.board.neighbors()) {
                if ((twin.previous == null) || (!b.equals(twin.previous.board)))
                    twinPq.insert(new SearchNode(b, twin, twin.moves + 1));
            }
            twin = twinPq.delMin();
        }
        if (orig.board.isGoal())
            finalNode = orig;
    }

    private Comparator<SearchNode> newManhattanComparator() {
        return (s1, s2) -> (s1.manhattan + s1.moves) - (s2.manhattan + s2.moves);
    }

    private class SearchNode {
        Board board;
        SearchNode previous;
        int moves;
        int manhattan;

        SearchNode(Board board, SearchNode prev, int moves) {
            this.board = board;
            previous = prev;
            this.moves = moves;
            this.manhattan = this.board.manhattan();
        }
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
