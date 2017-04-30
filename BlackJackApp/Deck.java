
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {

    private ArrayList deck;
    private PlayingCard card;

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        deck = new ArrayList<PlayingCard>();
        final ArrayList<String> suits = new ArrayList<>(Arrays.asList("Spades", "Clubs", "Diamonds", "Hearts"));
        int x = 0;
        int valueOfCard = 1;
        int indexOfSuits = 0;
        for (int i = 0; i < 52; i++) {
            if (x % 4 == 0 && x != 0) {
                if(valueOfCard<10){
                    valueOfCard++;
                }
                indexOfSuits = 0;
            }
            card = new PlayingCard(valueOfCard, suits.get(indexOfSuits));
            deck.add(card);
            indexOfSuits++;
            x++;
        }

        shuffle();

    }

    private void subtractCard(int tempNum) {
        deck.remove(tempNum);
    }

    public PlayingCard getTopCard() {
        if (deck.isEmpty()) {
            createDeck();
        }
        int num = deck.lastIndexOf(this);
        PlayingCard tempCard = (PlayingCard) deck.get(num);
        subtractCard(num);
        return tempCard;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }
}
