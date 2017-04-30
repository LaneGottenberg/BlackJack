import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayerClient{
    ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();

  public static void main(String[] args){

    String serverName = "localhost";
    int port = 5001;

    try{
      System.out.println("Connecting to "+ serverName+" on port "+port);
      Socket messageServer = new Socket(serverName, port);
      System.out.println("Connection established to black jack server...");

      Thread t = new PlayerMessageHandler(messageServer);
      t.start();
    }catch(SocketTimeoutException s){
      System.out.println("Socket Timed Out...");
    }catch(IOException e){
      e.printStackTrace();
    }


  }
}
