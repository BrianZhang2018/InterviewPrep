package grubhub.OOD;

/**
 * GrubHub + Remitly
 * Created by brianzhang on 10/15/19.
 */
public class DeckOfCards {

    private int numOfCards;
    private PokerCard[] deckOfPokerCards;         // Contains all 52 cards
    private int cardsUsed;

    public DeckOfCards()    // Constructor
    {
        this.numOfCards = PokerCard.getRanks().length * PokerCard.getSuits().length;
        this.deckOfPokerCards = new PokerCard[numOfCards];

        int index = 0;

        for (int suit = 0; suit < PokerCard.getSuits().length; suit++)
            for (int rank = 0; rank < PokerCard.getRanks().length; rank++)
                this.deckOfPokerCards[index++] = new PokerCard(suit, rank);

        cardsUsed = 0;
    }

    /* ---------------------------------
      shuffle(n): shuffle the deck
      alternative solution:
      --------------------------------- */
    public void shuffle() {
        int i, j, k;

        for (k = 0; k < numOfCards; k++) {
            i = (int) (numOfCards * Math.random());  // Pick 2 random cards
            j = (int) (numOfCards * Math.random());  // in the deck

   	     /* ---------------------------------
           swap these randomly picked cards
   		--------------------------------- */
            PokerCard tmp = deckOfPokerCards[i];
            deckOfPokerCards[i] = deckOfPokerCards[j];
            deckOfPokerCards[j] = tmp;
        }

        cardsUsed = 0;   // Reset current card to deal
    }

    /* -------------------------------------------
      deal(): deal deckOfPokerCards[currentCard] out
      ------------------------------------------- */
    public PokerCard deal() {
        if (cardsUsed == numOfCards) {
            shuffle();
        }
        cardsUsed++;
        return deckOfPokerCards[cardsUsed - 1];
    }

    public String toString() {

        for (PokerCard pokerCard : deckOfPokerCards) {
            System.out.println(pokerCard.getCardRank() + pokerCard.getCardSuit());
        }
        return "";
    }
}
