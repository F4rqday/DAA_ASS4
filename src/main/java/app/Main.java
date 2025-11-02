package app;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graph.common.BasicMetrics;
import graph.common.Graph;
import graph.common.Loader;
import graph.scc.CondensationBuilder;
import graph.scc.TarjanSCC;
import graph.topo.KahnTopologicalOrder;


public class Main {
public static void main(String[] args) throws Exception {
String file = (args.length > 0) ? args[0] : "data/small-01.json";
if (!Files.exists(Path.of(file))) {
System.err.println("File not found: " + file); System.exit(1);
}
var L = Loader.fromJsonFile(file);
Graph g = L.g; int src = L.source;
var m = new BasicMetrics(); m.startTimer();



var tarjan = new TarjanSCC(g, m);
int[] comp = tarjan.run();
int C = Arrays.stream(comp).max().orElse(-1) + 1;



List<List<Integer>> groups = new ArrayList<>();
for (int i=0;i<C;i++) groups.add(new ArrayList<>());
for (int v=0; v<g.n; v++) groups.get(comp[v]).add(v);
System.out.println("SCC components (count="+C+"):");
for (int i=0;i<C;i++) System.out.println(" #"+i+" size="+groups.get(i).size()+" -> "+groups.get(i));




}