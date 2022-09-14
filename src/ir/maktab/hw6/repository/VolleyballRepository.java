package ir.maktab.hw6.repository;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.VolleyballClub;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolleyballRepository {
    private static VolleyballRepository volleyballRepository;

    private VolleyballRepository() {
    }

    public static VolleyballRepository getInstance() {
        if (volleyballRepository == null)
            volleyballRepository = new VolleyballRepository();
        return volleyballRepository;
    }

    public boolean isExistClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "select from \"volleyball_club\" where \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        return resultSet.next();
    }

    public boolean addClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String insertQuery = "INSERT INTO \"volleyball_club\" (\"club_name\") VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public boolean deleteClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String deleteQuery = "DELETE from \"volleyball_club\" WHERE \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, clubName);
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public VolleyballClub selectClub(String clubName) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT * from \"volleyball_club\" WHERE \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        VolleyballClub volleyballClub = new VolleyballClub(resultSet.getString("club_name"), resultSet.getInt("match_plays"),
                resultSet.getInt("won_games"), resultSet.getInt("lost_game"), resultSet.getInt("score"),
                resultSet.getInt("win_sets"), resultSet.getInt("sets_scores"));
        connection.close();
        return volleyballClub;
    }

    public void updateMatchPlays(Club club) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateGamesPlayQuery = "UPDATE \"volleyball_club\" set \"match_plays\"=? WHERE \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateGamesPlayQuery);
        preparedStatement.setInt(1, club.getMatchPlays());
        preparedStatement.setString(2, club.getClubName());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean updateWinClub(Club club) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateWonGamesQuery = "UPDATE \"volleyball_club\" set \"won_games\"=?,\"score\"=? WHERE \"club_name\"=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateWonGamesQuery);
        preparedStatement.setInt(1, club.getGamesWon());
        preparedStatement.setInt(2, club.getScore());
        preparedStatement.setString(3, club.getClubName());
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public boolean updateLoseClub(Club club) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateWonGamesQuery = "UPDATE \"volleyball_club\" set \"lost_game\"=? WHERE \"club_name\"=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateWonGamesQuery);
        preparedStatement.setInt(1, club.getGamesLost());
        preparedStatement.setString(2, club.getClubName());
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result >= 1;
    }

    public void updateScore(Club clubA, Club clubB) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String updateScoreQuery = "UPDATE \"volleyball_club\" set \"score\"=? WHERE \"club_name\"=?";
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

    public void updateWinSetsInLeague(String clubName, int winSetsInCompetition) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT \"win_sets\" from \"volleyball_club\" WHERE \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt("win_sets");
        String updateQuery = "UPDATE \"volleyball_club\" set \"win_sets\"=? WHERE \"club_name\"=?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
        preparedStatement1.setInt(1, result + winSetsInCompetition);
        preparedStatement1.setString(2, clubName);
        preparedStatement1.executeUpdate();
        connection.close();
    }

    public void updateSetsScoreInLeague(String clubName, int scoreInCompetition) throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT \"sets_scores\" from \"volleyball_club\" WHERE \"club_name\"=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, clubName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt("sets_scores");
        String updateQuery = "UPDATE \"volleyball_club\" set \"sets_scores\"=? WHERE \"club_name\"=?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
        preparedStatement1.setInt(1, result + scoreInCompetition);
        preparedStatement1.setString(2, clubName);
        preparedStatement1.executeUpdate();
        connection.close();
    }

    public List<VolleyballClub> showFootballTableInfo() throws SQLException {
        Connection connection = ConnectionGate.getConnection();
        String selectQuery = "SELECT \"club_name\",\"match_plays\",\"won_games\",\"lost_games\",\"score\", from \"volleyball_club\"";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        List<VolleyballClub> volleyballClubs = new ArrayList<>();
        while (resultSet.next()) {
            VolleyballClub volleyballClub = new VolleyballClub(resultSet.getString("club_name"), resultSet.getInt("match_plays"),
                    resultSet.getInt("won_games"), resultSet.getInt("lost_games"), resultSet.getInt("score"));
            volleyballClubs.add(volleyballClub);
        }
        connection.close();
        return volleyballClubs;
    }
}
