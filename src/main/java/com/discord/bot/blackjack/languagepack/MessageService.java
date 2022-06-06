package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public interface MessageService {

    String commandList();

    String displayRules();

    String playerHandMsg(Hand hand);

    String finishTurnMsg(int result, int playerScore, int computerScore, Hand computerHand);

    String terminateGameActionMsg();

    String scoreExceededMsg(Hand hand);

    String existingGameErrorMsg();

    String noActiveGameErrorMsg();

    String wrongCommandErrorMsg();

}
