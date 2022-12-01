package pl.pvpheaven.nats.messenger.handler;

public interface NatsHandler<V> {
    void onMessageReceive(V value);
}