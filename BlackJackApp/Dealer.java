import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class Dealer{
    Deck deck;
    private static ArrayList<DealerMessageHandler> dealerMessageHandlerList = new ArrayList<DealerMessageHandler>();

    public Dealer(){
        deck = new Deck();
        deck.shuffle();
    }

    public static void addDealerMessageHandler(DealerMessageHandler theDealerMessageHandler){
        dealerMessageHandlerList.add(theDealerMessageHandler);
    }

    public static void remove(DealerMessageHandler theDealerMessageHandler){
        dealerMessageHandlerList.remove(theDealerMessageHandler);
    }

    public static int getClientListSize(){
        return dealerMessageHandlerList.size();
    }

    public static String[] getBuddyListText(){
        String[] buddyListText = new String[getClientListSize()];

        for(int i = 0; i<getClientListSize(); i++){
            buddyListText[i] = dealerMessageHandlerList.get(i).getRemoteAddress().toString();
        }
        return buddyListText;
    }

    public static void broadCastMessage(String message, Socket client){
        for (DealerMessageHandler iterator : dealerMessageHandlerList ) {
            try{
                System.out.println("In for loop...Client address is: "+iterator.getClientConnection().getRemoteSocketAddress());
                DataOutputStream out = new DataOutputStream(iterator.getClientConnection().getOutputStream());
                out.writeUTF("["+client.getRemoteSocketAddress()+" >>] "+ message);
            }catch(IOException e){
                System.out.println("Client has left the chatroom");
                Dealer.remove(iterator);
                iterator.setDone(true);
                System.out.println("The Buddy List is now size: "+getClientListSize());
            }
        }
    }

    public static void closeConnection(SocketAddress clientAddress) throws IOException{
        for (int i = 0; i< dealerMessageHandlerList.size(); i++) {
            System.out.println("In close for loop...Client address is: "+dealerMessageHandlerList.get(i).getClientConnection().getRemoteSocketAddress());
            if(dealerMessageHandlerList.get(i).getRemoteAddress() == clientAddress){
                dealerMessageHandlerList.get(i).closeSocket();
            }
        }
    }

    public void dealCard(PlayerClient playerClient){
        PlayingCard cardToSend = deck.getTopCard();
        //playerClient.addToHand(cardToSend);
    }
}
