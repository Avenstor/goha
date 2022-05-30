package com.discord.bot.blackjack;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class BlackjackListener extends GenericMessageListener {

    private final Map<String, Game> activeGames = new HashMap<>();

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!bj")) {
            sendMessage(event, """
                    List of commands:
                    !bj start - starts a new game against AI
                    !bj draw - draw a card
                    !bj pass - stay with your current cards
                    !bj stop - stop an existing game if it got lost somewhere in the chat""");
        } else if (message.startsWith("!bj")) {
            String temp = getDiscordMessage(event);
            String authorId = getAuthorId(event);
            String[] words = temp.split(" ");
            if (words[1].equalsIgnoreCase("start") && !gameExists(authorId)) {
                activeGames.put(authorId, new Game());
                activeGames.get(authorId).startGame();
                sendMessage(event, "Your hand: \n" + activeGames.get(authorId).getPlayerHand().getHandDescription());
            } else if (words[1].equalsIgnoreCase("start") && gameExists(authorId)){
                sendMessage(event, "You need to finish your current game before starting a new one.");
            } else if (words[1].equalsIgnoreCase("pass") && gameExists(authorId)) {
                activeGames.get(authorId).setPlayerScore(activeGames.get(authorId).calculateScore(activeGames.get(authorId).getPlayerHand()));
                sendMessage(event, "You have passed your turn.\nYour score: " + activeGames.get(authorId).getPlayerScore());
                activeGames.get(authorId).computerBehavior(activeGames.get(authorId).getDeck(), activeGames.get(authorId).getComputerHand());
                activeGames.get(authorId).setComputerScore(activeGames.get(authorId).calculateScore(activeGames.get(authorId).getComputerHand()));
                sendMessage(event, "The AI has scored " + activeGames.get(authorId).getComputerScore() + " with following cards:\n" + activeGames.get(authorId).getComputerHand().getHandDescription());
                sendMessage(event, activeGames.get(authorId).result(activeGames.get(authorId).getPlayerScore(), activeGames.get(authorId).getComputerScore()));
                activeGames.remove(authorId);
            } else if (words[1].equalsIgnoreCase("draw") && gameExists(authorId)) {
                activeGames.get(authorId).drawCard(activeGames.get(authorId).getDeck(), activeGames.get(authorId).getPlayerHand());
                sendMessage(event, "Your hand: \n" + activeGames.get(authorId).getPlayerHand().getHandDescription());
                if(activeGames.get(authorId).calculateScore(activeGames.get(authorId).getPlayerHand()) > 21){
                    sendMessage(event, "Your score exceeded 21.\nYOU LOSE");
                    activeGames.remove(authorId);
                }
            } else if (words[1].equalsIgnoreCase("stop") && gameExists(authorId)){
                activeGames.remove(authorId);
                sendMessage(event, "Your active game has been terminated.");
            } else if (words[1].equalsIgnoreCase("stop") && !gameExists(authorId)){
                sendMessage(event, "There is no on-going game.");
            }else {
                sendMessage(event, "Wrong command. Type !bj for command list.");
            }

        }
    }

    private boolean gameExists(String authorId){
        return activeGames.containsKey(authorId);
    }

}
