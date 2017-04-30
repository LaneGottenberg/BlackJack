import java.net.*;
import java.io.*;
import java.net.InetAddress;

public class BlackJackServer{
  private static int port = 5001;
  private static ServerSocket serverSocket;


  public static void main(String [] args){
      Dealer theDealer = new Dealer();

    try{
        serverSocket = new ServerSocket(port);
    }catch(IOException z){
        System.out.println("Talk Server catch IOException");
    }

    while(true){
      try{

        System.out.println("Waiting for player on port " + serverSocket.getLocalPort() + "...");
        Socket client = serverSocket.accept();
        System.out.println("Connection established: "+ client.getRemoteSocketAddress());

        Thread t = new DealerMessageHandler(client);
        t.start();
      }catch(SocketTimeoutException s){
        System.out.println("Socket Timed Out...");
        break;
      }catch(IOException e){
        e.printStackTrace();
        break;
      }
    }
  }
}
