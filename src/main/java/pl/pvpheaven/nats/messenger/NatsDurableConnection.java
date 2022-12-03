package pl.pvpheaven.nats.messenger;

import io.nats.client.Connection;
import pl.pvpheaven.nats.messenger.codec.NatsCodec;
import pl.pvpheaven.nats.messenger.handler.NatsHandler;

final class NatsDurableConnection<V> implements NatsConnection<V> {

    private final NatsCodec<V> natsCodec;
    private final Connection natsConnection;

    NatsDurableConnection(NatsCodec<V> natsCodec, Connection natsConnection) {
        this.natsCodec = natsCodec;
        this.natsConnection = natsConnection;
    }

    @Override
    public void publish(String channel, V value) {
        this.natsConnection.publish(channel, this.natsCodec.encodeValue(value));
    }

    @Override
    public void subscribe(String channel, NatsHandler<V> natsHandler) {
        this.natsConnection.createDispatcher(message -> natsHandler.onMessageReceive(this.natsCodec.decodeValue(message.getData())))
                .subscribe(channel);
    }

}