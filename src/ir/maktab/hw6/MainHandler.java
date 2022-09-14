package ir.maktab.hw6;

import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.model.VolleyballClub;
import ir.maktab.hw6.service.FootballServiceImpl;
import ir.maktab.hw6.service.VolleyballServiceImpl;
import ir.maktab.hw6.service.interfaces.LeagueService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainHandler {
    public static Scanner scanner = new Scanner(System.in);
    public static void menu() throws SQLException {
        System.out.println("chose your league \n 1:FOOTBALL \n 2:VOLLEYBALL");
        switch (Integer.parseInt(scanner.nextLine())){
            case 1:
                footballMenu();
                break;
            case 2:
                volleyballMenu();
        }
    }

    public static void footballMenu() throws SQLException {
        FootballServiceImpl footballService = FootballServiceImpl.getInstance();
        boolean flag = true;
        while (flag) {
            System.out.println("chose \n ~1:add or delete club \n ~2:add competition \n ~3:watch club info \n" +
                    " ~4:watch competition result \n ~5:exit");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.println("chose \n ~1:add new club \n ~2:delete club ");
                    int input = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter club name");
                    String clubName = scanner.nextLine();
                    switch (input) {
                        case 1:
                            System.out.println(footballService.addClub(clubName));
                            break;
                        case 2:
                            System.out.println(footballService.removeClub(clubName));
                    }
                    break;
                case 2:
                    FootballClub footballClubA;
                    do {
                        System.out.println("enter first club in competition");
                        footballClubA = footballService.findClub(scanner.nextLine());
                        if (footballClubA == null)
                            System.out.println("club not exist");
                    } while (footballClubA != null);
                    System.out.println("enter the " + footballClubA.getClubName() + "goals");
                    footballClubA.setGoal(Integer.parseInt(scanner.nextLine()));
                    FootballClub footballClubB;
                    do {
                        System.out.println("enter second club in competition");
                        footballClubB = footballService.findClub(scanner.nextLine());
                        if (footballClubB == null)
                            System.out.println("club not exist");
                    } while (footballClubB != null);
                    System.out.println("enter the" + footballClubB.getClubName() + "goals");
                    footballClubB.setGoal(Integer.parseInt(scanner.nextLine()));
                    footballService.competeAndCalculateScore(footballClubA, footballClubB);
                    break;
                case 3:
                    System.out.println("enter your club name");
                    FootballClub footballClub = footballService.findClub(scanner.nextLine());
                    if (footballClub != null)
                        System.out.println(footballClub);
                    System.out.println("the club isn't exist");
                    break;
                case 4:
                    System.out.println(footballService.showCompeteTable());
                    break;
                case 5:
                    System.out.println("goodbye");
                    flag = false;
            }
        }

    }

    public static void volleyballMenu() throws SQLException {
        LeagueService volleyballService = VolleyballServiceImpl.getInstance();
        boolean flag = true;
        while (flag) {
            System.out.println("chose \n ~1:add or delete club \n ~2:add competition \n ~3:watch club info \n" +
                    " ~4:watch competition result \n ~5:exit");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.println("chose \n ~1:add club \n ~2:delete club ");
                    int input = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter club name");
                    String clubName = scanner.nextLine();
                    switch (input) {
                        case 1:
                            System.out.println(volleyballService.addClub(clubName));
                            break;
                        case 2:
                            System.out.println(volleyballService.removeClub(clubName));
                    }
                    break;
                case 2:
                    VolleyballClub volleyballClubA;
                    do {
                        System.out.println("enter first club in competition");
                        volleyballClubA = ((VolleyballServiceImpl) volleyballService).findClub(scanner.nextLine());
                        if (volleyballClubA == null)
                            System.out.println("club not exist");
                    } while (volleyballClubA == null);
                    System.out.println("enter the " + volleyballClubA.getClubName() + "\t" + "win sets");
                    volleyballClubA.setWinSets(Integer.parseInt(scanner.nextLine()));
                    System.out.println("enter the " + volleyballClubA.getClubName() + "\t" + "scores in all sets");
                    volleyballClubA.setSetsScores(Integer.parseInt(scanner.nextLine()));
                    VolleyballClub volleyballClubB;
                    do {
                        System.out.println("enter second club in competition");
                        volleyballClubB = ((VolleyballServiceImpl) volleyballService).findClub(scanner.nextLine());
                        if (volleyballClubB == null)
                            System.out.println("club not exist");
                    } while (volleyballClubB == null);
                    System.out.println("enter the" + volleyballClubB.getClubName() + "\t" + "win sets");
                    volleyballClubB.setWinSets(Integer.parseInt(scanner.nextLine()));
                    System.out.println("enter the " + volleyballClubB.getClubName() + "\t" + "scores in all sets");
                    volleyballClubB.setSetsScores(Integer.parseInt(scanner.nextLine()));
                    ((VolleyballServiceImpl) volleyballService).updateWinSetsInLeague(volleyballClubA, volleyballClubB);
                    ((VolleyballServiceImpl) volleyballService).updateSetsScoreInLeague(volleyballClubA, volleyballClubB);
                    volleyballService.competeAndCalculateScore(volleyballClubA, volleyballClubB);
                    break;
                case 3:
                    System.out.println("enter your club name");
                    VolleyballClub volleyballClub = ((VolleyballServiceImpl) volleyballService).findClub(scanner.nextLine());
                    if (volleyballClub != null)
                        System.out.println(volleyballClub);
                    System.out.println("the club isn't exist");
                    break;
                case 4:
                    System.out.println(volleyballService.showCompeteTable());
                    break;
                case 5:
                    System.out.println("goodbye");
                    flag = false;
            }
        }
    }
}
