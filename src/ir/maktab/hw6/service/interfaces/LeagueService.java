package ir.maktab.hw6.service.interfaces;

import ir.maktab.hw6.model.Club;

import java.sql.SQLException;
import java.util.List;

public interface LeagueService {
   String addClub(String clubName) throws SQLException;
   String removeClub(String clubName) throws SQLException;
   void compete(Club clubA,Club clubB) throws SQLException;
   int calculateScore(Club club);

  List<Club> showCompeteTable() throws SQLException;
}
