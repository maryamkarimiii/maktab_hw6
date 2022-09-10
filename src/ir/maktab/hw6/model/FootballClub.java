package ir.maktab.hw6.model;

import ir.maktab.hw6.model.enums.CompetitionResult;

public class FootballClub extends Club {
    private int drawnGame;
    private int gole;


    public FootballClub() {
    }

    public FootballClub(String clubName, int matchPlays, int gamesWon, int gamesLost, int score, int drawnGame) {
        super(clubName, matchPlays, gamesWon, gamesLost, score);
        this.drawnGame = drawnGame;
    }

    public int getGole() {
        return gole;
    }

    public void setGole(int gole) {
        this.gole = gole;
    }

    public int getDrawnGame() {
        return drawnGame;
    }

    public void setDrawnGame(int drawnGame) {
        this.drawnGame = drawnGame;
    }
}
