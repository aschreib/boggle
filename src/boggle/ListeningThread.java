package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread{
 
 private Socket socket;
 private Client client;
 
 public ListeningThread(Socket socket, Client client){
  this.socket = socket;
  this.client = client;
 }

 @Override
 public void run() {
  InputStream input;
  try {
   input = socket.getInputStream();
   BufferedReader reader = new BufferedReader(new InputStreamReader(input));
   String line;
   int index = 0;
   while ((line = reader.readLine()) != null) {
    client.getBoard()[index] = line;
    index++;
    //System.out.println(line);
   }
   client.getGui().createBoard(client.getBoard());
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 
 }
 
 

}