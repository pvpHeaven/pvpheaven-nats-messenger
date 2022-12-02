package pl.pvpheaven.nats.messenger;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.pvpheaven.nats.messenger.codec.NatsCodec;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class NatsMessenger implements NatsClient {

    /* Utility */
    private static final Logger LOGGER = Logger.getLogger(NatsMessenger.class.getSimpleName());

    private final Connection natsConnection;

    public static NatsMessenger create(@NonNull Options natsOptions) {
        Connection localNatsConnection = null;
        try {
            localNatsConnection = Nats.connect(natsOptions);
        } catch (final IOException | InterruptedException exception) {
            LOGGER.log(Level.SEVERE, "Something went wrong while connecting to NATS server!", exception);
        }
        return new NatsMessenger(localNatsConnection);
    }

    @Override
    public <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec) {
        return new NatsPubSubConnection<>(natsCodec, this.natsConnection);
    }

}