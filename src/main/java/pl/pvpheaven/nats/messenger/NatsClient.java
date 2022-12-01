package pl.pvpheaven.nats.messenger;

import pl.pvpheaven.nats.messenger.codec.NatsCodec;

public interface NatsClient {
    <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec);
}