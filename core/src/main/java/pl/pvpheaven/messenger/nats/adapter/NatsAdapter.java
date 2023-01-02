package pl.pvpheaven.messenger.nats.adapter;

import pl.pvpheaven.messenger.nats.handler.NatsHandler;

/* Purposefully empty. */
public class NatsAdapter<V> implements NatsHandler<V> {

    @Override
    public void onMessageReceive(V value) {}

}