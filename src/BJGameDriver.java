import java.util.ArrayList;
import java.util.Scanner;

public class BJGameDriver {
    private ArrayList<Player> playerArrayList;

    // Index to control the turn of players
    // Initialized to 1
    private int turnIndex;

    // Initialized to size of the player list
    private int numOfPlayers;
    private DeckOfCards deckOfCards;

    // Create an object for the dealer
    private Dealer dealer = new Dealer();
    private boolean isGameFinished;

    private ArrayList<Player> winnersArrayList;


    // For the case the deck is not empty initially
    private void initDeck() {
        deckOfCards = new DeckOfCards(false);
    }

    // Initialize the players list and winners list
    private void initPlayers(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
        this.winnersArrayList = new ArrayList<>();
    }

    // Check if the game is finished yet
    private boolean isGameFinished() {
        return isGameFinished;
    }


    // Check whether the dealer has black jack, if yes, go check all players if any also has BJ, if yes set the player status to even
    // terminate the game
    private boolean isDealerBlackJack() {
        if (dealer.isBlackJack()) {
            System.out.println("Dealer has Black Jack!!!!");

            for (Player player : playerArrayList) {
                if (player.isBlackJack()) {
                    player.setStatusTie();
                } else {
                    player.setStatusLose();
                }
                System.out.println(player.getResult());

                // Terminate the game
                isGameFinished = true;
            }
            return true;
        }
        return false;
    }


    // Determine the winners and return an arrayList of the winners for android dev use later
    public void initWinners() {
        int dealerPoint = dealer.calculateTotalDealerHand();

        for (Player player : playerArrayList) {
            int playerPoint = player.calculateTotalPointPlayerHand();

            if (playerPoint > dealerPoint) {
                player.setStatusWin();
                winnersArrayList.add(player);
            } else if (playerPoint == dealerPoint) {
                player.setStatusTie();
            } else {
                player.setStatusLose();
            }

            // Print out the player result
            System.out.println(player.getResult());
        }

        // Terminate the game
        isGameFinished = true;
    }


    // Remove the first card of the deck
    private CardNode removeFirstCard() {
        return deckOfCards.removeAtIndex(0);
    }

    // Dealing one card for the player
    private void dealCardForPlayer(Player player) {
        player.dealCardForDealer(removeFirstCard());
    }

    // Dealing one card for the dealer
    private void dealCardForDealer() {
        dealer.dealCardForDealer(removeFirstCard());
    }

    // To start the game, Deal 2 cards for each player and the dealer
    public void dealStartingCards() {
        for (Player player : playerArrayList) {

            System.out.println("Dealing cards for: " + player.getName());
            dealCardForPlayer(player);
            dealCardForPlayer(player);

            System.out.println(player.getName() + "'s hand: ");
            player.printHand();

            System.out.println("TOTAL POINT: " + player.calculateTotalPointPlayerHand());
        }

        dealCardForDealer();
        dealCardForDealer();
        System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
    }


    // Dealer must hit while his hand is 16 or under
    // If the total is 17 or more, he must stand
    private void dealerPlays() {

        // Firstly, go check whether the dealer has Black Jack or not
        // Here the isGameFinished will control the game. NO need to do anything
        if (isDealerBlackJack()) {
            return;
        } else {

            while (dealer.calculateTotalDealerHand() <= 16) {
                System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
                System.out.println("Dealer continues to hit.");
                dealCardForDealer();
            }

            // If the dealer is lower than 17
            // For the case the dealer is already 16, he must get one more card
            if (dealer.calculateTotalDealerHand() < 17) {
                System.out.println("Dealer continues to hit.");
                dealCardForDealer();
            }
            System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
        }
    }


    // determine the winners


    // Manage the move of the players
    private void processMove(String phoneNumber, String move) {

        // Process move for PLAYER first

        // Determine the current player based on the turn index
        Player currentPlayer = playerArrayList.get(turnIndex);

        if (phoneNumber.equals(currentPlayer.getPhoneNumber())) {

            // Notify the current player name and total point
            System.out.println("It's " + currentPlayer.getName() + "s turn.");
            int currentTotalPoint = currentPlayer.calculateTotalPointPlayerHand();
            System.out.println("Current TOTAL POINT: " + currentTotalPoint);

            // If the player wants to stand, then skip to the next player in the list
            if (move.toUpperCase().equals("STAND")) {
                turnIndex++;
                return;
            }

            while (move.toUpperCase().equals("HIT")) {

                // Check if the player is busted or not, if the player is busted, skip to the next player
                if (currentPlayer.isBusted()) {
                    System.out.println("You've been busted already!");
                    turnIndex++;
                    return;
                }

                // If the player chooses to hit and he/she is not busted yet, then deal card for him/her and process the move
                dealCardForPlayer(currentPlayer);
                System.out.println(currentPlayer.getName() + "'s card hand: ");
                currentPlayer.printHand();
                System.out.println("TOTAL POINT after the move: " + currentPlayer.calculateTotalPointPlayerHand());

                System.out.println("STAND or HIT ?");
                Scanner scanner = new Scanner(System.in);
                move = scanner.next();

                // If the player wants to stand, then skip to the next player in the list
                if (move.toUpperCase().equals("STAND")) {
                    turnIndex++;
                    return;
                }
            }

            // Process move for the dealer after dealing with all the players
            if (turnIndex == numOfPlayers - 1) {
                dealerPlays();
                System.out.println("Black Jack game is over!!!!");
                System.out.println(" WHO IS THE WINNER????");
                isGameFinished = true;
            }
        }

        // If the phone number is not matched to the current player's one
        System.out.println(" It's not turn of the player having phone number " + phoneNumber + "!!!!");
    }

}


