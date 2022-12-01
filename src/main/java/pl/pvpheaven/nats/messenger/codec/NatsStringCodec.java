package pl.pvpheaven.nats.messenger.codec;

/**
 * @implNote Default, built-in string codec. Can be used as example.
 */
public final class NatsStringCodec implements NatsCodec<String> {

    @Override
    public String decodeValue(byte[] byteArray) {
        return new String(byteArray);
    }

    @Override
    public byte[] encodeValue(String value) {
        return value.getBytes();
    }

}