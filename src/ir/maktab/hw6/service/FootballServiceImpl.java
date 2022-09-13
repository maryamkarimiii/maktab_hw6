package ir.maktab.hw6.service;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;
import ir.maktab.hw6.repository.FootballRepository;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.sql.SQLException;
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
    public void compete(Club clubA, Club clubB) throws SQLException {
        int goalA = ((FootballClub) clubA).getGoal();
        int goalB = ((FootballClub) clubB).getGoal();
        if (goalA > goalB) {
            clubA.setCompetitionResult(CompetitionResult.WIN);
            clubB.setCompetitionResult(CompetitionResult.LOSE);
        }
        if (goalB > goalA) {
            clubB.setCompetitionResult(CompetitionResult.WIN);
            clubA.setCompetitionResult(CompetitionResult.LOSE);
        }
        if (goalA == goalB) {
            clubA.setCompetitionResult(CompetitionResult.DRAW);
            clubB.setCompetitionResult(CompetitionResult.DRAW);
        }
        footballRepository.updateRecordAfterCompetition((FootballClub) clubA, (FootballClub) clubB);
        clubA = findClub(clubA.getClubName());
        clubB = findClub(clubB.getClubName());
        clubA.setScore(calculateScore(clubA));
        clubB.setScore(calculateScore(clubB));
        footballRepository.updateScore((FootballClub) clubA, (FootballClub) clubB);
    }

    @Override
    public int calculateScore(Club club) {
        int score = club.getGamesWon() * 3 + ((FootballClub) club).getDrawnGame() * 1;
        club.setScore(score);
        return score;
    }

    @Override
    public List<Club> showCompeteTable() throws SQLException{
          return sortByScore(footballRepository.showFootballTableInfo());
    }


    private List<Club> sortByScore(List<Club> clubs) {
        clubs.sort(new Comparator<Club>() {
            @Override
            public int compare(Club o1, Club o2) {
                return Integer.compare(o1.getScore(),o2.getScore());
            }
        });
        return clubs;
    }
}
