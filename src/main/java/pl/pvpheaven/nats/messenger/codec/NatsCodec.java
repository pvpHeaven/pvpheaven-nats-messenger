package pl.pvpheaven.nats.messenger.codec;

public interface NatsCodec<V> {
    V decodeValue(byte[] byteArray);
    byte[] encodeValue(V value);
}