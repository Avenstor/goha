package com.discord.bot.blackjack;

import com.discord.bot.GenericMessageListener;
import com.discord.bot.blackjack.languagepack.MessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BlackjackListener extends GenericMessageListener {

    private final Map<String, Game> activeGames = new HashMap<>();
    private final MessageService msgService;

    public BlackjackListener(MessageService msgService) {
        this.msgService = msgService;
    }

    //TODO:
    // - jedno wywołanie `sendMessage(event, discordMessage)`
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (event.getAuthor().isBot()) {
            return;
        }
        if (message.equalsIgnoreCase("!bj")) {
            sendMessage(event, msgService.commandList());
        } else if (message.startsWith("!bj")) {
            String authorId = getAuthorId(event);
            String temp = getDiscordMessage(event);
            String[] words = temp.split(" ");
            String discordMessage;
            if (gameExists(authorId)) {
                discordMessage = switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "start" -> msgService.existingGameErrorMsg();
                    case "pass" -> finishTurn(authorId);
                    case "draw" -> drawCard(authorId);
                    case "stop" -> terminateGame(authorId);
                    default -> msgService.wrongCommandErrorMsg();
                };
                sendMessage(event, discordMessage);
            } else {
                discordMessage = switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "start" -> createNewGame(authorId);
                    case "stop" -> terminateGame(authorId);
                    default -> msgService.wrongCommandErrorMsg();
                };
                sendMessage(event, discordMessage);
            }
        }
    }

    private boolean gameExists(String authorId) {
        return activeGames.containsKey(authorId);
    }

    private String createNewGame(String authorId) {
        activeGames.put(authorId, new Game());
        activeGames.get(authorId).startGame();
        return msgService.playerHandMsg(activeGames.get(authorId).getPlayerHand());
    }

    private String finishTurn(String authorId) {
        //TODO: dlaczego mamy tutaj 3 wywołania na obiekcie game, zoptymalizować
        activeGames.get(authorId).setPlayerScore(activeGames.get(authorId).calculateScore(activeGames.get(authorId).getPlayerHand()));
        activeGames.get(authorId).computerBehavior(activeGames.get(authorId).getDeck(), activeGames.get(authorId).getComputerHand());
        activeGames.get(authorId).setComputerScore(activeGames.get(authorId).calculateScore(activeGames.get(authorId).getComputerHand()));
        activeGames.remove(authorId);
        return msgService.finishTurnMsg(activeGames.get(authorId).getPlayerScore()) + "\n" +
                msgService.computerScoreMsg(activeGames.get(authorId).getComputerScore(), activeGames.get(authorId).getComputerHand()) + "\n" +
                msgService.resultMsg(activeGames.get(authorId).result());
    }

    private String drawCard(String authorId) {
        //TODO: dlaczego mamy tutaj 2 wywołania na obiekcie game, zoptymalizować
        activeGames.get(authorId).drawCard(activeGames.get(authorId).getDeck(), activeGames.get(authorId).getPlayerHand());
        if (activeGames.get(authorId).calculateScore(activeGames.get(authorId).getPlayerHand()) > 21) {
            activeGames.remove(authorId);
        }
        return msgService.playerHandMsg(activeGames.get(authorId).getPlayerHand()) + "\n" +
                msgService.scoreExceededMsg();
    }

    private String terminateGame(String authorId) {
        activeGames.remove(authorId);
        return msgService.terminateGameActionMsg();
    }
}
