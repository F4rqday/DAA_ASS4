import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import graph.common.BasicMetrics;
import graph.common.Graph;
import graph.dagsp.DagShortestPath;
import graph.topo.KahnTopologicalOrder;


public class DagSpTest {
@Test void shortest_simpleLine() {
Graph dag = new Graph(3, List.of(new int[]{0,1,2}, new int[]{1,2,3}));
var topo = KahnTopologicalOrder.order(dag, new BasicMetrics());
var r = DagShortestPath.shortest(dag, 0, topo, new BasicMetrics());
assertArrayEquals(new long[]{0,2,5}, r.dist);
}
}