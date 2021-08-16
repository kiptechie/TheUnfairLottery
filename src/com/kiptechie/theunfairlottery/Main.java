package com.kiptechie.theunfairlottery;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        promptPrizeValues(); // prompt user to input this week's prizes' values

        Scanner scanner = new Scanner(System.in);

        String prizeInput = scanner.nextLine();  // A comma-separated list of this week's prizes' values

        promptWinnerNames(); // prompt user to input this week's winners

        String nameInput = scanner.nextLine();  // A comma-separated list of this week's winners

        scanner.close();

        String[] names = Helpers.toStringArray(nameInput); // delete whitespace and create array of names
        int[] prizes = Helpers.toIntArr(prizeInput); // create an int array of prizes

        distributePrizes(names, prizes);

    }

    public static void distributePrizes(String[] names, int[] prizes) {

        List<Integer> prizeList = Helpers.toIntList(prizes); // convert int[] to List<Integer> for sorting with Collections Utility

        prizeList.sort(Collections.reverseOrder()); // reverse the list in order to assign the highest values first

        // round one
        List<Integer[]> prizesOutput = new ArrayList<>();
        List<Integer> sumOfPrizesList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            prizesOutput.add(new Integer[]{prizeList.get(0)}); // placeholder for prize distribution
            sumOfPrizesList.add(0); // placeholder for sum of prizes
            prizeList.remove(0); // remove assigned prize
        }

        // round two
        while (!prizeList.isEmpty()) {
            Helpers.populateSumOfPrizes(prizesOutput, prizeList, sumOfPrizesList); // sum of prizes for each winner
            int minValue = Collections.min(sumOfPrizesList); // winner with the least prize
            int indexOfMinimum = sumOfPrizesList.indexOf(minValue);
            // largest prize goes to the guy with the least prize
            Integer[] toSet = Arrays.copyOf(prizesOutput.get(indexOfMinimum), prizesOutput.get(indexOfMinimum).length + 1);
            toSet[prizesOutput.get(indexOfMinimum).length] = prizeList.get(0);
            prizesOutput.set(indexOfMinimum, toSet);
            prizeList.remove(0);
        }

        awardWinners(prizesOutput, names); // Award winners and their prizes
    }

    private static void awardWinners(List<Integer[]> prizesOutput, String[] names) {
        System.out.println("Results:");
        for (int i = 0; i < prizesOutput.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(names[i]).append(": ");
            for (int j = 0; j < prizesOutput.get(i).length; j++) {
                stringBuilder.append(prizesOutput.get(i)[j]).append(", ");
            }
            System.out.println(stringBuilder);
        }
    }

    private static void promptWinnerNames() {
        String winnersPrompt = "Input this week's winners' names separated by a comma e.g (Joshua,Mahesh,Lilian):";
        System.out.println(winnersPrompt);
    }

    public static void promptPrizeValues() {
        String prizesPrompt = "Welcome to Un-Fair Lottery\n" +
                "Please input this week's prize values separated by a comma e.g (100,800,200,500,400,1000):";
        System.out.println(prizesPrompt);
    }
}
