package graph.scc;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import graph.common.Graph;
import graph.common.Metrics;


public class TarjanSCC {
private final Graph g; private final Metrics m;
private int timer = 0, compCnt = 0;
private int[] tin, low, comp; private boolean[] inSt;
private Deque<Integer> st = new ArrayDeque<>();


public TarjanSCC(Graph g, Metrics m) { this.g = g; this.m = m; }


public int[] run() {
int n = g.n; tin = new int[n]; low = new int[n]; comp = new int[n];
Arrays.fill(tin, -1); Arrays.fill(comp, -1); inSt = new boolean[n];
for (int v = 0; v < n; v++) if (tin[v] == -1) dfs(v);
return comp; 
}


private void dfs(int v) {
m.inc("sccDfsVisited");
tin[v] = low[v] = timer++; st.push(v); inSt[v] = true;
for (int[] e : g.adj.get(v)) {
m.inc("sccEdges");
int to = e[0];
if (tin[to] == -1) { dfs(to); low[v] = Math.min(low[v], low[to]); }
else if (inSt[to]) low[v] = Math.min(low[v], tin[to]);
}
if (low[v] == tin[v]) {
while (true) {
int u = st.pop(); inSt[u] = false; comp[u] = compCnt;
if (u == v) break;
}
compCnt++;
}
}
}