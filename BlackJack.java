import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BlackJack {
    Deck deck = new Deck();
    public ArrayList<Card> playerHand = new ArrayList<>();
    public ArrayList<Card> dealerHand = new ArrayList<>();
    public boolean isPlaying;

    public BlackJack() {
        isPlaying = true;
        boolean globalPlaying = true;
        while (globalPlaying) {
            globalPlaying = play();
        }
    }

    public boolean play() {
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.dealCard());
            dealerHand.add(deck.dealCard());
        }
        while (isPlaying) {
            // Deals Hand
            boolean hasrun = false;
            String playString = showHand("Press 1 to hit or 0 to stay");
            int playInt = Integer.parseInt(playString);
            if (playInt == 0) {
                // Stay
                while (getScore(dealerHand) < 17) {
                    dealerHand.add(deck.dealCard());
                    showHand("Dealer draws card, press enter");
                    if (getScore(dealerHand) > 21) {
                        // Switches Ace
                        for (Card card : playerHand) {
                            if (card.getValue() == 11) {
                                card.setValue(1);
                            }
                        }
                    }
                }
                String play = endGame();
                hasrun = true;
                if (play.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Thanks for playing");
                    isPlaying = false;
                    return false;
                } else {
                    dealerHand.clear();
                    playerHand.clear();
                    deck = new Deck();
                    return true;
                }
            } else {
                // Hit
                playerHand.add(deck.dealCard());

            }
            if (getScore(playerHand) > 21) {
                // Switches Ace
                for (Card card : playerHand) {
                    if (card.getValue() == 11) {
                        card.setValue(1);
                    }
                }
                if (getScore(playerHand) > 21) {
                    // Wraps Up Game
                    if (hasrun == false) {
                        String play = endGame();
                        if (play.equals("0")) {
                            JOptionPane.showMessageDialog(null, "Thanks for playing");
                            isPlaying = false;
                            return false;
                        } else {
                            dealerHand.clear();
                            playerHand.clear();
                            deck = new Deck();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getScore(ArrayList<Card> hand) {
        int sum = 0;
        for (Card x : hand) {
            sum += x.getValue();
        }
        return sum;
    }

    public String getHandMessage(ArrayList<Card> hand) {
        String message = "";
        for (Card x : hand) {
            message += "\n  " + x.toString();
        }
        return message;
    }

    public String showHand(String input) {
        String dealerMessage = "Dealer Hand:" + getHandMessage(dealerHand) + "\nSum : " + getScore(dealerHand);
        String playerMessage = "\nYour Hand:" + getHandMessage(playerHand) + "\nSum : " + getScore(playerHand);
        String response = JOptionPane.showInputDialog(null, dealerMessage + "\n" + playerMessage + "\n\n" + input);
        return response;
    }

    private String endGame() {
        String play = "";
        // Both Bust
        if (getScore(playerHand) > 21 && getScore(dealerHand) > 21) {
            play = showHand("TIE - press enter to play again or 0 to quit");
        }
        // Only Dealer Busts
        else if (getScore(playerHand) < 21 && getScore(dealerHand) > 21) {
            play = showHand("YOU WIN! - press enter to play again or 0 to quit");
        }
        // Only You Bust
        else if (getScore(playerHand) > 21 && getScore(dealerHand) < 21) {
            play = showHand("YOU LOSE! - press enter to play again or 0 to quit");
        }
        // Both Don't Bust
        else if (getScore(playerHand) < 21 && getScore(dealerHand) < 21) {
            // You have Lower
            if (getScore(playerHand) < getScore(dealerHand)) {
                play = showHand("YOU LOSE! - press enter to play again or 0 to quit");
            }
            // You have Higher
            else {
                play = showHand("YOU WIN! - press enter to play again or 0 to quit");
            }
        }
        // You Don't Bust But Dealer Gets BlackJack
        else if (getScore(playerHand) < 21 && getScore(dealerHand) == 21) {
            play = showHand("YOU LOSE! - press enter to play again or 0 to quit");
        }
        // You both get BlackJack
        else if (getScore(playerHand) == 21 && getScore(dealerHand) == 21) {
            play = showHand("BLACK JACK, BUT YOU TIED! - press enter to play again or 0 to quit");
        }
        // Only You Get BlackJack
        else if (getScore(playerHand) == 21 && getScore(dealerHand) != 21) {
            play = showHand("BLACK JACK, YOU WIN! - press enter to play again or 0 to quit");
        }
        // Only Dealer Get BlackJack
        else if (getScore(playerHand) != 21 && getScore(dealerHand) == 21) {
            play = showHand("DEALER GOT BLACK JACK, YOU LOST! - press enter to play again or 0 to quit");
        }
        // Tie score
        else if (getScore(playerHand) == getScore(dealerHand)) {
            play = showHand("TIE, YOU GOT THE SAME SCORE! - press enter to play again or 0 to quit");
        }
        return play;
    }
}
