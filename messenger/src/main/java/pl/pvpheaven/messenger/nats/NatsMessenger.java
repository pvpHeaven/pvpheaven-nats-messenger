package pl.pvpheaven.messenger.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import pl.pvpheaven.messenger.nats.codec.NatsCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NatsMessenger implements NatsClient {

    private static final Logger LOGGER = Logger.getLogger(NatsMessenger.class.getSimpleName());
    private final Options natsOptions;

    private NatsMessenger(Options natsOptions) {
        this.natsOptions = natsOptions;
    }

    public static NatsMessenger create(Options natsOptions) {
        return new NatsMessenger(natsOptions);
    }

    @Override
    public <V> NatsConnection<V> createConnection(NatsCodec<V> natsCodec) {
        Connection localNatsConnection = null;
        try {
            localNatsConnection = Nats.connect(natsOptions);
        } catch (final IOException | InterruptedException exception) {
            LOGGER.log(Level.SEVERE, "Something went wrong while connecting to NATS server!", exception);
        }
        return new NatsDurableConnection<>(natsCodec, localNatsConnection);
    }

}