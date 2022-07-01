import java.security.SecureRandom;

public class DeckOfCards {
    int numOfCards = 0;
    CardNode deck;
    public static final String[] CARD_VALUES = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}
    public static final String[] CARD_SUITS = new String[]{"H", "S", "D", "C"};
    CardNode head = null;

    private void generateStandardDeck() {
        for (int indexCard = 0; indexCard < CARD_VALUES.length; indexCard++) {
            for (int indexSuit = 0; indexSuit < CARD_SUITS.length; indexSuit++) {
                CardNode card = new CardNode(CARD_VALUES[indexCard], CARD_SUITS[indexSuit], null);

            }
        }
    }

    // Add card to the front of the deck
    private void addFront(CardNode newCard) {
        if (head == null) {
            head = newCard;
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
    private CardNode removeAtIndex(int position) {
        if (head == null) {
            return null;
        } else {

            // Decrement the number of cards by 1
            numOfCards--;

            // Store the head
            CardNode currentNode = head;

            // If we need to remove the head of the deck
            if (position == 0) {
                head = head.getLink();
                return currentNode;
            } else {
                // Find the previous node of the node need to be removed
                for (int i = 0; currentNode != null && i < position - 1; i++) {
                    currentNode = currentNode.getLink();
                }

                // If the position is larger the number of nodes
                if (currentNode == null || currentNode.getLink() == null) {
                    return null;
                }

                // Save the removed card
                CardNode removedNode = currentNode.getLink();
                removedNode.setLink(null);

                CardNode nextNode = currentNode.getLink().getLink();
                currentNode.setLink(nextNode);
                return removedNode;
            }

        }
    }

    // Shuffle the deck by removing a card at random position then add it back to the front of the deck
    public void shuffleDeck() {
        for (int i = 0; i < 1000; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int randomPosition = Math.abs(secureRandom.nextInt()) % numOfCards;

            CardNode removedCard = removeAtIndex(randomPosition);
            addFront(removedCard);
        }
    }

    public void printDeck() {
        CardNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getCard());
            System.out.println();

            currentNode = currentNode.getLink();
        }
    }
