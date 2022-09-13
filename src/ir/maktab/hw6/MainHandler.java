package ir.maktab.hw6;

import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.service.FootballServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainHandler {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        footballMenu();
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
                    System.out.println("enter first club in competition");
                    FootballClub footballClubA = footballService.findClub(scanner.nextLine());
                    if (footballClubA != null) {
                        System.out.println("enter the " + footballClubA.getClubName() + "goals");
                        footballClubA.setGoal(Integer.parseInt(scanner.nextLine()));
                    } else System.out.println("club not exist");
                    System.out.println("enter second club in competition");
                    FootballClub footballClubB = footballService.findClub(scanner.nextLine());
                    if (footballClubB != null) {
                        System.out.println("enter the" + footballClubB.getClubName() + "goals");
                        footballClubB.setGoal(Integer.parseInt(scanner.nextLine()));
                    }
                    footballService.compete(footballClubA, footballClubB);
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
                    break;
            }
        }
    }
}
