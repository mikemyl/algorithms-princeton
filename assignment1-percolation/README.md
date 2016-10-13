# Programming Assignment 1 - Percolation

This first assignment's specification can be found [here](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html).

We basically need to utilize the [union find data structure](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) in order to decide whether the modeled system percolates or not.

### Challenges
* Map the percolation model to the UF data structure (map 2D -> 1D) while avoiding quadratic complexity.
* Avoid the "backwash" bug (that is caused when adding the additional "virtual" nodes to the percolation grid).

Deliverables:

* [Percolation.java](src/Percolation.java)
* [PercolationStats.java](src/PercolationStats.java)
