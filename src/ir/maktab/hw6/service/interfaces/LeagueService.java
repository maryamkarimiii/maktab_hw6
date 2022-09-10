package ir.maktab.hw6.service.interfaces;

import ir.maktab.hw6.model.Club;

import java.sql.SQLException;

public interface LeagueService {
   String addClub(String clubName) throws SQLException;
   String removeClub(String clubName) throws SQLException;
   void compete();
   int calculateScore(Club club);

   void showCompeteTable() throws SQLException;
}
