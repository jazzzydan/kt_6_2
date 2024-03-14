package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class kt_6_2 {
    public static void main(String[] args) {

        generateAndWriteTicketToFile("pilet.txt"); // genereerib ja kirjutab faili pilet.txt 5x5 bingo pilet
        generateAndWriteNumbersToFile("numbrid.txt"); // genereerib ja kirjutab faili numbrid.txt 40 unikaalset numbrit vahemikus 1-75

        int[][] ticket = readTicketFromFile("pilet.txt");
        ArrayList<Integer> drawnNumbers = readNumbersFromFile("numbrid.txt");

        String ticketString = "ei võitnud";
        if (checkCornerGame(ticket, drawnNumbers)) {
            ticketString = "võitnud";
        }
        System.out.println("Nurkademäng: " + ticketString);

        ticketString = "ei võitnud";
        if (checkDiagonalGame(ticket, drawnNumbers)) {
            ticketString = "võitnud";
        }
        System.out.println("Diagonaalidemäng: " + ticketString);

        ticketString = "ei võitnud";
        if (checkFullGame(ticket, drawnNumbers)) {
            ticketString = "võitnud";
        }
        System.out.println("Täismäng: " + ticketString);
    }

    public static int[][] readTicketFromFile(String fileName) {
        int[][] ticket = new int[5][5];
        try {
            Scanner scanner = new Scanner(new File(fileName));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (scanner.hasNextInt()) {
                        ticket[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public static ArrayList<Integer> readNumbersFromFile(String fileName) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    public static boolean checkCornerGame(int[][] ticket, ArrayList<Integer> drawnNumbers) {
        return drawnNumbers.contains(ticket[0][0]) && drawnNumbers.contains(ticket[0][4])
                && drawnNumbers.contains(ticket[4][0]) && drawnNumbers.contains(ticket[4][4]);
    }

    public static boolean checkDiagonalGame(int[][] ticket, ArrayList<Integer> drawnNumbers) {
        return (drawnNumbers.contains(ticket[0][0]) && drawnNumbers.contains(ticket[1][1])
                && drawnNumbers.contains(ticket[2][2]) && drawnNumbers.contains(ticket[3][3])
                && drawnNumbers.contains(ticket[4][4]))
                || (drawnNumbers.contains(ticket[0][4]) && drawnNumbers.contains(ticket[1][3])
                && drawnNumbers.contains(ticket[2][2]) && drawnNumbers.contains(ticket[3][1])
                && drawnNumbers.contains(ticket[4][0]));
    }

    public static boolean checkFullGame(int[][] ticket, ArrayList<Integer> drawnNumbers) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!drawnNumbers.contains(ticket[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void generateAndWriteTicketToFile(String fileName) {
        int[][] ticket = generateTicket();
        writeTicketToFile(fileName, ticket);
    }

    private static int[][] generateTicket() {
        int[][] ticket = new int[5][5];
        for (int i = 0; i < 5; i++) {
            List<Integer> numbers = generateUniqueNumbers(i * 15 + 1, (i + 1) * 15);
            for (int j = 0; j < 5; j++) {
                ticket[j][i] = numbers.get(j);
            }
        }
        return ticket;
    }

    private static List<Integer> generateUniqueNumbers(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, 5);
    }

    private static void writeTicketToFile(String fileName, int[][] ticket) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int[] row : ticket) {
                for (int number : row) {
                    writer.write(number + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAndWriteNumbersToFile(String fileName) {
        List<Integer> numbers = generateUniqueNumbers(1, 75, 40);
        writeNumbersToFile(fileName, numbers);
    }

    private static List<Integer> generateUniqueNumbers(int start, int end, int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, count);
    }

    private static void writeNumbersToFile(String fileName, List<Integer> numbers) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int number : numbers) {
                writer.write(number + ", ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}