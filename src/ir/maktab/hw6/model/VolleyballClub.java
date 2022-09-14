package ir.maktab.hw6.model;

public class VolleyballClub extends Club {
    private int winSets;
    private int setsScores;

    public VolleyballClub(String clubName, int gamesPlayed, int gamesWon, int gamesLost, int score, int winSets,int setsScores) {
        super(clubName, gamesPlayed, gamesWon, gamesLost, score);
        this.winSets = winSets;
        this.setsScores=setsScores;
    }
}
