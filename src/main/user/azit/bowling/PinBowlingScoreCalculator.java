package main.user.azit.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azit on 23/1/17.
 */
public class PinBowlingScoreCalculator {

    private List<Integer> scores;
    private int totalScore=0;

    public PinBowlingScoreCalculator(List<Integer> scores){
        this.scores = scores;
    }

    public int calculateTotalScore() throws Exception{
            List frameScores = calculateFrameScore(this.scores);
            return calculateFrameScoreWithBonus(frameScores);
    }


    // Create an array of frameScores which has an array of first ball and second ball scores of the frame while validating the scores input
    // Conditions to be considered - frame has a strike, player stopped playing the game, store the additional balls after 10th frame strike or a spare in 11th frame for easier calculation of total score
    public List calculateFrameScore(List<Integer> scores) throws Exception{
        List<List<Integer>> frameScores = new ArrayList<>();
        int frameCount = 0;
        int ballCountInFrame = 0;
        int scoreCount = 0;
        List<Integer> ballScores = new ArrayList();

        for(Integer score : scores) {
            if(frameCount > 9 && (frameScores.get(9).get(0) != 10 || (frameScores.get(9).size() > 1 && (frameScores.get(9).get(0) + frameScores.get(9).get(1) != 10)))){
                System.out.println("A game cannot have more than 10 frames");
                throw new Exception("Invalid scores - A game cannot have more than 10 frames");
            }
            if(score > 10){
                System.out.println("Single ball score cannot be more than 10");
                throw new Exception("Invalid scores - Single ball score cannot be more than 10");
            }
            if(ballCountInFrame == 0){
                ballScores = new ArrayList();
            }
            if(ballCountInFrame % 2 == 0) {
                if(score == 10) {
                    ballScores.add(score);
                    frameScores.add(ballScores);
                    frameCount++;
                    scoreCount++;
                    continue;
                } else if(frameCount > 9){
                    ballScores.add(score);
                } else {
                    ballScores.add(score);
                }

                if(scoreCount == scores.size()-1){
                    frameScores.add(ballScores);
                }
                ballCountInFrame++;
            } else if (ballCountInFrame % 2 == 1){
                int totalFrameScore=0;
                ballScores.add(score);
                for(Integer ballScore : ballScores){
                    totalFrameScore += ballScore;
                }
                if(totalFrameScore > 10){
                    System.out.println("Frame score cannot be more than 10");
                    throw new Exception("Invalid scores - Frame score cannot be more than 10");
                }
                frameScores.add(ballScores);
                frameCount++;
                ballCountInFrame = 0;
            }
            scoreCount++;
        }
        return frameScores;
    }

    public int calculateFrameScoreWithBonus(List<List<Integer>> frameScores){
        List<Integer> frameScoresWithBonus = new ArrayList();
        int frameCount = 0;

        for(List<Integer> frameScore : frameScores) {
            //For a strike, calculate score if player has played the next two balls or played only one ball or haven't played any ball and similarly for a spare,
            // if the player has played the next ball
            if (frameScore.get(0) == 10) {
                if ((frameScores.size()-1 >= frameCount+1) && frameScores.get(frameCount+1).get(0) == 10) {
                    if((frameScores.size()-1 >= frameCount+2)) {
                        frameScoresWithBonus.add(frameScore.get(0) + frameScores.get(frameCount + 1).get(0) + frameScores.get(frameCount + 2).get(0));
                    } else {
                        frameScoresWithBonus.add(frameScore.get(0) + frameScores.get(frameCount+1).get(0));
                    }
                } else if ((frameScores.size()-1 >= frameCount+1)) {
                    if(frameScores.get(frameCount+1).size()-1 >= 1) {
                        frameScoresWithBonus.add(frameScore.get(0) + frameScores.get(frameCount + 1).get(0) + frameScores.get(frameCount + 1).get(1));
                    } else {
                        frameScoresWithBonus.add(frameScore.get(0) + frameScores.get(frameCount+1).get(0));
                    }
                } else {
                    frameScoresWithBonus.add(frameScore.get(0));
                }
            } else if ((frameScores.size()-1 >= frameCount+1) &&  frameScore.get(0) + frameScore.get(1) == 10) {
                if(frameCount >= frameScores.size() - 1) {
                    frameScoresWithBonus.add(10);
                } else {
                    frameScoresWithBonus.add(10 + frameScores.get(frameCount + 1).get(0));
                }
            } else {
                if(frameScore.size()-1 >= 1) {
                    frameScoresWithBonus.add(frameScore.get(0) + frameScore.get(1));
                } else {
                    frameScoresWithBonus.add(frameScore.get(0));
                }
            }
            if (++frameCount > 9)
                break;
        }

        for(Integer totalFrameScore : frameScoresWithBonus){
            totalScore += totalFrameScore;
        }

        return totalScore;
    }
}
