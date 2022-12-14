package pl.pvpheaven.messenger.nats.codec;

/**
 * @param <V> Value type
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