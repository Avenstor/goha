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
    public String finishTurn(int playerScore, int computerScore, Hand computerHand) {


        return null;
    }

    @Override
    public String computerScoreMsg(int score, Hand hand) {
        return "The AI has scored " + score + " with following cards:\n" + hand.getHandDescription();
    }

    @Override
    public String finishTurnMsg(int score) {
        return "You have passed your turn.\nYour score: " + score;
    }

    @Override
    public String terminateGameActionMsg() {
        return "Your active game has been terminated.";
    }

    @Override
    public String scoreExceededMsg() {
        return "Your score exceeded 21.\nYOU LOSE";
    }

    @Override
    public String resultMsg(int result) {
        if(result == 1){
            return "YOU WIN";
        } else if (result == 0) {
            return "DRAW";
        } else {
            return "YOU LOSE";
        }
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
