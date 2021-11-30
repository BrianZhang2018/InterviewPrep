package remitly;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by brianzhang on 6/15/20.
 */
public class PokerCard {

    public static void main(String[] args) {
        PokerCard test = new PokerCard();
        test.shuffle(); test.print();
    }

    Card[] cards;
    int numOfCards;

    public PokerCard() {
        numOfCards = Card.suits.length * Card.ranks.length;
        cards = new Card[numOfCards];

        int index = 0;
        for (int i = 0; i < Card.suits.length; i++) {
            for (int j = 0; j < Card.ranks.length; j++) {
                cards[index++] = new Card(Card.suits[i], Card.ranks[j]);
            }
        }
    }

    public void shuffle() {
        if (cards == null || cards.length < 2) return;

        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
            int r = rand.nextInt(i + 1);
            Card tmp = cards[i];
            cards[i] = cards[r];
            cards[r] = tmp;
        }
    }

    public void print() {
        System.out.println(Arrays.toString(cards));
    }
}


class Card {
    static String[] suits = {"c", "d", "h", "s"};
    static String[] ranks = {"a", "2", "k"};

    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.suit + " : " + this.rank;
    }
}
