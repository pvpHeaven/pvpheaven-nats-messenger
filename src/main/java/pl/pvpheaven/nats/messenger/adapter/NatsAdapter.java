package pl.pvpheaven.nats.messenger.adapter;

import pl.pvpheaven.nats.messenger.handler.NatsHandler;

public class NatsAdapter<V> implements NatsHandler<V> {

    @Override /* Empty */
    public void onMessageReceive(V value) {}

}