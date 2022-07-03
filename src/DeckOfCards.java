import java.security.SecureRandom;

public class DeckOfCards {
    int numOfCards = 0;
    CardNode deck;
    public static final String[] CARD_VALUES = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    public static final String[] CARD_SUITS = new String[]{"H", "S", "D", "C"};
    CardNode head = null;

    // Request whether the player want to create a blank or standard deck
    public DeckOfCards(boolean emptyDeck) {
        if (emptyDeck) {
            return;
        } else
            generateStandardDeck();
    }

    private void generateStandardDeck() {
        for (int indexCard = 0; indexCard < CARD_VALUES.length; indexCard++) {
            for (int indexSuit = 0; indexSuit < CARD_SUITS.length; indexSuit++) {
                CardNode card = new CardNode(CARD_VALUES[indexCard], CARD_SUITS[indexSuit], null);
                addLast(card);

            }
        }
    }

    // Add card to the front of the deck
    private void addFront(CardNode newCard) {
        if (head == null) {
            head = newCard;
            System.out.println("1");
        } else {
            newCard.setLink(head);
            head = newCard;

        }
        numOfCards++;
    }

    // Add card ti the end of the deck
    private void addLast(CardNode newCardNode) {
        if (head == null) {
            head = newCardNode;
        } else {
            CardNode currentNode = head;
            while (currentNode.getLink() != null) {
                currentNode = currentNode.getLink();
            }
            currentNode.setLink(newCardNode);
        }
        numOfCards++;
    }

    // Remove a card from the deck at given position
    public CardNode removeAtIndex(int position) {
        if (head == null) {
            return null;
        }
        CardNode cursor = head;
        if (position == 0) {
            CardNode temp = head;
            head = head.getLink();
            temp.setLink(null);
            numOfCards--;
            return temp;
        }

        for (int i = 0; cursor != null && i < position - 1; i++) {
            cursor = cursor.getLink();
        }
        if (cursor == null || cursor.getLink() == null) {

            return null;
        }
        CardNode nextOfNext = cursor.getLink().getLink();
        CardNode removedNode = cursor.getLink();
        removedNode.setLink(null);
        cursor.setLink(nextOfNext);
        numOfCards--;
        return removedNode;
    }


    // Shuffle the deck by removing a card at random position then add it back to the front of the deck
    public void shuffleDeck() {
        for (int i = 0; i < 1000; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int randomPosition = Math.abs(secureRandom.nextInt()) % numOfCards;

            CardNode removedCard = removeAtIndex(randomPosition);
            addLast(removedCard);
        }
    }

    public void printDeck() {
        /*CardNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getCard());
            // System.out.println();

            currentNode = currentNode.getLink();
        }*/
        CardNode cursor = head;
        if (head == null) {
            return;
        }

        while (cursor != null) {

            System.out.println(cursor.printCard());
            cursor = cursor.getLink();
        }
    }

    public static void main(String[] args) {
        DeckOfCards deckOfCards = new DeckOfCards(false);

        deckOfCards.shuffleDeck();
        deckOfCards.printDeck();


        System.out.println(deckOfCards.numOfCards);

    }
}
