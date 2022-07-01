import java.util.ArrayList;

public class CardHand implements Comparable {
    public static final int BLACKJACK_VALUE = 9999;
    public static final int BUSTED_VALUE = -1;

    // Using an arrayList to hold the card hand
    private ArrayList<CardNode> cardHand;


    // If the player demand the hand is empty, create a deck with no card, otherwise, create a full standard deck

    public CardHand() {
        cardHand = new ArrayList<>();
    }

    // Check the status of the player, whether the player is busted, black jack or nothing special so return the total point of the player's card hand
    private int getStatus() {
        if (isBusted()) {
            return BUSTED_VALUE;
        } else if (isBlackJack()) {
            return BLACKJACK_VALUE;
        } else {
            return this.calculateTotalPointOfCardHand();
        }
    }


    // Check if the player is busted
    private boolean isBusted() {
        return this.calculateTotalPointOfCardHand() > 21;
    }

    // Check if the player have black jack
    private boolean isBlackJack() {
        return (cardHand.size() == 2 && this.calculateTotalPointOfCardHand() == 21);
    }

    // Calculate the total point of the player's card hand
    private int calculateTotalPointOfCardHand() {

        // Separate the sum of the card hand except the aces and the sum of the aces as well as the number of aces in the card hand
        int totalPoint = 0;
        int nonAcesSum = 0;
        int acesSum = 0;
        int numOfAces = 0;


        for (CardNode card : cardHand) {
            if (card.getCardValue() == 1) {
                numOfAces++;
            } else {
                nonAcesSum += card.getCardValue();
            }
        }

        // There cannot be the case where more than 1 Ace are counted as 11 because at least 22 means the player is busted already.
        // Therefore, initially we assign 1 Ace the value of 11, all remaining Aces are counted as 1 only. Then we calculate the total, if it is busted,
        // the value of all every single Ace would be counted as 1 only.
        if (numOfAces > 0) {
            acesSum = 11;
        }

        // All remaining Aces except the first one which is already counted 11 would be counted as 1 only
        for (int countAce = 0; countAce < numOfAces - 1; countAce++) {
            acesSum += 1;
        }
        totalPoint = acesSum + nonAcesSum;

        // If the player is busted, then every Ace are counted as 1 only
        if (acesSum + nonAcesSum > 21) {
            totalPoint -= 10;
        }

        return totalPoint;
    }

    // Present card hand of the player
    public void presentCardHand() {
        for (int cardIndex = 0; cardIndex < cardHand.size(); cardIndex++) {
            System.out.println(cardHand.get(cardIndex).getCard());
            System.out.println();
        }
    }

    // Add new card to the player's hand
    public void addCardToHand(CardNode cardNode) {
        cardHand.add(cardNode);
    }


    // Compare the status of the player and the opponent
    @Override
    public int compareTo(Object o) {
        CardHand opponentCardHand = (CardHand) o;

        // Return 0 if both are even where both are busted or have black jack, 1 if the player wins, or -1 if the opponent wins
        if (this.getStatus() == opponentCardHand.getStatus()) {
            return 0;
        } else if (this.getStatus() > opponentCardHand.getStatus()) {
            return 1;
        } else {
            return -1;
        }
    }
}
