package graph.scc;


import java.util.ArrayList;
import java.util.List;

import graph.common.Graph;


public class CondensationBuilder {
public static Graph build(Graph g, int[] comp) {
int max = -1; for (int x : comp) max = Math.max(max, x); int C = max + 1;
boolean[][] seen = new boolean[C][C];
List<int[]> edges = new ArrayList<>();
for (int[] e : g.edges) {
int a = comp[e[0]], b = comp[e[1]]; int w = e[2];
if (a != b && !seen[a][b]) { edges.add(new int[]{a,b,w}); seen[a][b] = true; }
}
return new Graph(C, edges);
}
}