package pl.pvpheaven.nats.messenger;

import pl.pvpheaven.nats.messenger.codec.NatsCodec;

public interface NatsClient {

    /**
     * @param natsCodec Codec that will encode and decode our messages.
     * @return new NatsConnection for value type.
     * @param <V> Connection value type.
     */
    <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec);

}