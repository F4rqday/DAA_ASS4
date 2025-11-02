package graph.topo;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import graph.common.Graph;
import graph.common.Metrics;


public class KahnTopologicalOrder {
public static List<Integer> order(Graph dag, Metrics m) {
int n = dag.n; int[] indeg = new int[n];
for (int u = 0; u < n; u++) for (int[] e : dag.adj.get(u)) indeg[e[0]]++;
Deque<Integer> q = new ArrayDeque<>();
for (int i = 0; i < n; i++) if (indeg[i] == 0) { q.add(i); m.inc("topoPushes"); }
List<Integer> order = new ArrayList<>(n);
while (!q.isEmpty()) {
int v = q.remove(); m.inc("topoPops"); order.add(v);
for (int[] e : dag.adj.get(v)) { int to = e[0]; if (--indeg[to] == 0) { q.add(to); m.inc("topoPushes"); } }
}
if (order.size() != n) throw new IllegalStateException("Graph is not a DAG");
return order;
}
}