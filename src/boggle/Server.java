package boggle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

	// may want to split this up into 2 classes

	// must be multi-threaded to be able to connect to 2 clients


	private List<Socket> sockets; // game is played with exactly 2 clients

	private ServerSocket serverSocket;

	// either have constructor or main to open connection - I think we should
	// use a main, added one below
	public Server() throws IOException {
		serverSocket = new ServerSocket(8080);
        sockets = new ArrayList<Socket>();

	}

	public void run() {
		Socket clientSocket;

		while (true) {

			try {
				clientSocket = serverSocket.accept();
				
				synchronized(sockets){
					sockets.add(clientSocket);
					}
				
				SocketHandler handlerThread = new SocketHandler(clientSocket,sockets);
				handlerThread.start();
						
						
						
					

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public List<Socket> getSockets() {
		return sockets;
	}


	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.run();

	}

}
