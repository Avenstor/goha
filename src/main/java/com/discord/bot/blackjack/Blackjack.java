package com.discord.bot.blackjack;

import com.discord.bot.blackjack.languagepack.MessageService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Blackjack {

    private final Map<String, BlackjackLogic> activeGames = new HashMap<>();
    private final MessageService msgService;

    public Blackjack(MessageService msgService) {
        this.msgService = msgService;
    }

    public String userInput(String authorId, String userInput){
        if (userInput.equalsIgnoreCase("!bj")) {
            return msgService.commandList();
        } else if (userInput.startsWith("!bj")) {
            String[] words = userInput.split(" ");
            if (gameExists(authorId)) {
                return switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "start" -> msgService.existingGameErrorMsg();
                    case "pass" -> finishTurn(authorId);
                    case "draw" -> drawNextCard(authorId);
                    case "stop" -> terminateGame(authorId);
                    default -> msgService.wrongCommandErrorMsg();
                };
            } else {
                return switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "rules" -> msgService.displayRules();
                    case "start" -> createNewGame(authorId);
                    case "stop" -> msgService.noActiveGameErrorMsg();
                    default -> msgService.wrongCommandErrorMsg();
                };
            }
        } else {
            return "";
        }
    }

    private String terminateGame(String authorId) {
        activeGames.remove(authorId);
        return msgService.terminateGameActionMsg();
    }

    private String drawNextCard(String authorId) {
        BlackjackLogic activeBlackjackLogic = activeGames.get(authorId);
        activeBlackjackLogic.drawCard(activeBlackjackLogic.getDeck(), activeBlackjackLogic.getPlayerHand());
        if (activeBlackjackLogic.isPlayerScoreOver21()){
            activeGames.remove(authorId);
            return msgService.scoreExceededMsg(activeBlackjackLogic.getPlayerHand());
        } else {
            return msgService.playerHandMsg(activeBlackjackLogic.getPlayerHand());
        }
    }

    private String finishTurn(String authorId) {
        BlackjackLogic activeBlackjackLogic = activeGames.get(authorId);
        activeBlackjackLogic.finishTurn();
        activeGames.remove(authorId);
        return msgService.finishTurnMsg(activeBlackjackLogic.result(), activeBlackjackLogic.getPlayerScore(), activeBlackjackLogic.getComputerScore(), activeBlackjackLogic.getComputerHand());
    }

    private String createNewGame(String authorId) {
        activeGames.put(authorId, new BlackjackLogic());
        activeGames.get(authorId).startGame();
        return msgService.playerHandMsg(activeGames.get(authorId).getPlayerHand());
    }

    private boolean gameExists(String authorId) {
        return activeGames.containsKey(authorId);
    }

}
