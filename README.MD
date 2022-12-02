# 🌠 NATS Messenger (Work In Progress)
Biblioteka stworzona na potrzeby serwera `pvpHeaven.pl`, udostępniona *for sake of humanity*.

Rozszerza `pub/sub` z `NATS.io` o kodeki i możliwość *abstrakcji*.

---
## 🌠 NATS Messenger :: Użycie

> Tworzenie klienta i połączenia:
```java
final Options options = new Options.Builder()
        .server("nats://demo.nats.io:4222")
        .build();
final NatsClient natsClient = NatsMessenger.create(options);
final NatsConnection<String> natsConnection = natsClient.createConnection(new NatsStringCodec());
```

> Subskrybowanie kanałów i publikowanie wiadomości:
```java
natsConnection.subscribe("test-channel", new NatsTestListener());
/* Lub */
natsConnection.subscribe("test-channel", value -> System.out.println(value));
natsConnection.publish("test-channel", "Test message!");
```

> Przykładowy "słuchacz" wiadomości:
```java
class NatsMessageListener implements NatsHandler<String> {

    @Override
    public void onMessageReceive(String value) {
        System.out.println(value);
    }

}
```
[🌠 Bardziej rozbudowane przykłady użycia pvpheaven-nats-messenger.](https://github.com/nwseOOO/pvpheaven-nats-messenger-examples).
