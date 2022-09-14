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
        System.out.println( Integer.compare(6,4));
    }
}