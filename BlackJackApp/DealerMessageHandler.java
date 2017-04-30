import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.net.InetAddress;

public class DealerMessageHandler extends Thread{
  private Socket client;
  private DataInputStream in;
  private DataOutputStream out;
  private SocketAddress remoteAddress;
  private boolean done = false; //Is the handler done with its actions?


  public DealerMessageHandler(Socket sentClient){
    this.client = sentClient;
    Dealer.addDealerMessageHandler(this);
    System.out.println("Printing from the server message handler... the ip sent is: "+client.getRemoteSocketAddress());
    System.out.println("The buddy list is size: "+Dealer.getClientListSize());
  }

  public void run(){
    while(!done){
      try{
          if(!client.isClosed()){
              this.in = new DataInputStream(client.getInputStream());
              this.out = new DataOutputStream(client.getOutputStream());

              String messageToSendToClient = this.safelyReadInputStream();

              if(messageToSendToClient != null){
                  Dealer.broadCastMessage(messageToSendToClient, client);
              }
         }
     }catch(IOException e){
         System.out.println("Thrown from DealerMessageHandler");
         e.printStackTrace();
    }
  }
  try{
    client.close();
}catch(IOException ioe){

}
 }

    public void setDone(boolean value){
        done = value;
    }

    public String safelyReadInputStream() throws IOException{

      if(in.available() == 0){
        return null;
      }else{
        return in.readUTF();
      }
    }

    public void closeSocket() throws IOException{
        this.client.close();
    }

    public Socket getClientConnection(){
        return this.client;
    }

    public SocketAddress getRemoteAddress(){
        return this.remoteAddress;
    }
}
