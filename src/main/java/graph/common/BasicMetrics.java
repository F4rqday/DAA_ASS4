package graph.common;


import java.util.concurrent.ConcurrentHashMap;


public class BasicMetrics implements Metrics {
private final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
private long t0, t1;

public void startTimer() { t0 = System.nanoTime(); }
public void stopTimer() { t1 = System.nanoTime(); }
public void inc(String key) { map.merge(key, 1L, Long::sum); }
public long get(String key) { return map.getOrDefault(key, 0L); }
public long elapsedMillis() { return (t1 - t0) / 1_000_000; }
}