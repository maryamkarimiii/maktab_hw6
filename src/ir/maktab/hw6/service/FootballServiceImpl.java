package ir.maktab.hw6.service;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.repository.FootballLeagueRepository;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.sql.SQLException;

public class FootballServiceImpl implements LeagueService {
    FootballLeagueRepository footballLeagueRepository=FootballLeagueRepository.getInstance();
    @Override
    public String addClub(String clubName) throws SQLException {

       if(footballLeagueRepository.addFootballClub(clubName))
           return "add club be successful";
       else return "not successful";
    }

    @Override
    public String removeClub(String clubName) throws SQLException {
       if(footballLeagueRepository.deleteClub(clubName))
           return "delete club successfully";
       else  return "delete not be successful";

    }

    @Override
    public void compete() {
        footballLeagueRepository.flag=true;
    }

    @Override
    public int calculateScore(Club club ) {
        club=new FootballClub();
        int score = club.getGamesWon() * 3 + ((FootballClub) club).getDrawnGame() * 1;
        club.setScore(score);
        return score;
    }

    @Override
    public void showCompeteTable() throws SQLException {
        footballLeagueRepository.showFootballTableInfo();
    }
}
