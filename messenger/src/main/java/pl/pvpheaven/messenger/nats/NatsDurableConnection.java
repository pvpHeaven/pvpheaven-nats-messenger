package pl.pvpheaven.messenger.nats;

import io.nats.client.Connection;
import pl.pvpheaven.messenger.nats.codec.NatsCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;
import pl.pvpheaven.messenger.nats.handler.NatsHandler;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

final class NatsDurableConnection<V> implements NatsConnection<V> {

    private static final Logger LOGGER = Logger.getLogger(NatsDurableConnection.class.getSimpleName());
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

    @Override
    public void flush(Duration timeout) {
        try {
            this.natsConnection.flush(timeout);
        } catch (final TimeoutException | InterruptedException exception) {
            LOGGER.log(Level.SEVERE, "Something went wrong while flushing current connection!", exception);
        }
    }

}