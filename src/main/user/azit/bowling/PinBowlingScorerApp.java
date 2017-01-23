package main.user.azit.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azit on 22/1/17.
 */
public class PinBowlingScorerApp {
    private static int totalScore = 0;

    public static void main(String[] args){
        List<Integer> scores = new ArrayList<Integer>();

        for(String score : args){
            try {
                scores.add(Integer.parseInt(score));
            } catch(NumberFormatException nfe){
                System.out.println("Score has to be integers only");
                System.exit(0);
            }
        }

        PinBowlingScoreCalculator pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);

        try {
            System.out.println("Total score of the game is :" + pinBowlingScoreCalculator.calculateTotalScore());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
