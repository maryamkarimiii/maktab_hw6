package ir.maktab.hw6.model;

import java.util.Date;
import java.util.List;

public class League {
    private String leagueName;
    private Date leagueBeginner;
    private List<Club> clubs;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Date getLeagueBeginner() {
        return leagueBeginner;
    }

    public void setLeagueBeginner(Date leagueBeginner) {
        this.leagueBeginner = leagueBeginner;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
