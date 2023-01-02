import io.nats.client.Options;
import org.junit.jupiter.api.Test;
import pl.pvpheaven.messenger.nats.NatsClient;
import pl.pvpheaven.messenger.nats.NatsMessenger;
import pl.pvpheaven.messenger.nats.codec.NatsStringCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NatsMessengerTest {

    private static final Options natsOptions = new Options.Builder().server("nats://demo.nats.io:4222").build();
    private final NatsClient natsClient = NatsMessenger.create(natsOptions);
    private final NatsConnection<String> natsConnection = natsClient.createConnection(new NatsStringCodec());

    private final String uniqueChannel = UUID.randomUUID().toString();

    @Test
    void publishSubscribe() {
        assertNotNull(this.natsClient, "NatsClient == null.");
        assertNotNull(this.natsConnection, "NatsConnection == null.");

        final String message = "Test message";

        this.natsConnection.subscribe(this.uniqueChannel, (value) -> assertEquals(message, value, "Message != value"));
        this.natsConnection.publish(this.uniqueChannel, message);
        this.natsConnection.flush(Duration.ZERO);
    }

}