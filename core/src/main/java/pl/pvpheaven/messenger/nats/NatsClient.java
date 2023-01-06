package pl.pvpheaven.messenger.nats;

import pl.pvpheaven.messenger.nats.codec.NatsCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;

public interface NatsClient {

    /**
     * @param <V> Connection value type.
     * @param natsCodec Codec that will encode and decode our messages.
     * @return new NatsConnection for value type.
     */
    <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec);

}