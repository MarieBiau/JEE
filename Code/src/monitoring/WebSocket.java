package monitoring;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/**
 * Classe pour les websockets du serveur
 * @author Pierre-Antoyne Fontaine
 * 
 */
@ServerEndpoint("/socketPanne")
public class WebSocket {
	//Sessions des utilisateurs connectés
	static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	
	@OnOpen
	/**
	 * Méthode appelée lors d'une ouverture de socket
	 * @param session
	 */
	public void open(Session session) {
		queue.add(session);
	}

	@OnClose
	/**
	 * Méthode appelée lors de la fermeture d'un socket
	 * @param session
	 */
	public void close(Session session) {
		queue.remove(session);
	}

	@OnError
	/**
	 * Méthode appelée lors d'une erreur
	 * @param session
	 * @param error
	 */
	public void onError(Session session, Throwable error) {
		queue.remove(session);
	}

	@OnMessage
	/**
	 * Méthode appelée lors de la reception d'un message
	 * @param message
	 * @param session
	 */
	public void handleMessage(String message, Session session) {
		WebSocket.maj(message);
	}
	
	/**
	 * Méthode statique pour envoyer un message à tous les clients connectés
	 * @param value
	 */
	public static void maj(String value){
		try{
			for (Session s : queue){
				s.getBasicRemote().sendText(value);
			}	
		} catch (IOException e){
			System.out.println(e.getMessage());
		}

	}
}