
public class PlayingCard {

    private int number;
    private String suit;

    public PlayingCard(int tempNumber, String tempSuit) {
        number = tempNumber;
        suit = tempSuit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String toString() {
        return "PlayingCard{" + "number=" + number + ", suit=" + suit + '}';
    }

}
