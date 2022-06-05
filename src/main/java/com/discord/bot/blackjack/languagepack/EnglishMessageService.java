package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public class EnglishMessageService implements MessageService {

    @Override
    public String commandList() {
        return """
                    List of commands:
                    !bj start - starts a new game against AI
                    !bj draw - draw a card
                    !bj pass - stay with your current cards
                    !bj stop - stop an existing game if it got lost somewhere in the chat""";
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
