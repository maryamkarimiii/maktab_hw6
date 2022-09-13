package ir.maktab.hw6.model;

import ir.maktab.hw6.model.enums.CompetitionResult;

import java.util.List;
import java.util.Objects;


public class Club {
    private String clubName;
    private int matchPlays;
    private int gamesWon;
    private int gamesLost;
    private int score;
    private CompetitionResult competitionResult;
    private List<Player> players;

    public Club() {
    }

    public Club(String clubName) {
        this.clubName = clubName;
    }

    public Club(String clubName, int gamesPlayed, int gamesWon, int gamesLost, int score) {
        this.clubName = clubName;
        this.matchPlays = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.score = score;
    }


    static class Player {
        private String name;
        private String family;
        private String natinalCode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getNatinalCode() {
            return natinalCode;
        }

        public void setNatinalCode(String natinalCode) {
            this.natinalCode = natinalCode;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", family='" + family + '\'' +
                    ", natinalCode='" + natinalCode + '\'' +
                    '}';
        }
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getMatchPlays() {
        return matchPlays;
    }

    public void setMatchPlays(int matchPlays) {
        this.matchPlays = matchPlays;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public CompetitionResult getCompetitionResult() {
        return competitionResult;
    }

    public void setCompetitionResult(CompetitionResult competitionResult) {
        this.competitionResult = competitionResult;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return matchPlays == club.matchPlays && gamesWon == club.gamesWon && gamesLost == club.gamesLost && score == club.score && Objects.equals(clubName, club.clubName) && competitionResult == club.competitionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, matchPlays, gamesWon, gamesLost, score, competitionResult);
    }

    @Override
    public String toString() {
        return clubName + ":" +
                "clubName='" + clubName + '\'' +
                ", matchPlays=" + matchPlays +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                ", score=" + score;
    }
}
