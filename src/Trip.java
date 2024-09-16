import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Trip {

    public static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static int calculateNumberOfBoats(int limit, int[] weights) {
        boolean[] boarded = new boolean[weights.length];
        int filledWeight, boardedNumber = 0, boatsNumber = 0;
        sort(weights);
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

        result = calculateNumberOfBoats(weightLimit, weights);

        try(FileWriter output = new FileWriter("output.txt")) {
            output.write(String.valueOf(result));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}