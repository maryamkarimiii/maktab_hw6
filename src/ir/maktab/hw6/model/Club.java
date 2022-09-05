package ir.maktab.hw6.model;

import java.util.List;

public class Club {
    private String name;
    private String manager;
    private List<Player>players;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesEquality;

    static class Player{
     private String name;
     private String family;
     private String natinalCode;
     private String moblie;

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

       public String getMoblie() {
           return moblie;
       }

       public void setMoblie(String moblie) {
           this.moblie = moblie;
       }

       @Override
       public String toString() {
           return "Player{" +
                   "name='" + name + '\'' +
                   ", family='" + family + '\'' +
                   ", natinalCode='" + natinalCode + '\'' +
                   ", moblie='" + moblie + '\'' +
                   '}';
       }
   }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
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

    public int getGamesEquality() {
        return gamesEquality;
    }

    public void setGamesEquality(int gamesEquality) {
        this.gamesEquality = gamesEquality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", players=" + players +
                ", gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                ", gamesEquality=" + gamesEquality +
                '}';
    }

}
