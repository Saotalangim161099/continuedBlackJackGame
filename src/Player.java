public class Player {
    private String name;
    private String phoneNumber;
    private CardHand playerHand;

    private int status;

    // constants for player status
    public static final int STATUS_WIN = 1;
    public static final int STATUS_TIE = 0;
    public static final int STATUS_LOSE = 11;
    public static final int STATUS_UNDETERMINED = 13;

    public Player(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.playerHand = new CardHand();
        this.status = STATUS_UNDETERMINED;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Method to get the total point of the player hand

    // Method to present the player hand cards

    // Method to show the name and phone number of the player
    public String toString() {
        return "Name: " + name + ".  " + "Phone number: " + phoneNumber;
    }
}
