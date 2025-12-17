package org.example;
import java.util.ArrayList;
import java.util.Random;
import lombok.*;

@Getter
@Setter
public class Assignment {
    private String assignmentId = String.format("%02d", nextId++);
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    /**
     * calculates average score of an assignment
     */
    public void calcAssignmentAvg() {
        double totalScore = 0;
        for (Integer score : scores) {
            totalScore += score;
        }

        double avg = totalScore / scores.size();
    }

    /**
     * generates random scores for all students in an assignment
     */
    public void generateRandomScore() {
        Random rand = new Random();

        for (int i = 0; i < scores.size(); i++) {
            int random = rand.nextInt(11);
            int score = switch (random) {
                case 0 -> rand.nextInt(60);
                case 1, 2 -> 60 + rand.nextInt(10);
                case 3, 4 -> 70 + rand.nextInt(10);
                case 5, 6, 7, 8 -> 80 + rand.nextInt(10);
                case 9, 10 -> 90 + rand.nextInt(11);
                default -> 0;
            };

            scores.set(i, score);
        }
    }

    @Override
    public String toString() {
        return "Assignment{" +
                assignmentId + '\'' +
                assignmentName + '\'' +
                weight + '\'' +
                '}';
    }
}
