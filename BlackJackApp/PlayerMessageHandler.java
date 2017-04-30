import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class PlayerMessageHandler extends Thread{
  private Socket client;
  private DataInputStream in;
  private DataOutputStream out;
  private String message = null;

  public PlayerMessageHandler(Socket sentClient){
    this.client = sentClient;
    PlayerView view = new PlayerView(this);
  }

  public void run(){
    BufferedReader br = null;
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the text to send: ");
    while(true){
      try{
        br = new BufferedReader(new InputStreamReader(System.in));
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());

        if(in.available() != 0){
            String chatText = in.readUTF();
            System.out.println(chatText);
        }


        if(message != null){
            out.writeUTF(message);
            message = null;
        }

      }catch(IOException e){
        e.printStackTrace();
        break;
      }
   }
  }

  public void setMessage(String message){
      this.message = message;
  }

  public String getAddress(){
      return "Address: "+client.getLocalSocketAddress().toString();
  }
}
