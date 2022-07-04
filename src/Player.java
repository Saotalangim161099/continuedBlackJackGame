public class Player {
    private String name;
    private String phoneNumber;

    // A card hand to save the hand of the player
    private CardHand playerHand;

    // Status to determine whether the user is losing, winning or even to the opponent
    private int status;

    // constants for player status
    public static final int STATUS_WIN = 1;
    public static final int STATUS_TIE = 0;
    public static final int STATUS_LOSE = -1;
    public static final int STATUS_UNDETERMINED = 13;

    public Player(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.playerHand = new CardHand();
        this.status = STATUS_UNDETERMINED;
    }

    public String getStatus() {
        switch (status) {
            case STATUS_LOSE:
                return "Lose";
            case STATUS_TIE:
                return "Tie";
            case STATUS_WIN:
                return "Win";
        }
        return "Undetermined!!!!";
    }

    public String getResult() {
        return "Player: " + this.getName() + "   Status: " + this.getStatus();
    }

    // If both the player and the dealer win, set the status of the player to ever
    public void setStatusTie() {
        setStatus(STATUS_TIE);
    }

    public void setStatusWin() {
        setStatus(STATUS_WIN);
    }

    public void setStatusLose() {
        setStatus(STATUS_LOSE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CardHand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(CardHand playerHand) {
        this.playerHand = playerHand;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    // Method to get the total point of the player hand
    public int calculateTotalPointPlayerHand() {
        return playerHand.calculateTotalPoint();
    }

    // Method to present the player hand cards
    public void printHand() {
        playerHand.presentCardHand();
    }

    // Method to show the name and phone number of the player
    public String toString() {
        return "Name: " + name + ".  " + "Phone number: " + phoneNumber;
    }

    // Add one card to the player hand
    public void dealCardForDealer(CardNode cardNode) {
        this.getPlayerHand().addCardToHand(cardNode);
    }

    // Check if the player's hand busted or not
    public boolean isBusted() {
        return playerHand.isBusted();
    }

    public boolean isBlackJack() {
        return playerHand.isBlackJack();
    }
}
