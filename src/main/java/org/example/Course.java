package org.example;
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

    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;

        for (Assignment a : assignments) {
            totalWeight += a.getWeight();
        }

        return totalWeight == 100;
    }

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

    public void displayScores() {
        int[] finalScores = calcStudentsAverage();

        this.courseId = String.format("C-D%s-%02d", department.getDepartmentId(), nextId++);
        System.out.printf("Course: %s(%s)\n", courseName, courseId);

        System.out.printf("%-30s", "");
        for (Assignment a : assignments) {
            System.out.printf("%-15s", a.getAssignmentName());
        }
        System.out.printf("%-15s", "Final Score\n");

        for (int i = 0; i < registeredStudents.size(); i++) {
            System.out.printf("%-30s", registeredStudents.get(i).getStudentName());

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

    public String toSimplifiedString() {
        return "Assignment{" +
                courseId + '\'' +
                courseName + '\'' +
                credits + '\'' +
                department.getDepartmentName() + '\'' +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Assignment{" +
                courseId + '\'' +
                courseName + '\'' +
                credits + '\'' +
                department.getDepartmentName() + '\'');

        for (Assignment a : assignments) {
            result.append("  ").append(a.toString()).append('\'');
        }

        for (Student s : registeredStudents) {
            result.append(s.getStudentId()).append('\'').append(s.getStudentName()).append('\'').append(s.getDepartment().getDepartmentName());
        }

        result.append(isAssignmentWeightValid() ? " AssignmentWeightValid" : " AssignmentWeightInvalid");
        result.append('}');
        return result.toString();
    }
}