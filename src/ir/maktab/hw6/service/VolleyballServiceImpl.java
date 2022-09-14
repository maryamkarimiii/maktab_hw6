package ir.maktab.hw6.service;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.VolleyballClub;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.util.List;

public class VolleyballServiceImpl implements LeagueService {

    @Override
    public String addClub(String clubName) {
        return "j";
    }

    @Override
    public String removeClub(String clubName) {
        return "";
    }

    @Override
    public void compete(Club clubA, Club clubB) {

    }

    @Override
    public int calculateScore(Club club) {
        return 0;
    }

    @Override
    public List<Club> showCompeteTable() {
       List<Club> club=null;
       return club;
    }
}
