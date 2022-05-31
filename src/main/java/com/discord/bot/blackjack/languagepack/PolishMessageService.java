package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public class PolishMessageService implements MessageService {
    @Override
    public String commandList() {
        return """
                Lista komend:
                !bj start - rozpocznij nową gre
                !bj draw  - dobierz kartę
                !bj pass - zakończ swoją turę z obecnymi kartami
                !bj stop - zakończ proces aktywnej gry
                """;
    }

    @Override
    public String playerHandMsg(Hand hand) {
        return "Twoje karty:\n" + hand.getHandDescription();
    }

    @Override
    public String finishTurn(int playerScore, int computerScore, Hand computerHand) {
        return null;
    }

    @Override
    public String computerScoreMsg(int score, Hand hand) {
        return "AI uzyskało " + score + " z kartami:\n" + hand.getHandDescription();
    }

    @Override
    public String finishTurnMsg(int score) {
        return "Zakończyłeś swoją turę.\nTwój wynik: " + score;
    }

    @Override
    public String terminateGameActionMsg() {
        return "Aktywna gra została zakończona.";
    }

    @Override
    public String scoreExceededMsg() {
        return "Twój wynik przekroczył 21.\nPRZEGRAŁEŚ";
    }

    @Override
    public String resultMsg(int result) {
        if(result == 1){
            return "WYGRAŁEŚ";
        } else if (result == 0) {
            return "REMIS";
        } else {
            return "PRZEGRAŁEŚ";
        }
    }

    @Override
    public String existingGameErrorMsg() {
        return "Musisz zakończyć swoją obecną gre zanim zaczniesz nową.";
    }

    @Override
    public String noActiveGameErrorMsg() {
        return "Żadna gra się teraz nie odbywa.";
    }

    @Override
    public String wrongCommandErrorMsg() {
        return "Nieznana komenda. Wpisz !bj aby uzyskać listę komend.";
    }
}
