package ir.maktab.hw6;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.repository.FootballRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        FootballRepository footballRepository=FootballRepository.getInstance();
        System.out.println(Integer.compare(footballRepository.selectFootballClub("name").getScore(),footballRepository.selectFootballClub("maryam").getScore()));
    }
    //            preparedStatement3.setInt(1, clubA.getGamesWon() + 1);
//            preparedStatement3.setString(2, clubA.getClubName());
//            preparedStatement3.executeUpdate();
//            String updateLostGamesQuery = "UPDATE \"footballClub\" set \"lostGames\"=? WHERE \"clubName\"=?";
//            PreparedStatement preparedStatement4 = connection.prepareStatement(updateLostGamesQuery);
//            preparedStatement4.setInt(1, clubB.getGamesLost() + 1);
//            preparedStatement4.setString(2, clubB.getClubName());
//            preparedStatement4.executeUpdate();
//        } else if (clubA.getCompetitionResult().equals(CompetitionResult.LOSE)) {
//            String updateWonGamesQuery = "UPDATE \"footballClub\" set \"wonGames\"=? WHERE \"clubName\"=? ";
//            PreparedStatement preparedStatement3 = connection.prepareStatement(updateWonGamesQuery);
//            preparedStatement3.setInt(1, clubB.getGamesWon() + 1);
//            preparedStatement3.setString(2, clubB.getClubName());
//            preparedStatement3.executeUpdate();
//            String updateLostGamesQuery = "UPDATE \"footballClub\" set \"lostGames\"=? WHERE \"clubName\"=?";
//            PreparedStatement preparedStatement4 = connection.prepareStatement(updateLostGamesQuery);
//            preparedStatement4.setInt(1, clubA.getGamesLost() + 1);
//            preparedStatement4.setString(2, clubA.getClubName());
//            preparedStatement4.executeUpdate();
//        } else if (clubA.getCompetitionResult().equals(CompetitionResult.DRAW) && clubB.getCompetitionResult().equals(CompetitionResult.DRAW)) {
//            String updateDrawnGamesQuery = "UPDATE \"footballClub\" set \"drawnGames\"=? WHERE \"clubName\"=?";
//            PreparedStatement preparedStatement5 = connection.prepareStatement(updateDrawnGamesQuery);
//            preparedStatement5.setInt(1, clubA.getDrawnGame() + 1);
//            preparedStatement5.setString(2, clubA.getClubName());
//            preparedStatement5.addBatch();
//            preparedStatement5.setInt(1, clubB.getDrawnGame() + 1);
//            preparedStatement5.setString(2, clubB.getClubName());
//            preparedStatement5.addBatch();
//            preparedStatement5.executeBatch();
//        }
//    }
}