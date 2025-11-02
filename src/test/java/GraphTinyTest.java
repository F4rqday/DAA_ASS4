import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import graph.common.BasicMetrics;
import graph.common.Graph;
import graph.scc.TarjanSCC;


public class GraphTinyTest {
@Test void scc_twoCycles() {
List<int[]> edges = List.of(new int[]{0,1,1}, new int[]{1,0,1}, new int[]{2,3,1}, new int[]{3,2,1}, new int[]{1,2,1});
Graph g = new Graph(4, edges);
int[] comp = new TarjanSCC(g, new BasicMetrics()).run();
int C = Arrays.stream(comp).max().orElse(-1) + 1;
assertEquals(2, C);
}
}