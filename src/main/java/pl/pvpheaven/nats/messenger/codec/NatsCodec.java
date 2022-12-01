package pl.pvpheaven.nats.messenger.codec;

/**
 * @param <V> Object type
 */
public interface NatsCodec<V> {
    /**
     * @param byteArray Encoded message.
     * @return Decoded value.
     */
    V decodeValue(byte[] byteArray);

    /**
     * @param value Decoded value.
     * @return Encoded message.
     */
    byte[] encodeValue(V value);
}