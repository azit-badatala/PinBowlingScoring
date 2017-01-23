package test.user.azit.bowling;

import main.user.azit.bowling.PinBowlingScoreCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azit on 23/1/17.
 */
public class PinBowlingScorerTest {
    private PinBowlingScoreCalculator pinBowlingScoreCalculator;

    @Test
    public void test_valid_scores() throws Exception{
        List<Integer> scores = createScores(new Integer[]{1,2,3,4});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(10, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_valid_scores_with_strike() throws Exception{
        List<Integer> scores = createScores(new Integer[]{1,2,10,4});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(21, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_valid_scores_with_spare() throws Exception{
        List<Integer> scores = createScores(new Integer[]{1,2,9,1,4,5});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(26, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_valid_scores_with_spare_current_score() throws Exception{
        List<Integer> scores = createScores(new Integer[]{9,1,9,1});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(29, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_valid_scores_with_spare_and_strike() throws Exception{
        List<Integer> scores = createScores(new Integer[]{1,2,9,1,4,5,10,4});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(44, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_valid_scores_with_perfect_score() throws Exception{
        List<Integer> scores = createScores(new Integer[]{10,10,10,10,10,10,10,10,10,10,10,10});
        pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        Assert.assertEquals(300, pinBowlingScoreCalculator.calculateTotalScore());
    }

    @Test
    public void test_invalid_scores_more_than_10_frames() throws Exception{
        try {
            List<Integer> scores = createScores(new Integer[]{1, 2, 9, 1, 4, 5, 10, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
            pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        } catch (Exception e){
            Assert.assertEquals("Invalid scores - A game cannot have more than 10 frames", e.getMessage());
        }
    }

    @Test
    public void test_invalid_score_more_than_10() throws Exception{
        try {
            List<Integer> scores = createScores(new Integer[]{1, 2, 9, 1, 4, 15, 10, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1});
            pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        } catch (Exception e){
            Assert.assertEquals("Invalid scores - Single ball score cannot be more than 10", e.getMessage());
        }
    }

    @Test
    public void test_invalid_frame_score_more_than_10() throws Exception{
        try {
            List<Integer> scores = createScores(new Integer[]{1, 2, 9, 2, 4, 10, 10, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1});
            pinBowlingScoreCalculator = new PinBowlingScoreCalculator(scores);
        } catch (Exception e){
            Assert.assertEquals("Invalid scores - Single ball score cannot be more than 10", e.getMessage());
        }
    }

    private List<Integer> createScores(Integer[] scores){
        List<Integer> scoresList = new ArrayList<Integer>();
        for(Integer score : scores){
                scoresList.add(score);
        }
        return scoresList;
    }
}
