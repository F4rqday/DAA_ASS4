package graph.dagsp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import graph.common.Graph;
import graph.common.Metrics;


public class DagShortestPath {
public static class Result { public long[] dist; public int[] parent; }


public static Result shortest(Graph dag, int src, List<Integer> topo, Metrics m) {
int n = dag.n; long INF = (long)1e18; Result r = new Result();
r.dist = new long[n]; Arrays.fill(r.dist, INF); r.parent = new int[n]; Arrays.fill(r.parent, -1);
r.dist[src] = 0;
for (int v : topo) {
if (r.dist[v] == INF) continue;
for (int[] e : dag.adj.get(v)) {
int to = e[0], w = e[1];
if (r.dist[to] > r.dist[v] + w) {
r.dist[to] = r.dist[v] + w; r.parent[to] = v; m.inc("dagRelaxations");
}
}
}
return r;
}


public static class Longest { public long[] best; public int[] parent; public int start, end; public long length; }


public static Longest longestPath(Graph dag, List<Integer> topo) {
int n = dag.n; Longest L = new Longest();
L.best = new long[n]; Arrays.fill(L.best, Long.MIN_VALUE/4); L.parent = new int[n]; Arrays.fill(L.parent, -1);

int[] indeg = new int[n]; for (int u=0; u<n; u++) for (int[] e : dag.adj.get(u)) indeg[e[0]]++;
for (int i=0;i<n;i++) if (indeg[i]==0) { L.best[i]=0; L.start=i; }
for (int v : topo) {
if (L.best[v] == Long.MIN_VALUE/4) continue;
for (int[] e : dag.adj.get(v)) {
int to = e[0], w = e[1];
if (L.best[to] < L.best[v] + w) { L.best[to] = L.best[v] + w; L.parent[to] = v; }
}
}

L.length = Long.MIN_VALUE; L.end = -1;
for (int i=0;i<n;i++) if (L.best[i] > L.length) { L.length = L.best[i]; L.end = i; }
return L;
}


public static List<Integer> reconstruct(int[] parent, int t) {
List<Integer> path = new ArrayList<>();
for (int v = t; v != -1; v = parent[v]) path.add(v);
Collections.reverse(path); return path;
}
}