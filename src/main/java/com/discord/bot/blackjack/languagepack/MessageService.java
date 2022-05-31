package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public interface MessageService {

    String commandList();

    String playerHandMsg(Hand hand);

    String finishTurn(int playerScore, int computerScore, Hand computerHand);

    String computerScoreMsg(int score, Hand hand);

    String finishTurnMsg(int score);

    String terminateGameActionMsg();

    String scoreExceededMsg();

    String resultMsg(int result);

    String existingGameErrorMsg();

    String noActiveGameErrorMsg();

    String wrongCommandErrorMsg();

}
