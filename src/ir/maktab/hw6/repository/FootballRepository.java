package ir.maktab.hw6.repository;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FootballRepository {
    private static FootballRepository footballRepository;

    private FootballRepository() {
    }

    public static FootballRepository getInstance() {
        if (footballRepository == null)
            footballRepository = new FootballRepository();
        return footballRepository;
    }

    public boolean isExistClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "select from \"footballClub\" where \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public boolean addClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String insertQuery = "INSERT INTO \"footballClub\" (\"clubName\") VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public boolean deleteClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String deleteQuery = "DELETE from \"footballClub\" WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public FootballClub selectFootballClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT * from \"footballClub\" WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        FootballClub footballClub = new FootballClub(resultSet.getString("clubName"), resultSet.getInt("matchPlay"),
                resultSet.getInt("wonGames"), resultSet.getInt("lostGames"), resultSet.getInt("score"),
                resultSet.getInt("drawnGames"));
        connection.close();
        return footballClub;
    }

    public void updateRecordAfterCompetition(FootballClub clubA, FootballClub clubB) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateGamesPlayQuery = "UPDATE \"footballClub\" set \"matchPlay\"=? WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateGamesPlayQuery);
        preparedStatement.setInt(1, clubA.getMatchPlays() + 1);
        preparedStatement.setString(2, clubA.getClubName());
        preparedStatement.addBatch();
        preparedStatement.setInt(1, clubB.getMatchPlays() + 1);
        preparedStatement.setString(2, clubB.getClubName());
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
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
            preparedStatement5.setInt(1, clubB.getDrawnGame() + 1);
            preparedStatement5.setString(2, clubB.getClubName());
            preparedStatement5.addBatch();
            preparedStatement5.executeBatch();
        }
    }

    public void updateScore(FootballClub clubA, FootballClub clubB) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateScoreQuery = "UPDATE \"footballClub\" set \"score\"=? WHERE \"clubName\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateScoreQuery);
        preparedStatement.setInt(1, clubA.getScore());
        preparedStatement.setString(2, clubA.getClubName());
        preparedStatement.addBatch();
        preparedStatement.setInt(1, clubB.getScore());
        preparedStatement.setString(2, clubB.getClubName());
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
        connection.close();
    }

    public List<Club> showFootballTableInfo() throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT \"clubName\",\"matchPlay\",\"wonGames\",\"lostGames\",\"drawnGames\",\"score\" from \"footballClub\"";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        List<Club> footballClubs = new ArrayList<>();
        while (resultSet.next()) {
            FootballClub footballClub = new FootballClub(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(6),
                    resultSet.getInt(5));
            footballClubs.add(footballClub);
        }
        return footballClubs;
    }
}

