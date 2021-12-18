package fr.ptlc.SGServer;

import java.util.Scanner;

import fr.ptlc.SGServer.game.GameMaps;

public class Main {
	
	public static final String version = "0.1.8beta";
	
	public static String ip = "";
	public static int port = 2;
	
	public static void main(String[] args) {
		System.out.println("Serveur SimpleGame " + version + " par PTLC_, pour le fun, <3");
		System.out.println("(Client par cervantess & PTLC_ : pomm.000webhostapp.com)");
		GameMaps.preload();
		try {
			ip = args[0];
			port = Integer.parseInt(args[1]);
			System.out.println("Lancement du serveur sur " + ip + ":" + port + " selon les arguments d'exécution");
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Adresse IP ?");
			ip = sc.nextLine();
			System.out.println("Port ?");
			port = sc.nextInt();
			sc.close();
			System.out.println("Lancement du serveur sur " + ip + ":" + port);
		}
		SG sg = new SG();
		WebsocketServer wss = new WebsocketServer(ip, port) {
			public String onMessage(int id, String message) {
				return sg.onPlayerMessage(id, message);
			}
			public void onClose(int id) {
				sg.onPlayerDisconnect(id);
			}
		};
		sg.setWebsocketServer(wss);
		wss.start();
	}
	
}