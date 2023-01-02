package pl.pvpheaven.messenger.nats.codec;

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