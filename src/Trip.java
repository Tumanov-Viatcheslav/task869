import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trip {

    public static int calculateNumberOfBoatsCrude(int limit, int[] weights) {
        boolean[] boarded = new boolean[weights.length];
        int filledWeight, boardedNumber = 0, boatsNumber = 0;
        Sorts.bubbleSortF(weights);
        Arrays.fill(boarded, false);
        while (boardedNumber < weights.length) {
            filledWeight = 0;
            for (int i = weights.length - 1; i >= 0; i--) {
                if (boarded[i] || (filledWeight + weights[i]) > limit)
                    continue;
                filledWeight += weights[i];
                boarded[i] = true;
                boardedNumber++;
            }
            boatsNumber++;
        }
        return boatsNumber;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int calculateNumberOfBoats(int limit, int[] weights) {
        boolean[] boarded = new boolean[weights.length];
        List<Integer>[][] board = new List[weights.length + 1][limit + 1];
        int[][] maxWeight = new int[weights.length + 1][limit + 1];
        int boardedNumber = 0, boatsNumber = 0;
        Sorts.bubbleSortF(weights);
        Arrays.fill(boarded, false);
        for (int i = 0; i <= limit; i++) {
            maxWeight[0][i] = 0;
            board[0][i] = new ArrayList<>();
        }
        while (boardedNumber < weights.length) {
            for (int i = 0; i < weights.length; i++) {
                for (int j = 0; j <= limit; j++) {
                    if (weights[i] > j || boarded[i]) {
                        maxWeight[i + 1][j] = maxWeight[i][j];
                        board[i + 1][j] = board[i][j];
                    }
                    else {
                        maxWeight[i + 1][j] = max(maxWeight[i][j], maxWeight[i][j - weights[i]] + weights[i]);
                        if ((maxWeight[i][j - weights[i]] + weights[i]) > maxWeight[i][j]) {
                            board[i + 1][j] = new ArrayList<Integer>();

                            board[i + 1][j].addAll(board[i][j - weights[i]]);
                            board[i + 1][j].add(i);
                        }
                        else {
                            board[i + 1][j] = board[i][j];
                        }
                    }
                }
            }
            System.out.println("Boat");
            for (int passenger : board[weights.length][limit]) {
                boarded[passenger] = true;
                System.out.println(passenger);
            }
            boardedNumber += board[weights.length][limit].size();
            boatsNumber++;
        }
        return boatsNumber;
    }

    public static int calculateNumberOfKayaks(int limit, int[] weights) {
        int numberOfKayaks = 0;
        boolean[] boarded = new boolean[weights.length];
        Sorts.bubbleSortF(weights);
        Arrays.fill(boarded, false);
        for (int i = weights.length - 1; i >= 0; i--) {
            if (boarded[i])
                continue;
            for (int j = i - 1; j >= 0; j--) {
                if (!boarded[j] && (weights[i] + weights[j] < limit)) {
                    boarded[i] = true;
                    boarded[j] = true;
                    numberOfKayaks++;
                    break;
                }
            }
            if (!boarded[i])
                numberOfKayaks++;
        }
        return numberOfKayaks;
    }

    public static int calculateNumberOfKayaks2(int limit, int[] weights) {
        int numberOfKayaks = 0, lightIndex = 0;
        Sorts.radixSortLSD(weights, 10);
        for (int i = weights.length - 1; i >= lightIndex; i--) {
            if (weights[i] + weights[lightIndex] < limit)
                lightIndex++;
            numberOfKayaks++;
        }
        return numberOfKayaks;
    }

    private static void writeResultToFile(int result, String fileName) {
        try(FileWriter output = new FileWriter(fileName)) {
            output.write(String.valueOf(result));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        int numberOfPeople, weightLimit = 0, result;
        int[] weights = null;
        try(BufferedReader input = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            String[] dividedLine;
            line = input.readLine();
            dividedLine = line.split(" ");
            numberOfPeople = Integer.parseInt(dividedLine[0]);
            weightLimit = Integer.parseInt(dividedLine[1]);
            weights = new int[numberOfPeople];
            line = input.readLine();
            dividedLine = line.split(" ");
            for (int i = 0; i < numberOfPeople; i++) {
                weights[i] = Integer.parseInt(dividedLine[i]);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        result = calculateNumberOfKayaks2(weightLimit, weights);

        writeResultToFile(result, "output.txt");
    }
}