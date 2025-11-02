package graph.common;


import java.util.*;


public class Graph {
public final int n;
public final List<int[]> edges; 
public final List<List<int[]>> adj; 


public Graph(int n, List<int[]> edges) {
this.n = n;
this.edges = edges;
this.adj = new ArrayList<>(n);
for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
for (int[] e : edges) adj.get(e[0]).add(new int[]{e[1], e[2]});
}


public Graph transpose() {
List<int[]> rev = new ArrayList<>();
for (int[] e : edges) rev.add(new int[]{e[1], e[0], e[2]});
return new Graph(n, rev);
}
}