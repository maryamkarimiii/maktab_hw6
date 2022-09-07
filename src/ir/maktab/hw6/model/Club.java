package ir.maktab.hw6.model;

import java.util.List;

public class Club {
    private String clubName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesEquality;
    private List<Player> players;

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




}
