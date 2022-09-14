package ir.maktab.hw6.model;

public class VolleyballClub extends Club {
    private int winSets;
    private int setsScores;

    public VolleyballClub(String clubName, int gamesPlayed, int gamesWon, int gamesLost, int score, int winSets,int setsScores) {
        super(clubName, gamesPlayed, gamesWon, gamesLost, score);
        this.winSets = winSets;
        this.setsScores=setsScores;
    }

    public VolleyballClub(String clubName, int gamesPlayed, int gamesWon, int gamesLost, int score) {
        super(clubName, gamesPlayed, gamesWon, gamesLost, score);
    }

    public int getWinSets() {
        return winSets;
    }

    public void setWinSets(int winSets) {
        this.winSets = winSets;
    }

    public int getSetsScores() {
        return setsScores;
    }

    public void setSetsScores(int setsScores) {
        this.setsScores = setsScores;
    }
}
