package graph.common;


public interface Metrics {
void startTimer();
void stopTimer();
void inc(String key);
long get(String key);
long elapsedMillis();
}