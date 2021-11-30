package grubhub.OOD;

/**
 * Created by brianzhang on 10/15/19.
 */
public class PokerCard {

    private static final String[] Suits = {"d", "c", "h", "s"};
    private static final String[] Ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private int cardSuit;
    private int cardRank;

    public PokerCard(int suit, int rank) {
        this.cardSuit = suit;
        this.cardRank = rank;
    }

    //translate to real suit value
    public String getCardSuit() {
        return Suits[cardSuit];
    }

    //translate to real card value
    public String getCardRank() {
        return Ranks[cardRank];
    }

    public static String[] getSuits() {
        return Suits;
    }

    public static String[] getRanks() {
        return Ranks;
    }

    @Override
    public String toString() {
        return Suits[cardSuit] + Ranks[cardRank];
    }

}
