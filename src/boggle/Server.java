package boggle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

	private ServerSocket serverSocket;
	private List<Socket> sockets;
	private List<SocketHandler> socketHandlers;
	private Evaluator evaluator;

	private String[] boggleBoard;

	private int numGamesStarted;

	public Server() throws IOException {
		serverSocket = new ServerSocket(8080);
		sockets = new ArrayList<Socket>();
		socketHandlers = new ArrayList<SocketHandler>();
		evaluator = new Evaluator();
		boggleBoard = new BoggleDice().getLetters();
		numGamesStarted = 0;

	}

	public void run() {

		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();

				SocketHandler handlerThread = new SocketHandler(this, clientSocket);
				socketHandlers.add(handlerThread);
				handlerThread.start();

				synchronized (sockets) {
					sockets.add(clientSocket);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Socket> getSockets() {
		return sockets;
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public String[] getBoard() {
		return boggleBoard;
	}

	public int getNumGamesStarted() {
		return numGamesStarted;
	}

	public void incrementNumGamesStarted() throws IOException {
		numGamesStarted++;
		if (numGamesStarted == 2) {
			for (SocketHandler h : socketHandlers) {
				h.sendBoardToClient();
			}
		}

	}

	public static void main(String[] args) {
		Server server;
		try {
			server = new Server();
			server.run();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
