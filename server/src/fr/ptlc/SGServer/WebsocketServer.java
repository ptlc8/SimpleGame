package fr.ptlc.SGServer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class WebsocketServer extends WebSocketServer {
	
	//default values
	private static String IP = "localhost";
	private static int TCP_PORT = 13028;

	private Map<WebSocket, Integer> connections;
	private static int kId = 1;
	
	public WebsocketServer() {
		super(new InetSocketAddress(IP, TCP_PORT));
		connections = new HashMap<>();
	}

	public WebsocketServer(String IP, int TCP_PORT) {
		super(new InetSocketAddress(IP, TCP_PORT));
		connections = new HashMap<>();
	}
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		connections.put(conn, kId++);
		System.out.println("Nouvelle connexion depuis " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		int id = connections.get(conn);
		onClose(id);
		connections.remove(conn);
		System.out.println("Connexion fermée du client " + id + (conn.getRemoteSocketAddress()!=null ? " depuis " + conn.getRemoteSocketAddress().getHostString() : "") + " ("+code+")");
	}
	
	public abstract void onClose(int id);

	@Override
	public void onMessage(WebSocket conn, String message) {
		int id = connections.get(conn);
		/**/if (!message.startsWith("getentities"))
		System.out.println("Message du client " + id + " : " + message);
		String reply = onMessage(id, message);
		if (conn != null) conn.send(reply);
	}
	
	public abstract String onMessage(int id, String message);

	@Override
	public void onError(WebSocket conn, Exception ex) {
		//ex.printStackTrace();
		if (conn != null) {
			connections.remove(conn);
			conn.close();
			// do some thing if required
		}
		System.out.println("ERREUR" + (conn==null ? " !" : " depuis ") + (conn.getRemoteSocketAddress().getAddress()==null ? "une connexion" : conn.getRemoteSocketAddress().getAddress().getHostAddress()));
		System.err.println(ex);
		ex.printStackTrace();
	}

	@Override
	public void onStart() {
		System.out.println("Serveur démarré");
		
	}
	
	public void sendMessage(int id, String message) {
		for (Entry<WebSocket, Integer> conn : connections.entrySet())
			if (conn.getValue() == id)
				conn.getKey().send(message);
	}
	
}
