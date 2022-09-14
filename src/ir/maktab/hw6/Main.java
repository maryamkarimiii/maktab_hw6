package ir.maktab.hw6;

import ir.maktab.hw6.model.Club;
import ir.maktab.hw6.model.FootballClub;
import ir.maktab.hw6.repository.FootballRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        System.out.println( Integer.compare(3,0));
    }
    //                    System.out.println("enter first club in competition");
//                    FootballClub footballClubA = footballService.findClub(scanner.nextLine());
//                    if (footballClubA != null) {
//                        System.out.println("enter the " + footballClubA.getClubName() + "goals");
//                        footballClubA.setGoal(Integer.parseInt(scanner.nextLine()));
//                    } else System.out.println("club not exist");
//                    System.out.println("enter second club in competition");
//                    FootballClub footballClubB = footballService.findClub(scanner.nextLine());
//                    if (footballClubB != null) {
//                        System.out.println("enter the" + footballClubB.getClubName() + "goals");
//                        footballClubB.setGoal(Integer.parseInt(scanner.nextLine()));
//                    }
//                    footballService.compete(footballClubA, footballClubB);
}