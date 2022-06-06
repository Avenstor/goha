package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public class EnglishMessageService implements MessageService {

    @Override
    public String commandList() {
        return """
                    List of commands:
                    !bj rules - display rules of the game
                    !bj start - starts a new game against AI
                    !bj draw - draw a card
                    !bj pass - stay with your current cards
                    !bj stop - stop an existing game if it got lost somewhere in the chat""";
    }

    @Override
    public String displayRules() {
        return """
                The objective of the game is to get 21 points.
                You can only draw new cards or pass your turn with current score.
                If you have more points than 21, you automatically lose.
                If nobody has 21 points, the person with higher score wins.
                Cards like TWO, THREE have the same value as their name; TWO has 2 points, THREE has 3 points.
                Jack = 2 points, Queen = 3 points, King = 4 points, Ace = 11 points
                """;
    }

    @Override
    public String playerHandMsg(Hand hand) {
        return "Your hand:\n" + hand.getHandDescription();
    }

    @Override
    public String finishTurnMsg(int result, int playerScore, int computerScore, Hand computerHand) {
        String message = "You have passed your turn.\nYour score: " + playerScore
                + "\nThe AI has scored " + computerScore
                + " with following cards:\n" + computerHand.getHandDescription() + "\n";
        StringBuilder sb = new StringBuilder(message);
        if(result == 1){
            sb.append("YOU WIN");
        } else if (result == 0) {
            sb.append("DRAW");
        } else {
            sb.append("YOU LOSE");
        }
        return sb.toString();
    }

    @Override
    public String terminateGameActionMsg() {
        return "Your active game has been terminated.";
    }

    @Override
    public String scoreExceededMsg(Hand hand) {
        return playerHandMsg(hand) + "\nYour score exceeded 21.\nYOU LOSE";
    }

    @Override
    public String existingGameErrorMsg() {
        return "You need to finish your current game before starting a new one.";
    }

    @Override
    public String noActiveGameErrorMsg() {
        return "There is no on-going game.";
    }

    @Override
    public String wrongCommandErrorMsg() {
        return "Wrong command. Type !bj for command list.";
    }
}
