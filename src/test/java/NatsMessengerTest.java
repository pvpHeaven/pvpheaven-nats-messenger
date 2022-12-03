import io.nats.client.Connection;
import io.nats.client.Options;
import org.junit.jupiter.api.Test;
import pl.pvpheaven.nats.messenger.NatsClient;
import pl.pvpheaven.nats.messenger.NatsConnection;
import pl.pvpheaven.nats.messenger.NatsMessenger;
import pl.pvpheaven.nats.messenger.adapter.NatsAdapter;
import pl.pvpheaven.nats.messenger.codec.NatsStringCodec;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NatsMessengerTest {

    private static final Options natsOptions = new Options.Builder().server("nats://localhost:4222").build();
    private final NatsClient natsClient = NatsMessenger.create(natsOptions);
    private final NatsConnection<String> natsConnection = natsClient.createConnection(new NatsStringCodec());

    private final String uniqueChannel = UUID.randomUUID().toString();

    @Test
    void publishSubscribe() {
        assertNotNull(this.natsClient, "NatsClient == null.");
        assertNotNull(this.natsConnection, "NatsConnection == null.");

        final String message = "Test message";

        this.natsConnection.subscribe(this.uniqueChannel, new NatsAdapter<String>() {
            @Override
            public void onMessageReceive(String value) {
                assertEquals(message, value, "Message != value");
            }
        });
        this.natsConnection.publish(this.uniqueChannel, message);
    }

}