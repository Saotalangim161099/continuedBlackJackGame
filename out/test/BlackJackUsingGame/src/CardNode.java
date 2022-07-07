public class CardNode {
    private String value;
    private String suit;

    private int numOfCards;
    private CardNode link;

    public CardNode(String value, String suit, CardNode link) {
        this.value = value;
        this.suit = suit;
        this.link = link;
    }

    // Return the card of the deck
    public String printCard() {
        switch (suit) {
            case "H":
                switch (value) {
                    case "1":
                        return "A- H";
                    case "11":
                        return "J- H";
                    case "12":
                        return "K- H";
                    case "13":
                        return "Q- H";
                    default:
                        return value + "- H";

                }
            case "S":
                switch (value) {
                    case "1":
                        return "A- S";
                    case "11":
                        return "J- S";
                    case "12":
                        return "K- S";
                    case "13":
                        return "Q- S";
                    default:
                        return value + "- S";

                }
            case "D":
                switch (value) {
                    case "1":
                        return "A- D";
                    case "11":
                        return "J- D";
                    case "12":
                        return "K- D";
                    case "13":
                        return "Q- D";
                    default:
                        return value + "- D";

                }
            case "C":
                switch (value) {
                    case "1":
                        return "A- C";
                    case "11":
                        return "J- C";
                    case "12":
                        return "K- C";
                    case "13":
                        return "Q- C";
                    default:
                        return value + "- C";
                }
        }
        return null;
    }

    public void setCard(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public CardNode getLink() {
        return link;
    }

    public void setLink(CardNode newNode) {
        this.link = newNode;
    }


    // Calculate the value of the card
    // The value 11, 12 and 13 are counted as 10 only
    public int getCardValue() {
        if (value.equals("11") || value.equals("12") || value.equals("13")) {
            return 10;
        }
        return Integer.parseInt(this.value);
    }


    // Check whether the card value of the object card is smaller than the one of the compared card
    public boolean isSmallerCardValue(CardNode comparedCard) {
        return this.getCardValue() < comparedCard.getCardValue();
    }

    // Compare card value of the player and the opponent
    public int compareCardValue(CardNode opp) {
        return this.getCardValue() - opp.getCardValue();
    }

    /*public static void main(String[] args) {
        CardNode cardNode = new CardNode("13", "H", null);
        //cardNode
        System.out.println(cardNode.compareCardValue(new CardNode("1", "C", null)));
    }*/
}
