package ir.maktab.hw6.service;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;
import ir.maktab.hw6.repository.FootballRepository;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FootballServiceImpl implements LeagueService {
    private static FootballServiceImpl footballService;

    private FootballServiceImpl() {
    }

    public static FootballServiceImpl getInstance() {
        if (footballService == null)
            footballService = new FootballServiceImpl();
        return footballService;
    }

    FootballRepository footballRepository = FootballRepository.getInstance();

    @Override
    public String addClub(String clubName) throws SQLException {
        String result = "";
        if (!footballRepository.isExistClub(clubName)) {
            footballRepository.addClub(clubName);
            result += "add club be successful";
            return result;
        } else {
            result += "not successful,the club name already exist";
            return result;
        }
    }

    @Override
    public String removeClub(String clubName) throws SQLException {
        if (footballRepository.isExistClub(clubName)) {
            footballRepository.deleteClub(clubName);
            return "delete club successfully";
        } else return "delete not be successful,the club isn't exist";
    }

    public FootballClub findClub(String name) throws SQLException {
        if (footballRepository.isExistClub(name))
            return footballRepository.selectFootballClub(name);
        else return null;
    }

    @Override
    public void competeAndCalculateScore(Club clubA, Club clubB) throws SQLException {
        changeMathPlaysAfterGame(clubA, clubB);
        int result = Integer.compare(((FootballClub) clubA).getGoal(), ((FootballClub) clubB).getGoal());
        if (result == 0) {
            changeAfterDrawn(clubA);
            changeAfterDrawn(clubB);
            footballRepository.updateDrawnClubs(((FootballClub) clubA), ((FootballClub) clubB));
        } else if (result > 0) {
            changeAfterWin(clubA);
            changeAfterLost(clubB);
        } else {
            changeAfterWin(clubB);
            changeAfterLost(clubA);
        }
        footballRepository.updateScore(clubA, clubB);
    }

    public int calculateScore(Club club) {
        int score = club.getGamesWon() * 3 + ((FootballClub) club).getDrawnGame() * 1;
        club.setScore(score);
        return score;
    }

    public void changeAfterWin(Club club) throws SQLException {
        club.setCompetitionResult(CompetitionResult.WIN);
        club.setGamesWon(club.getGamesWon() + 1);
        club.setScore(club.getScore() + 3);
        footballRepository.updateWinClub(club);
    }

    public void changeAfterLost(Club club) throws SQLException {
        club.setCompetitionResult(CompetitionResult.LOSE);
        club.setGamesLost(club.getGamesLost() + 1);
        footballRepository.updateLoseClub(club);
    }

    public void changeAfterDrawn(Club club) {
        club.setCompetitionResult(CompetitionResult.DRAW);
        ((FootballClub) club).setDrawnGame(((FootballClub) club).getDrawnGame() + 1);
        club.setScore(club.getScore() + 1);
    }

    public void changeMathPlaysAfterGame(Club clubA, Club clubB) throws SQLException {
        clubA.setMatchPlays(clubA.getMatchPlays() + 1);
        clubB.setMatchPlays(clubB.getMatchPlays() + 1);
        footballRepository.updateMatchPlays(clubA, clubB);
    }

    @Override
    public List<Club> showCompeteTable() throws SQLException {
        return sortByScore(footballRepository.showFootballTableInfo());
    }


    private List<Club> sortByScore(List<Club> clubs) {
        Collections.sort(clubs, Collections.reverseOrder(new Comparator<Club>() {
            @Override
            public int compare(Club o1, Club o2) {
                return Integer.compare(o1.getScore(), o2.getScore());
            }
        }));
        return clubs;
    }
}
