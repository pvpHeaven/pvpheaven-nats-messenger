package pl.pvpheaven.nats.messenger;

import pl.pvpheaven.nats.messenger.handler.NatsHandler;

/**
 * @param <V> Value type
 */
public interface NatsConnection<V> {

    /**
     * @param channel Channel that the message will be published on.
     * @param value Message that will be published on channel.
     */
    void publish(String channel, V value);

    /**
     * @param channel Channel that we will be listening for messages on.
     * @param natsHandler Message handler that will do actions on message received.
     */
    void subscribe(String channel, NatsHandler<V> natsHandler);

}