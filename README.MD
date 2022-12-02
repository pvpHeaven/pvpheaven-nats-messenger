# 🌠 NATS Messenger (Work In Progress)
Library made for needs of our server and published *for sake of humanity*.

It extends `pub/sub` from `NATS.io` with lettuce-like (redis library) codecs and *abstraction* capabilities.

---
## 🌠 NATS Messenger :: Usage

> Creating new client and connection:
```java
final Options options = new Options.Builder()
        .server("nats://demo.nats.io:4222")
        .build();
final NatsClient natsClient = NatsMessenger.create(options);
final NatsConnection<String> natsConnection = natsClient.createConnection(new NatsStringCodec());
```

> Subscribing to a channel and publishing messages:
```java
natsConnection.subscribe("test-channel", new NatsTestListener());
/* Or */
natsConnection.subscribe("test-channel", value -> System.out.println(value));
natsConnection.publish("test-channel", "Test message!");
```

> Example message listener:
```java
class NatsMessageListener implements NatsHandler<String> {

    @Override
    public void onMessageReceive(String value) {
        System.out.println(value);
    }

}
```
[🌠 More advanced examples of pvpheaven-nats-messenger.](https://github.com/nwseOOO/pvpheaven-nats-messenger-examples)
