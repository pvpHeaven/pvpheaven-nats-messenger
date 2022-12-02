package pl.pvpheaven.nats.messenger.handler;

/**
 * @param <V> Value type
 */
public interface NatsHandler<V> {

    /**
     * @param value Received message object.
     */
    void onMessageReceive(V value);

}