package com.discord.bot.blackjack;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class BlackjackListener extends GenericMessageListener {

    private Game game = null;

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!bj")) {
            sendMessage(event, """
                    List of commands:
                    !bj start - starts a new game against AI
                    !bj draw - draw a card
                    !bj pass - stay with your current cards""");
        } else if (message.startsWith("!bj")) {
            String temp = getDiscordMessage(event);
            String[] words = temp.split(" ");
            if (words[1].equalsIgnoreCase("start")) {
                game = new Game();
                game.startGame();
                sendMessage(event, "Your hand: \n" + game.showHand(game.getPlayerHand()));
            } else if (words[1].equalsIgnoreCase("pass") && game != null) {
                game.setPlayerScore(game.calculateScore(game.getPlayerHand()));
                sendMessage(event, "You have passed your turn.\nYour score: " + game.getPlayerScore());
                game.computerBehavior(game.getDeck(), game.getComputerHand());
                game.setComputerScore(game.calculateScore(game.getComputerHand()));
                sendMessage(event, "The AI has scored " + game.getComputerScore() + " with following cards:\n" + game.showHand(game.getComputerHand()));
                sendMessage(event, game.result(game.getPlayerScore(), game.getComputerScore()));
                game = null;
            } else if (words[1].equalsIgnoreCase("draw") && game != null) {
                game.drawCard(game.getDeck(), game.getPlayerHand());
                sendMessage(event, "Your hand: \n" + game.showHand(game.getPlayerHand()));
                if(game.calculateScore(game.getPlayerHand()) > 21){
                    sendMessage(event, "Your score exceeded 21.\nYOU LOSE");
                    game = null;
                }
            } else {
                sendMessage(event, "Wrong command. Type !bj for command list.");
            }

        }
    }


}
