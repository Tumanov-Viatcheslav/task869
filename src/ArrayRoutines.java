//Сборник полезных функций для работы с массивами.
//2024-09-16

import java.util.Scanner;

public class ArrayRoutines {
    public static int[] inputArray(int size) {
        int[] mas = new int[size];
        System.out.println("Введите массив из " + size + " целых чисел");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < mas.length; i++) {
            mas[i] = scanner.nextInt();
        }
        return mas;
    }

    public static void printArray(int[] mas) {
        System.out.print("массив: ");
        for (int ma : mas) {
            System.out.print(" " + ma);
        }
        System.out.println();
    }

    public static int min(int[] mas) {
        int m = mas[0];
        for (int i = 1; i < mas.length; i++) {
            if (m > mas[i])
                m = mas[i];
        }
        return m;
    }

    public static int min(int[] mas, int start) {
        int m = mas[0];
        for (int i = start + 1; i < mas.length; i++) {
            if (m > mas[i])
                m = mas[i];
        }
        return m;
    }

    //функция, которая находит максимум в массиве
    public static int max(int[] mas) {
        int m = mas[0];
        for (int i = 1; i < mas.length; i++) {
            if (m < mas[i])
                m = mas[i];
        }
        return m;
    }

    //функция, которая находит НОМЕР максимума
    public static int indexOfMax(int[] mas) {
        int m = mas[0];
        int numberOfMaximum = 0;
        for (int i = 1; i < mas.length; i++) {
            if (m < mas[i]) {
                m = mas[i];
                numberOfMaximum = i;
            }
        }
        return numberOfMaximum;
    }

    public static int indexOfMin(int[] mas, int start) {
        int m = mas[start];
        int numberOfMin = start;
        for (int i = start + 1; i < mas.length; i++) {
            if (m > mas[i]) {
                m = mas[i];
                numberOfMin = i;
            }
        }
        return numberOfMin;
    }

    public static double avg(int[] mas) {
        double av;
        int sum = 0;
        for (int ma : mas) {
            sum += ma;
        }
        av = ((double) sum) / mas.length;
        return av;
    }

    public static void swapTwoElements(int[] mas, int i, int j) {
        int tmp = mas[i];
        mas[i] = mas[j];
        mas[j] = tmp;
    }

    public static int findX(int[] mas, int x) {
        for (int i = 0; i < mas.length; i++) {
            if (x == mas[i]) return i;
        }
        return -1;  //дошли до конца массива, совпадений не было
    }
}
