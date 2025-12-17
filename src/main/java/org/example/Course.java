package org.example;
import util.Util;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Integer> finalScores;
    private static int nextId = 1;

    /**
     * determines if assignment weight is valid
     * @return true if it is valid, false if not valid, should be 100
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;

        for (Assignment a : assignments) {
            totalWeight += a.getWeight();
        }

        return totalWeight == 100;
    }

    /**
     * adds student object and score to assignment if not registered
     * @param student student object
     * @return false if already registered, true if not
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student))
        {
            return false;
        }
        registeredStudents.add(student);

        for (Assignment a : assignments) {
            a.getScores().add(null);
        }
        finalScores.add(null);

        return true;
    }

    /**
     * calculates weighted average for all assignments for each student (aka final scores)
     * @return all weighted averages
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];
        for (int i = 0; i < registeredStudents.size(); i++) {
            double finalScore = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null)
                {
                    finalScore += score * a.getWeight() / 100.0;
                }
            }
            averages[i] = (int) finalScore;
        }
        return averages;
    }

    /**
     * creates an assignment if input is correct
     * @param assignmentName name of assignment
     * @param weight weight of assignment
     * @param maxScore highest score achieved in the assignment
     * @return false if input incorrect, true if correct
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        if (assignmentName == null || weight <= 0 || weight > 100 || maxScore <= 0 || maxScore > 100)
        {
            return false;
        }

        Assignment assignment = new Assignment();
        assignment.setAssignmentId(String.format("%02d", nextId++));
        assignment.setAssignmentName(assignmentName);
        assignment.setWeight(weight);
        assignment.setScores(new ArrayList<>());

        for (int i = 0; i < registeredStudents.size() - 1; i++) {
            assignment.getScores().add(null);
        }
        assignment.getScores().add(maxScore);

        assignments.add(assignment);
        return true;
    }

    /**
     * generates random scores for all assignments for each student, and calculates final scores of the students
     */
    public void generateScores() {
        for (Assignment a : assignments) {
            a.generateRandomScore();
        }

        for (int i = 0; i < registeredStudents.size(); i++) {
            double finalScore = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null)
                {
                    finalScore += score * a.getWeight() / 100.0;
                }
            }

            finalScores.set(i, (int) finalScore);
        }
    }

    /**
     * shows the student scores, averages for assignments, final scores,
     * assignment names, course name and id, and student names
     */
    public void displayScores() {
        int[] finalScores = calcStudentsAverage();

        this.courseId = String.format("C-D%s-%02d", department.getDepartmentId(), nextId++);
        System.out.printf("Course: %s(%s)\n", Util.toTitleCase(courseName), courseId);

        System.out.printf("%-30s", "");
        for (Assignment a : assignments) {
            System.out.printf("%-15s", a.getAssignmentName());
        }
        System.out.printf("%-15s", "Final Score\n");

        for (int i = 0; i < registeredStudents.size(); i++) {
            System.out.printf("%-30s", Util.toTitleCase(registeredStudents.get(i).getStudentName()));

            for (Assignment a : assignments) {
                System.out.printf("%-15d", a.getScores().get(i));
            }
            System.out.printf("%-15d\n", finalScores[i]);
        }

        System.out.printf("%-30s", "Average");
        for (Assignment a : assignments) {
            int avg = 0;
            for (Integer score : a.getScores()) {
                avg += score;
            }
            System.out.printf("%-15d", avg / a.getScores().size());
        }
        System.out.println();
    }

    /**
     * simplifies the tostring of an assignment
     * @return simplified tostring assignment
     */
    public String toSimplifiedString() {
        return "Assignment{" +
                courseId + '\'' +
                Util.toTitleCase(courseName) + '\'' +
                credits + '\'' +
                Util.toTitleCase(department.getDepartmentName()) + '\'' +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Assignment{" +
                courseId + '\'' +
                Util.toTitleCase(courseName) + '\'' +
                credits + '\'' +
                Util.toTitleCase(department.getDepartmentName()) + '\'');

        for (Assignment a : assignments) {
            result.append("  ").append(a.toString()).append('\'');
        }

        for (Student s : registeredStudents) {
            result.append("  ").append(s.toSimplifiedString()).append('\'');
        }

        result.append(isAssignmentWeightValid() ? " AssignmentWeightValid" : " AssignmentWeightInvalid");
        result.append('}');
        return result.toString();
    }
}