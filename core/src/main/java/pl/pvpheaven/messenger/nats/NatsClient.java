package pl.pvpheaven.messenger.nats;

import pl.pvpheaven.messenger.nats.codec.NatsCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;

public interface NatsClient {

    /**
     * @param natsCodec Codec that will encode and decode our messages.
     * @return new NatsConnection for value type.
     * @param <V> Connection value type.
     */
    <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec);

}