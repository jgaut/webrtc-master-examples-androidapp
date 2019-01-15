package org.appspot.apprtc.third;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.appspot.apprtc.ConnectActivity;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class EmptyClient extends WebSocketClient {

    private ConnectActivity connectActivity;

    public EmptyClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public EmptyClient(URI serverURI, ConnectActivity connectActivity) {
        super(serverURI);
        this.connectActivity = connectActivity;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
        if(message!=null && message.startsWith("callback_")){
            String[] idRoom = message.split("_");
            connectActivity.connectToRoom2(idRoom[1], false, false, false, 0);
        }
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

}
