package com.discord.bot.blackjack;

import com.discord.bot.blackjack.languagepack.MessageService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Blackjack {

    private final Map<Integer, BlackjackLogic> activeGames = new HashMap<>();
    //TODO: Ogarnac jaki rodzaj mapy bedzie dobry na zakolejkowane gry. albo lista.
    //private final Map<>
    private final MessageService msgService;
    private final JDA jda;
    private boolean isMultiplayerRequest = false;
    private User challengedUser;
    private int newGameCounter = 0;

    public Blackjack(MessageService msgService, JDA jda) {
        this.msgService = msgService;
        this.jda = jda;
    }

    public String userInput(String id, String message) {
        long authorId = castIdToLong(id);
        if (message.equalsIgnoreCase("!bj")) {
            return msgService.commandList();
        } else if (message.startsWith("!bj")) {
            String[] words = message.split(" ");
            if (gameExists(authorId)) {
                return switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "start" -> msgService.existingGameErrorMsg();
                    case "pass" -> singleplayerFinishTurn(authorId);
                    case "draw" -> drawNextCard(authorId);
                    case "stop" -> terminateGame(authorId);
                    default -> msgService.wrongCommandErrorMsg();
                };
            } else {
                return switch (words[1].toLowerCase(Locale.ROOT)) {
                    case "rules" -> msgService.displayRules();
                    case "start" -> createNewGame(authorId);
                    case "challenge" -> {
                        if (words.length <= 2) {
                            yield "You need to provide a valid user."; //msgService.userNotProvidedMsg();
                        } else {
                            isMultiplayerRequest = true;
                            try{
                                yield challengeUser(authorId, castIdToLong(words[2]));
                            } catch (NumberFormatException | NullPointerException e) {
                                yield "You need to provide a valid user."; //msgService.userNotProvidedMsg();
                            }
                        }
                    }
                    case "stop" -> msgService.noActiveGameErrorMsg();
                    default -> msgService.wrongCommandErrorMsg();
                };
            }
        } else {
            return "";
        }
    }

    public String privateUserInput(String authorId, String message){
        if(message.equalsIgnoreCase("!bj accept")){
            return "ok";
        } else {
            return msgService.wrongCommandErrorMsg();
        }
    }

    public String setPrivateMessage(String authorId){
        if(isMultiplayerRequest){
            return "You've been challenged to a game by " + Objects.requireNonNull(jda.getUserById(authorId)).getName() +
                    "\nType !bj accept to start a game";
        } else {
            return "";
        }
    }

    public User getChallengedUser() throws NoSuchUserException {
        try{
            return challengedUser;
        } catch (NullPointerException e){
            throw new NoSuchUserException("The user does not exist.");
        }
    }

    public String getChallengedUserName(){
        if(challengedUser == null){
            return "";
        } else {
            return challengedUser.getName();
        }

    }

    private String challengeUser(long authorId, long challenger) {
        if(isValidID(challenger)){
            challengedUser = getUserById(challenger);
            //createNewMultiplayerGame(authorId, challenger);
            return "A game request has been sent.";
        } else {
            return "User does not exist";
        }
    }

    private boolean isValidID(long id){
        try{
            User user = getUserById(id);
            return user != null;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    private User getUserById(long id) {
        return jda.getUserById(id);
    }

    private long castIdToLong(String id){
            return Long.parseLong(id.substring(2, id.length()-1));
    }

    private String terminateGame(long authorId) {
        activeGames.remove(getGameIdByAuthorId(authorId));
        return msgService.terminateGameActionMsg();
    }

    private String drawNextCard(long authorId) {
        BlackjackLogic activeBlackjackLogic = activeGames.get(getGameIdByAuthorId(authorId));
        activeBlackjackLogic.drawCard(activeBlackjackLogic.getDeck(), activeBlackjackLogic.getHands().get(activeBlackjackLogic.getIndexByAuthorId(authorId)));
        if (activeBlackjackLogic.isPlayerScoreOver21(activeBlackjackLogic.getHands().get(activeBlackjackLogic.getIndexByAuthorId(authorId)))) {
            activeGames.remove(getGameIdByAuthorId(authorId));
            return msgService.scoreExceededMsg(activeBlackjackLogic.getHands().get(activeBlackjackLogic.getIndexByAuthorId(authorId)));
        } else {
            return msgService.playerHandMsg(activeBlackjackLogic.getHands().get(activeBlackjackLogic.getIndexByAuthorId(authorId)));
        }
    }

    private String singleplayerFinishTurn(long authorId) {
        BlackjackLogic activeBlackjackLogic = activeGames.get(getGameIdByAuthorId(authorId));
        activeBlackjackLogic.finishTurn(authorId);
        activeGames.remove(getGameIdByAuthorId(authorId));
        return msgService.finishTurnMsg(activeBlackjackLogic.singleplayerResult(),
                activeBlackjackLogic.getHands().get(0).getScore(),
                activeBlackjackLogic.getHands().get(1).getScore(),
                activeBlackjackLogic.getHands().get(1));
    }

    private String createNewGame(long authorId) {
        activeGames.put(newGameCounter, new BlackjackLogic(authorId));
        newGameCounter++;
        activeGames.get(getGameIdByAuthorId(authorId)).startGame();
        return msgService.playerHandMsg(activeGames.get(getGameIdByAuthorId(authorId)).getHands().get(0));
    }

//    private void createNewMultiplayerGame(long authorId,) {
//
//        activeGames.put(authorId, new BlackjackLogic());
//        activeGames.get(authorId).startGame();
//    }

    private boolean gameExists(long authorId) {
        return activeGames.containsKey(getGameIdByAuthorId(authorId));
    }

    private int getGameIdByAuthorId(long authorId){
        for(int key : activeGames.keySet()){
            if(activeGames.get(key).getAuthorDiscordId() == authorId){
                return key;
            }
        }
        return -2;
    }

}
