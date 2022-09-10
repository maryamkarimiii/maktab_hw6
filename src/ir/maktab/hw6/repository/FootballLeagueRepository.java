package ir.maktab.hw6.repository;

import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;
import ir.maktab.hw6.service.FootballServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FootballLeagueRepository {
    private static FootballLeagueRepository footballLeagueRepository;
    private FootballLeagueRepository() {
    }
    public static FootballLeagueRepository getInstance() {
        if(footballLeagueRepository==null)
            footballLeagueRepository=new FootballLeagueRepository();
        return footballLeagueRepository;
    }

    public boolean flag = false;
    FootballServiceImpl footballLeagueService = new FootballServiceImpl();

    public boolean addFootballClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String insertQuery = "INSERT INTO \"footballClub\" (\"clubName\") VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        if (result > 1)
            return true;
        else return false;
    }

    public boolean deleteClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String deleteQuery = "DELETE from \"footballClub\" WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        if (result > 1)
            return true;
        else return false;
    }

    public FootballClub selectFootballClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT * from \"footballClub\" WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        FootballClub footballClub = new FootballClub(resultSet.getString("clubName"), resultSet.getInt("gamesPlay"),
                resultSet.getInt("wonGames"), resultSet.getInt("lostGames"), resultSet.getInt("score"),
                resultSet.getInt("drawnGames"));
        connection.close();
        return footballClub;
    }

    public void updateRecordAfterCompetition(FootballClub clubA, FootballClub clubB) throws SQLException {
        if (flag) {
            Connection connection = ConnectionGate.getConnection();
            String updateGamesPlayQuery = "UPDATE \"footballClub\" set \"matchPlay\"=? WHERE \"clubName\"=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateGamesPlayQuery);
            preparedStatement.setInt(1, clubA.getMatchPlays() + 1);
            preparedStatement.setString(2, clubA.getClubName());
            preparedStatement.addBatch();
            preparedStatement.setInt(1, clubB.getMatchPlays() + 1);
            preparedStatement.setString(2, clubB.getClubName());
            preparedStatement.executeBatch();
            String updateScoreQuery = "UPDATE \"footballClub\" set \"score\"=? WHERE \"clubName\"=?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(updateScoreQuery);
            preparedStatement2.setInt(1, footballLeagueService.calculateScore(clubA));
            preparedStatement2.setString(2, clubA.getClubName());
            preparedStatement2.addBatch();
            preparedStatement2.setInt(1, footballLeagueService.calculateScore(clubB));
            preparedStatement2.setString(2, clubB.getClubName());
            preparedStatement2.executeBatch();
            if (clubA.getCompetitionResult().equals(CompetitionResult.WIN)) {
                String updateWonGamesQuery = "UPDATE \"footballClub\" set \"wonGames\"=? WHERE \"clubName\"=? ";
                PreparedStatement preparedStatement3 = connection.prepareStatement(updateWonGamesQuery);
                preparedStatement3.setInt(1, clubA.getGamesWon() + 1);
                preparedStatement3.setString(2, clubA.getClubName());
                preparedStatement3.executeUpdate();
                String updateLostGamesQuery = "UPDATE \"footballClub\" set \"lostGames\"=? WHERE \"clubName\"=?";
                PreparedStatement preparedStatement4 = connection.prepareStatement(updateLostGamesQuery);
                preparedStatement4.setInt(1, clubB.getGamesLost() + 1);
                preparedStatement4.setString(2, clubB.getClubName());
                preparedStatement4.executeUpdate();
            } else if (clubA.getCompetitionResult().equals(CompetitionResult.LOSE)) {
                String updateWonGamesQuery = "UPDATE \"footballClub\" set \"wonGames\"=? WHERE \"clubName\"=? ";
                PreparedStatement preparedStatement3 = connection.prepareStatement(updateWonGamesQuery);
                preparedStatement3.setInt(1, clubB.getGamesWon() + 1);
                preparedStatement3.setString(2, clubB.getClubName());
                preparedStatement3.executeUpdate();
                String updateLostGamesQuery = "UPDATE \"footballClub\" set \"lostGames\"=? WHERE \"clubName\"=?";
                PreparedStatement preparedStatement4 = connection.prepareStatement(updateLostGamesQuery);
                preparedStatement4.setInt(1, clubA.getGamesLost() + 1);
                preparedStatement4.setString(2, clubA.getClubName());
                preparedStatement4.executeUpdate();
            } else if (clubA.getCompetitionResult().equals(CompetitionResult.DRAW) && clubB.getCompetitionResult().equals(CompetitionResult.DRAW)) {
                String updateDrawnGamesQuery = "UPDATE \"footballClub\" set \"drawnGames\"=? WHERE \"clubName\"=?";
                PreparedStatement preparedStatement5 = connection.prepareStatement(updateDrawnGamesQuery);
                preparedStatement5.setInt(1, clubA.getDrawnGame() + 1);
                preparedStatement5.setString(2, clubA.getClubName());
                preparedStatement5.addBatch();
                preparedStatement5.setInt(1, clubA.getDrawnGame() + 1);
                preparedStatement5.setString(2, clubA.getClubName());
                preparedStatement5.executeBatch();
            }
            connection.close();
        }
    }

    public List<ResultSet> showFootballTableInfo() throws SQLException {
        List<ResultSet> resultSets = new ArrayList<>();
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT * from \"footballClub\" ORDER BY \"score\"";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while (resultSet.next()) {
            resultSets.add(resultSet);
        }
        return resultSets;
    }


}

