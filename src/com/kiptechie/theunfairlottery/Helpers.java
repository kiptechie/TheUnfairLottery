package com.kiptechie.theunfairlottery;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class
 * Contains some helper functions
 */
public class Helpers {

    private static final String delimiter = "[,]";

    /**
     * This function converts a String to a String Array
     *
     * @param str String with comma as separators
     * @return String[]
     */
    public static String[] toStringArray(String str) {
        return str.replaceAll("\\s", "").split(delimiter);
    }

    /**
     * This function converts a String to int array
     *
     * @param str String with comma as separators
     * @return int[]
     */
    public static int[] toIntArr(String str) {
        String[] strings = Helpers.toStringArray(str);
        int[] intArr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            intArr[i] = Integer.parseInt(strings[i]);
        }
        return intArr;
    }

    /**
     * Calculates sum of prizes
     *
     * @param items            prizesOutput
     * @param currentItemIndex index
     * @return int
     */
    private static int calculateSum(List<Integer[]> items, int currentItemIndex) {
        int sum = 0;
        for (int j = 0; j < items.get(currentItemIndex).length; j++) {
            sum += items.get(currentItemIndex)[j];
        }
        return sum;
    }

    /**
     * Updates sum of prizes array
     *
     * @param prizesOutput     list from prizes output
     * @param prizesList       prizes integer list
     * @param sumOfPrizesArray sum of prizes array
     */
    public static void populateSumOfPrizes(List<Integer[]> prizesOutput, List<Integer> prizesList, List<Integer> sumOfPrizesArray) {
        int sumOfPrizes;
        for (int i = 0; i < prizesOutput.size() && prizesList.size() > 0; i++) {
            for (int j = 0; j < prizesOutput.get(i).length; j++) {
                sumOfPrizes = calculateSum(prizesOutput, i);
                sumOfPrizesArray.set(i, sumOfPrizes);
            }
        }
    }

    /**
     * Converts int array to integer list
     *
     * @param ints int array
     * @return Integer List
     */
    public static List<Integer> toIntList(int[] ints) {
        List<Integer> prizeList = new ArrayList<>();
        for (int prize : ints) {
            prizeList.add(prize);
        }
        return prizeList;
    }

}
