package pl.pvpheaven.messenger.nats.handler;

/**
 * @param <V> Value type
 */
public interface NatsHandler<V> {

    /**
     * @param value Received message object.
     */
    void onMessageReceive(V value);

}