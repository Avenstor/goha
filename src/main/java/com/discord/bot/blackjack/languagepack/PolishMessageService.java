package com.discord.bot.blackjack.languagepack;

import com.discord.bot.blackjack.Hand;

public class PolishMessageService implements MessageService {
    @Override
    public String commandList() {
        return """
                Lista komend:
                !bj rules - wyświetl zasady gry
                !bj start - rozpocznij nową gre
                !bj draw  - dobierz kartę
                !bj pass - zakończ swoją turę z obecnymi kartami
                !bj stop - zakończ proces aktywnej gry
                """;
    }

    @Override
    public String displayRules() {
        return """
                Celem gry jest zebranie 21 punktów.
                Możesz jedynie dobierać nowe karty lub pozostać z obecnymi passując.
                Jeśli masz więcej niż 21 punktów - przegrywasz.
                Jeśli nikt nie ma 21 punktów, osoba z wynikiem bliższym do 21 wygrywa.
                Karty numeryczne mają tą samą ilość punktów przypisaną do nich. DWÓJKA ma 2 punkty, TRÓJKA ma 3 punkty itd.
                Walet = 2 pkt, Królowa = 3 pkt, Król = 4 pkt, As = 11 pkt
                """;
    }

    @Override
    public String playerHandMsg(Hand hand) {
        return "Twoje karty:\n" + hand.getHandDescription();
    }

    @Override
    public String finishTurnMsg(int result, int playerScore, int computerScore, Hand computerHand) {
        String message = "Zakończyłeś swoją turę.\nTwój wynik: " + playerScore
                + "\nAI uzyskało " + computerScore
                + " z kartami:\n" + computerHand.getHandDescription() + "\n";
        StringBuilder sb = new StringBuilder(message);
        if(result == 1){
            sb.append("WYGRAŁEŚ");
        } else if (result == 0) {
            sb.append("REMIS");
        } else {
            sb.append("PRZEGRAŁEŚ");
        }
        return sb.toString();
    }

    @Override
    public String terminateGameActionMsg() {
        return "Aktywna gra została zakończona.";
    }

    @Override
    public String scoreExceededMsg(Hand hand) {
        return playerHandMsg(hand) + "\nTwój wynik przekroczył 21.\nPRZEGRAŁEŚ";
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
