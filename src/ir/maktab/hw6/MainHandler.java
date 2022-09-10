package ir.maktab.hw6;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.enums.CompetitionResult;
import ir.maktab.hw6.service.FootballServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MainHandler {
    public static Scanner scanner = new Scanner(System.in);

    public static void footballMenu() throws SQLException {
        FootballServiceImpl footballLeagueService = new FootballServiceImpl();
        boolean flag = true;
        while (flag) {
            System.out.println("chose ~1:add club \n ~2:delete club \n ~3:add competition \n ~4:watch club info \n ~5:watch competition result ");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.println("enter club name:");
                    String clubName=scanner.nextLine();
                    System.out.println(footballLeagueService.addClub(clubName));
                    break;
                case 2:
                    System.out.println("enter club name:");
                    String clubName2=scanner.nextLine();
                    System.out.println(footballLeagueService.removeClub(clubName2));
                    break;
                case 3:
                    System.out.println("enter first club in competition");
                    Club clubA = new FootballClub();
                    clubA.setClubName(scanner.nextLine());
                    System.out.println("enter the result: win , lost , equal");
                    clubA.setCompetitionResult(CompetitionResult.valueOf(scanner.nextLine()));
                    System.out.println("enter second club in competition");
                    Club clubB = new FootballClub();
                    clubB.setClubName(scanner.nextLine());
                    System.out.println("enter the result: win , lost , equal");
                    clubB.setCompetitionResult(CompetitionResult.valueOf(scanner.nextLine()));
                    footballLeagueService.compete();
                case 4:

                case 5:
                    break;
            }
        }
    }
}
