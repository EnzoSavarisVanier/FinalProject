package org.example;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public enum Gender {
        MALE, FEMALE
    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = String.format("S%06d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }


    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course))
        {
            return false;
        }
        registeredCourses.add(course);
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course))
        {
            return false;
        }
        registeredCourses.remove(course);
        return true;
    }

    /**
     * simplifies student string
     * @return simplified student string, only id, name, and department
     */
    public String toSimplifiedString() {
        return "Student{" +
                studentId + '\'' +
                studentName + '\'' +
                ((department == null) ? "null" : department.getDepartmentName()) + '\'' +
                '}';
    }
}
