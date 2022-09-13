package ir.maktab.hw6.model;

import java.util.Objects;

public class FootballClub extends Club {
    private int drawnGame;
    private int goal;


    public FootballClub() {
    }

    public FootballClub(String clubName, int matchPlays, int gamesWon, int gamesLost, int score, int drawnGame) {
        super(clubName, matchPlays, gamesWon, gamesLost, score);
        this.drawnGame = drawnGame;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getDrawnGame() {
        return drawnGame;
    }

    public void setDrawnGame(int drawnGame) {
        this.drawnGame = drawnGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return drawnGame == that.drawnGame && goal == that.goal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), drawnGame, goal);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", drawnGame=" + drawnGame
                + "\n";
    }
}
