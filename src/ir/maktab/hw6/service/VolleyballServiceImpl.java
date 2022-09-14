package ir.maktab.hw6.service;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.VolleyballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;
import ir.maktab.hw6.repository.VolleyballRepository;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VolleyballServiceImpl implements LeagueService {
    private static LeagueService volleyballService;

    private VolleyballServiceImpl() {
    }

    public static LeagueService getInstance() {
        if (volleyballService == null)
            volleyballService = new VolleyballServiceImpl();
        return volleyballService;
    }

    VolleyballRepository volleyballRepository = VolleyballRepository.getInstance();

    @Override
    public String addClub(String clubName) throws SQLException {
        if (!volleyballRepository.isExistClub(clubName)) {
            volleyballRepository.addClub(clubName);
            return "add club be successful";
        } else return "not successful,the club name already exist";
    }

    @Override
    public String removeClub(String clubName) throws SQLException {
        if (volleyballRepository.isExistClub(clubName)) {
            volleyballRepository.deleteClub(clubName);
            return "delete club successfully";
        } else return "delete not be successful,the club isn't exist";
    }

    public VolleyballClub findClub(String name) throws SQLException {
        if (volleyballRepository.isExistClub(name))
            return volleyballRepository.selectClub(name);
        else return null;
    }

    @Override
    public void competeAndCalculateScore(Club clubA, Club clubB) throws SQLException {
        int countSets = ((VolleyballClub) clubA).getWinSets() + ((VolleyballClub) clubB).getWinSets();
        int compareResult = Integer.compare(((VolleyballClub) clubA).getWinSets(), ((VolleyballClub) clubB).getWinSets());
        if (countSets == 5)
            if (compareResult > 0) {
                updateAfterWin(clubA);
                updateAfterLose(clubB);
                clubA.setScore(clubA.getScore() + 2);
                clubB.setScore(clubB.getScore() + 1);
            } else {
                updateAfterWin(clubB);
                updateAfterLose(clubA);
                clubA.setScore(clubA.getScore() + 1);
                clubB.setScore(clubB.getScore() + 2);
            }
        else {
            if (compareResult > 0) {
                updateAfterWin(clubA);
                updateAfterLose(clubB);
                clubA.setScore(clubA.getScore() + 3);
            } else {
                updateAfterLose(clubB);
                updateAfterWin(clubA);
                clubB.setScore(clubB.getScore() + 3);
            }
        }
        volleyballRepository.updateScore(clubA, clubB);
    }

    public void updateAfterWin(Club club) throws SQLException {
        club.setCompetitionResult(CompetitionResult.WIN);
        club.setGamesWon(club.getGamesWon() + 1);
        club.setMatchPlays(club.getMatchPlays() + 1);
        volleyballRepository.updateMatchPlays(club);
        volleyballRepository.updateWinClub(club);
    }

    public void updateAfterLose(Club club) throws SQLException {
        club.setCompetitionResult(CompetitionResult.LOSE);
        club.setGamesLost(club.getGamesLost() + 1);
        club.setMatchPlays(club.getMatchPlays() + 1);
        volleyballRepository.updateMatchPlays(club);
        volleyballRepository.updateLoseClub(club);
    }

    public void updateWinSetsInLeague(Club clubA, Club clubB) throws SQLException {
        volleyballRepository.updateWinSetsInLeague(clubA.getClubName(), ((VolleyballClub) clubA).getWinSets());
        volleyballRepository.updateWinSetsInLeague(clubB.getClubName(), ((VolleyballClub) clubB).getWinSets());
    }

    public void updateSetsScoreInLeague(Club clubA, Club clubB) throws SQLException {
        volleyballRepository.updateSetsScoreInLeague(clubA.getClubName(), ((VolleyballClub) clubA).getSetsScores());
        volleyballRepository.updateSetsScoreInLeague(clubB.getClubName(), ((VolleyballClub) clubB).getSetsScores());
    }


    @Override
    public List<Club> showCompeteTable() throws SQLException {
        return sortByScoreAndWinsSetsAndAllScores(volleyballRepository.showFootballTableInfo());
    }

    private List<Club> sortByScoreAndWinsSetsAndAllScores(List<VolleyballClub> clubs) {
        Collections.sort(clubs, Collections.reverseOrder(Comparator.comparing(VolleyballClub::getScore)
                .thenComparing(VolleyballClub::getWinSets)
                .thenComparing(VolleyballClub::getSetsScores)));
        return clubs;
    }
}
