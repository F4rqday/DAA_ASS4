# Assignment 4 — Smart City / Smart Campus Scheduling


## Build & Run Instructions

### 1️⃣ Prerequisites
Install **Java 17** and **Apache Maven 3.9**.

Check installation:
```bash
java -version
mvn -v
```

---

### 2️⃣ Build the project
```bash
mvn -DskipTests package
```
This compiles all Java files and creates the `target/` folder with `.class` files.

---

### 3️⃣ Run the program
```bash
mvn exec:java "-Dexec.args=data/small-02.json"
```
To run another dataset:
```bash
mvn -q exec:java "-Dexec.args=data/small-01.json"
mvn -q exec:java "-Dexec.args=data/small-02.json"
mvn -q exec:java "-Dexec.args=data/small-03.json"

mvn -q exec:java "-Dexec.args=data/medium-01.json"
mvn -q exec:java "-Dexec.args=data/medium-02.json"
mvn -q exec:java "-Dexec.args=data/medium-03.json"

mvn -q exec:java "-Dexec.args=data/large-01.json"
mvn -q exec:java "-Dexec.args=data/large-02.json"
mvn -q exec:java "-Dexec.args=data/large-03.json"
```

---

### 4️⃣ Run tests
```bash
mvn test
```
All tests should pass with `BUILD SUCCESS`.

---

## 📊 Example Output

```
SCC components (count=5):
 #0 size=1 -> [7]
 #1 size=1 -> [4]
 #2 size=1 -> [3]
 #3 size=3 -> [0, 1, 2]
 #4 size=2 -> [5, 6]

Condensation DAG: n=5, edges=4
Topo order of components: [3, 4, 2, 1, 0]

Shortest dist from component 3: [7, 6, 4, 0, ∞]
Critical path length: 7
Critical path comp nodes: [3, 2, 1, 0]

Metrics:
sccDfsVisited=8, sccEdges=9, topoPushes=5, topoPops=5, dagRelaxations=3, elapsedMs=12
```

---

## 📈 Performance Metrics

- Each JSON dataset contains between 6 and 50 nodes.
- Edge weights are randomly generated in range **1–3**.
- Datasets vary by density (sparse → dense) and SCC complexity.

| Dataset | SCCs | DAG nodes | Edges | DFS visited | SCC edges | Topo pushes | Topo pops | DAG relax | Time (ms) | Critical path |
|:--|--:|--:|--:|--:|--:|--:|--:|--:|--:|--:|
| small-01 | 4 | 4 | 3 | 6 | 6 | 4 | 4 | 3 | 16 | 6 |
| small-02 | 5 | 5 | 4 | 8 | 9 | 5 | 5 | 3 | 16 | 7 |
| small-03 | 7 | 7 | 6 | 7 | 6 | 7 | 7 | 6 | 15 | 12 |
| medium-01 | 8 | 8 | 6 | 12 | 12 | 8 | 8 | 5 | 16 | 5 |
| medium-02 | 13 | 13 | 11 | 15 | 14 | 13 | 13 | 4 | 17 | 7 |
| medium-03 | 15 | 15 | 12 | 18 | 17 | 15 | 15 | 5 | 18 | 10 |
| large-01 | 21 | 21 | 18 | 25 | 23 | 21 | 21 | 5 | 19 | 27 |
| large-02 | 38 | 38 | 16 | 40 | 19 | 38 | 38 | 4 | 22 | 12 |
| large-03 | 50 | 50 | 43 | 50 | 43 | 50 | 50 | 9 | 22 | 18 |

---

## 🧠 Algorithmic Analysis

| Stage | Algorithm | Complexity | Purpose |
|:--|:--|:--:|:--|
| **SCC** | Tarjan (DFS-based) | O(V + E) | Detects strongly connected components |
| **Condensation** | Edge compression | O(E) | Builds DAG of SCCs |
| **Topological Sort** | Kahn’s algorithm | O(V + E) | Derives valid execution order |
| **Shortest Path** | DP along topo order | O(V + E) | Finds earliest completion times |
| **Longest Path** | Max-DP along topo order | O(V + E) | Finds critical path duration |

---

## 🔍 Observations

- **SCC** efficiently compresses cyclic dependencies into single nodes.  
- **Condensation DAG** ensures acyclic structure suitable for further planning.  
- **Topological Sort** correctly determines the order of task execution.  
- **Shortest Path** gives minimum total duration between components.  
- **Longest (Critical) Path** shows overall time required for dependent tasks.  
- Performance scales linearly with the number of nodes and edges.
- Dense graphs produce slightly higher SCC counts and DFS operations, but linear time scaling is preserved.
- Sparse graphs show lower DAG relaxation counts due to fewer inter-component edges.


---

## 🧠Conclusion

The implementation exhibits near-linear scaling, validating theoretical complexity O(V + E).  
2. Tarjan’s DFS step is the heaviest but remains efficient for all tested graphs.  
3. The topological and DAG-SP phases add negligible overhead.  
4. Execution times under 25 ms confirm excellent practical efficiency and code optimization.

**Practical Recommendations:**
- Use SCC + Condensation for any cyclic dependency analysis (e.g., task scheduling, code dependencies).
- Use Topological Sort for acyclic planning and sequencing problems.
- Use DAG Shortest/Longest Paths for optimization of resource allocation or workflow timing.

