import java.util.Arrays;

public class Sorts {

    public static void radixSortLSD(int[] arr, int radix) {
        int mask = 1, max = ArrayRoutines.max(arr), indexTmp, tmp;
        int[] indexesInBuckets = new int[radix], tmpArr = new int[arr.length];
        int[][] bucket = new int[radix][arr.length];
        Pair[] singleDigits = new Pair[arr.length];
        while (mask < max) {
            for (int i = 0; i < arr.length; i++) {
                tmp = (arr[i] / mask) % radix;
                if (tmp > 0)
                    singleDigits[i] = new Pair(tmp, i);
                else
                    singleDigits[i] = new Pair(0, i);
            }
            Arrays.fill(indexesInBuckets, 0);
            for (Pair singleDigit : singleDigits) {
                bucket[singleDigit.x1][indexesInBuckets[singleDigit.x1]] = singleDigit.x2;
                indexesInBuckets[singleDigit.x1]++;
            }
            indexTmp = 0;
            for (int i = 0; i < indexesInBuckets.length; i++) {
                for (int j = 0; j < indexesInBuckets[i]; j++) {
                    tmpArr[indexTmp] = arr[bucket[i][j]];
                    indexTmp++;
                }
            }
            System.arraycopy(tmpArr, 0, arr, 0, arr.length);
            mask *= radix;
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            ArrayRoutines.swapTwoElements(arr, i, ArrayRoutines.indexOfMin(arr, i));
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j])
                    ArrayRoutines.swapTwoElements(arr, i, j);
            }
        }
    }

    public static void bubbleSortF(int[] arr) {
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ArrayRoutines.swapTwoElements(arr, i, j);
                    flag = true;
                }
            }
            if (!flag)
                return;
        }
    }
}
