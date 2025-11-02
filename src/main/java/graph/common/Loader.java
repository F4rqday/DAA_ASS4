package graph.common;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Loader {
public static class Loaded {
public Graph g; public int source;
}


public static Loaded fromJsonFile(String path) throws IOException {
String s = Files.readString(Path.of(path));
JsonObject jo = JsonParser.parseString(s).getAsJsonObject();
int n = jo.get("n").getAsInt();
int source = jo.has("source") ? jo.get("source").getAsInt() : 0;
List<int[]> edges = new ArrayList<>();
for (JsonElement e : jo.getAsJsonArray("edges")) {
JsonObject o = e.getAsJsonObject();
int u = o.get("u").getAsInt();
int v = o.get("v").getAsInt();
int w = o.has("w") ? o.get("w").getAsInt() : 1;
edges.add(new int[]{u,v,w});
}
Loaded L = new Loaded();
L.g = new Graph(n, edges);
L.source = source;
return L;
}
}