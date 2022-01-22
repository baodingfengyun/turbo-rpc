package rpc.turbo.transport.client.sender;

import rpc.turbo.transport.client.future.RequestWithFuture;

import java.io.Closeable;

public interface Sender extends Closeable {

    public void send(RequestWithFuture request);
}
