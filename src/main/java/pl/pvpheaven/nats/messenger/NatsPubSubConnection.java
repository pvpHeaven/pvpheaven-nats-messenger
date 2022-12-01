package pl.pvpheaven.nats.messenger;

import io.nats.client.Connection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.pvpheaven.nats.messenger.codec.NatsCodec;
import pl.pvpheaven.nats.messenger.handler.NatsHandler;

@RequiredArgsConstructor(access = AccessLevel.MODULE)
final class NatsPubSubConnection<V> implements NatsConnection<V> {

    private final NatsCodec<V> natsCodec;
    private final Connection natsConnection;

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