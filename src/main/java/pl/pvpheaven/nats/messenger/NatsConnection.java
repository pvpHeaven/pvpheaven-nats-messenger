package pl.pvpheaven.nats.messenger;

import pl.pvpheaven.nats.messenger.handler.NatsHandler;

public interface NatsConnection<V> {
    void publish(String channel, V value);
    void subscribe(String channel, NatsHandler<V> natsHandler);
}